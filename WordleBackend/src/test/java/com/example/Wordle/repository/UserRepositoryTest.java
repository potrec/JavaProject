package com.example.Wordle.repository;

import com.example.Wordle.models.Role;
import com.example.Wordle.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository underTestUserRepository;

    @Autowired
    private RoleRepository roleRepository;
    private static final String email = "test@example.com";
    private static final String username = "admin";
    private static final String password = "password";
    @Test
    public void UserRepositorySaveUser() {
        Role adminRole = roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
        Set<Role> roles = new HashSet<Role>();
        roles.add(adminRole);
        User admin = new User( username, "password", email, roles);
        Assertions.assertThat(admin).isNotNull();

        User savedUser = underTestUserRepository.save(admin);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUserId()).isGreaterThan(0);
    }

    @Test
    public void UserRepositoryFindByUsername() {
        underTestUserRepository.findByUsername(username);
        Assertions.assertThat(underTestUserRepository.findByUsername(username)).isPresent();
    }
}
