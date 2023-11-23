package com.example.Wordle.repository;

import com.example.Wordle.WordleApplication;
import com.example.Wordle.enums.AppUserRole;
import com.example.Wordle.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository underTestUserRepository;

    @Test
    public void UserRepositorySaveUser() {
        User user = User.builder()
                .username("Username")
                .password("Password")
                .email("email@example.com")
                .appUserRole(AppUserRole.USER).build();
        Assertions.assertThat(user).isNotNull();

        User savedUser = underTestUserRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
