package com.example.databaseproject.service.user;

import com.example.databaseproject.configuration.security.jwt.provider.JwtTokenProvider;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.auth.request.UserLoginRequestDto;
import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService {
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public String login(UserLoginRequestDto userLoginRequestDto){
        User user = userRepository.findByUsername(userLoginRequestDto.getUsername());
        if(passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())){
            return jwtTokenProvider.createToken(user.getUsername(), null);
        }else {
            return null;
        }
    }
}
