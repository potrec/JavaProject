package com.example.Wordle.repository;

import com.example.Wordle.enums.AppUserRole;
import com.example.Wordle.models.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepositorySaveUser() {
        Assertions.assertThat(userRepository).isNotNull();

        User user = User.builder()
                .username("Username")
                .password("Password")
                .email("email@example.com")
                .appUserRole(AppUserRole.USER).build();
        Assertions.assertThat(user).isNotNull();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
