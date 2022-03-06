package com.mcgb.varbifikrimbackend.security;

import com.mcgb.varbifikrimbackend.util.helper.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    private final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private UserDetailService userDetailsService;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;
    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER_PREFIX)) {
            jwtToken = jwtTokenUtil.getRequestTokenHeader(requestTokenHeader);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error(String.format("JWT Token alınırken bir hata oluştu : %s", e.getMessage()));
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token süresi doldu. %s");
            }
        } else {
            logger.warn("JWT Token formatı yanlış. %s");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String blackListedToken = redisUtil.getBlackListedToken(username, String.valueOf(jwtTokenUtil.getExpirationDateFromToken(jwtToken).getTime()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(jwtToken, userDetails) &&
                    (!StringUtils.hasText(blackListedToken) ||
                            StringUtils.hasText(blackListedToken) && !jwtToken.equals(blackListedToken))) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        if (request.getRequestURL().indexOf("/api/v1/auth/login") != -1 || request.getRequestURL().indexOf("/api/v1/register") != -1)
            return true;
        return false;
    }
}
