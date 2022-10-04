package com.example.databaseproject.controller.auth;

import com.example.databaseproject.dto.auth.request.UserLoginRequestDto;
import com.example.databaseproject.dto.auth.request.UserRegisterRequestDto;
import com.example.databaseproject.service.user.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserAuthController {
    private final UserRegisterService userRegisterService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public ResponseEntity userRegister(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        return (ResponseEntity) ResponseEntity.status(userRegisterService.userCreate(userRegisterRequestDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sign-in")
    public ResponseEntity userRegister(@RequestBody UserLoginRequestDto userLoginRequestDto){
        return null;
    }
}
