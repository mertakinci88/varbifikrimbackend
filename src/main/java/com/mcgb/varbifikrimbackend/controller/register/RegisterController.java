package com.mcgb.varbifikrimbackend.controller.register;

import com.mcgb.varbifikrimbackend.converter.RegisterConverter;
import com.mcgb.varbifikrimbackend.dto.register.RegisterDTO;
import com.mcgb.varbifikrimbackend.dto.register.RegisterResponseDTO;
import com.mcgb.varbifikrimbackend.dto.register.VerifyUserResponseDTO;
import com.mcgb.varbifikrimbackend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;
    @Autowired
    RegisterConverter registerConverter;

    @Transactional
    @PostMapping("/newuser")
    public ResponseEntity registerNewUser(@RequestBody @Valid RegisterDTO registerDTO) {
        RegisterResponseDTO registerResponse = registerService.registerNewUser(registerDTO);
        return ResponseEntity.ok(registerResponse);
    }

    @Transactional
    @GetMapping("/verifyuser")
    public ResponseEntity verifyUser(@RequestParam String code) {
        VerifyUserResponseDTO verifyUserResponseDTO = registerService.verifyUser(code);
        return ResponseEntity.ok(verifyUserResponseDTO);
    }
}
