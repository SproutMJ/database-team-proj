package com.example.databaseproject.service.user;

import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService {
    private final UserRepository userRepository;


}
