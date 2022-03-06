package com.mcgb.varbifikrimbackend.converter;

import com.mcgb.varbifikrimbackend.dto.register.RegisterDTO;
import com.mcgb.varbifikrimbackend.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterConverter {

    @Autowired
    ModelMapper modelMapper;

    public User convertRegisterToUser(RegisterDTO registerDTO) {
        User user = modelMapper.map(registerDTO, User.class);
        return user;
    }
}
