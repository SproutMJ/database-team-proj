package com.example.databaseproject.controller.user;

import com.example.databaseproject.dto.creditcard.request.CreateCreditCardTypeRequestDto;
import com.example.databaseproject.dto.user.request.CreateUserRequestDto;
import com.example.databaseproject.service.user.UserManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {
    private final UserManageService userManageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-user")
    public ResponseEntity creditCardCreate(@RequestBody CreateUserRequestDto userRequestDto){
        userManageService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
