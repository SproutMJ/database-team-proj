package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public User findByUsernameAndPassword(String username, String password) {
        User user = jdbcTemplate.queryForObject(
                "select * from USER where username=? and password=?",
                (rs, row)->{
                    User newUser = null;
                    return newUser;
                },
                username, password);
        return user;
    }

    public User findByUsername(String username) {
        User user = jdbcTemplate.queryForObject(
                "select * from USER where username=?",
                (rs, row)->{
                    User newUser = null;
                    return newUser;
                },
                username);
        return user;
    }

    public boolean existsByUsername(String username) {
        User user = jdbcTemplate.queryForObject("",
                (rs, row)->{
            User newUser = null;
                    return newUser;
                });
        return user != null;
    }

    public void createUser(User user) {
        jdbcTemplate.update(
                "insert into USER (username, password) values (?, ?)",
                null, null
        );
    }
}
