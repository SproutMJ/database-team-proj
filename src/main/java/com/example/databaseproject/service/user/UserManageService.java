package com.example.databaseproject.service.user;

import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.user.request.CreateUserRequestDto;
import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class UserManageService {
    public void createUser(CreateUserRequestDto userRequestDto) {
    }
}
