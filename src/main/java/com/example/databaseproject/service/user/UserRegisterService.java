package com.example.databaseproject.service.user;

import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.auth.request.UserRegisterRequestDto;
import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegisterService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public HttpStatus userCreate(UserRegisterRequestDto userRegisterRequestDto){
        try{
            userRepository.createUser(new User(null, userRegisterRequestDto.getUsername(), passwordEncoder.encode(userRegisterRequestDto.getPassword()), null));
            return HttpStatus.CREATED;
        }catch (Exception e){
            System.out.println(e);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
