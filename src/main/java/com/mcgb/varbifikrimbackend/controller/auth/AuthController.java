package com.mcgb.varbifikrimbackend.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcgb.varbifikrimbackend.dto.auth.AuthRequestDTO;
import com.mcgb.varbifikrimbackend.dto.auth.AuthResponseDTO;
import com.mcgb.varbifikrimbackend.entity.User;
import com.mcgb.varbifikrimbackend.repository.UserRepository;
import com.mcgb.varbifikrimbackend.security.JWTTokenUtil;
import com.mcgb.varbifikrimbackend.security.UserDetailService;
import com.mcgb.varbifikrimbackend.util.exception.BusinessException;
import com.mcgb.varbifikrimbackend.util.exception.ExceptionConstants;
import com.mcgb.varbifikrimbackend.util.helper.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements ExceptionConstants {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserDetailService userDetailService;
    @Autowired
    JWTTokenUtil jwtTokenUtil;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            User user = userRepository.findByUsername(authRequest.getUsername());
            String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new AuthResponseDTO(user.getUsername(), token));
        } catch (BadCredentialsException e) {
            throw new BusinessException(GIRIS_BILGILERI_HATALI);
        } catch (JsonProcessingException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, HttpServletRequest request) {
        String token = jwtTokenUtil.getRequestTokenHeader(authHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        new SecurityContextLogoutHandler().logout(request, null, null);
        redisUtil.setBlackListedToken(username, token, jwtTokenUtil.getExpirationDateFromToken(token));
        return ResponseEntity.ok("Çıkış başarılı");
    }
}
