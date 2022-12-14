package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    public List<User> findByName(String name) {
        List<User> users = jdbcTemplate.query(
                "select * from USER where name=?",
                (rs, row)->{
                    User newUser = User.builder()
                            .id(rs.getLong("ID"))
                            .name(rs.getString("name"))
                            .address(rs.getString("address"))
                            .email(rs.getString("email"))
                            .phone(rs.getString("phone"))
                            .job(rs.getString("job"))
                            .birthday(rs.getDate("birthday"))
                            .socialNumber(rs.getString("social_number"))
                            .build();
                    return newUser;
                },
                name);
        return users;
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

    public User findClosestBirthdayUser(Date birthday){
        User user = jdbcTemplate.queryForObject(
                "select *, if ((dayofyear(user.birthday) - dayofyear(?)) < 0, dayofyear(user.birthday) - dayofyear(?) + 500, dayofyear(user.birthday) - dayofyear(?)) m\n" +
                        "    from user\n" +
                        "    order by m, user.name\n" +
                        "    limit 1"
                ,(rs, row)->User.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .address(rs.getString("address"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .job(rs.getString("job"))
                        .birthday(rs.getDate("birthday"))
                        .socialNumber(rs.getString("social_number"))
                        .build()
                ,birthday, birthday, birthday
        );
        return user;
    }
}
