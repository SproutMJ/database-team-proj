package com.example.databaseproject.controller.auth;

import com.example.databaseproject.dto.auth.request.UserLoginRequestDto;
import com.example.databaseproject.dto.auth.request.UserRegisterRequestDto;
import com.example.databaseproject.dto.auth.response.LoginTokenDto;
import com.example.databaseproject.service.user.UserLoginService;
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

    private final UserLoginService userLoginService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    @CrossOrigin(origins = {"http://localhost:3000", "https://localhost:3000", "http://localhost:3000/register", "https://localhost:3000/register"})
    public ResponseEntity userRegister(@RequestBody UserRegisterRequestDto userRegisterRequestDto){
        return ResponseEntity.status(userRegisterService.userCreate(userRegisterRequestDto)).build();
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sign-in")
    @CrossOrigin(origins = {"http://localhost:3000", "https://localhost:3000", "http://localhost:3000/login", "https://localhost:3000/login"})
    public ResponseEntity<LoginTokenDto> userRegister(@RequestBody UserLoginRequestDto userLoginRequestDto){
        String token = userLoginService.login(userLoginRequestDto);
        if(token == null) return ResponseEntity.badRequest().build();
        else return ResponseEntity.ok(new LoginTokenDto(token));
    }

}
