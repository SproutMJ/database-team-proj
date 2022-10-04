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
        User user = jdbcTemplate.queryForObject("",
                (rs, row)->{
                    User newUser = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"));
                    return newUser;
                });
        return user;
    }

    public boolean existsByUsername(String username) {
        User user = jdbcTemplate.queryForObject("",
                (rs, row)->{
                    User newUser = new User(rs.getLong("id"), rs.getString("username"), rs.getString("password"));
                    return newUser;
                });
        return user != null;
    }

    public void createUser(User user) {
        jdbcTemplate.update(
                "insert into USER (username, password) values (?, ?)",
                user.getUsername(), user.getPassword()
        );
    }

    public void deleteUser(User user) {
        jdbcTemplate.update(
                "delete from USER where username=?",
                user.getUsername()
        );
    }
}
