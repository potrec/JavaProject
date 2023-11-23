package com.example.Wordle.repository;

import com.example.Wordle.WordleApplication;
import com.example.Wordle.enums.AppUserRole;
import com.example.Wordle.models.User;
import com.github.javafaker.Faker;
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
    private static final Faker FAKER = new Faker();
    private static final String email = "test@example.com";

    @Test
    public void UserRepositorySaveUser() {
        User user = User.builder()
                .username(FAKER.funnyName().name())
                .password("Password")
                .email(email)
                .appUserRole(AppUserRole.USER).build();
        Assertions.assertThat(user).isNotNull();

        User savedUser = underTestUserRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void findByEmail() {
        User user = underTestUserRepository.findByEmail(email);
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getEmail()).isEqualTo(email);
    }
}
