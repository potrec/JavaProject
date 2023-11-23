package com.example.Wordle.repository;

import com.example.Wordle.enums.AppUserRole;
import com.example.Wordle.models.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepositorySaveUser() {
        User user = User.builder().username("Username").password("Password").email("email@example.com").appUserRole(AppUserRole.USER).build();

        User savedUser = userRepository.save(user);

    }
}
