package com.mcgb.varbifikrimbackend.security;

import com.mcgb.varbifikrimbackend.enums.UserStatusTypeEnum;
import com.mcgb.varbifikrimbackend.entity.User;
import com.mcgb.varbifikrimbackend.repository.UserRepository;
import com.mcgb.varbifikrimbackend.util.exception.ExceptionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailService implements UserDetailsService, ExceptionConstants {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format(KULLANICI_BULUNAMADI, username));
        if (user.getStatus().equals(UserStatusTypeEnum.PASSIVE))
            throw new DisabledException(KULLANICI_AKTIF_DEGIL);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
