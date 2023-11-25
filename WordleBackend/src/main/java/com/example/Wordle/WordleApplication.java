package com.example.Wordle;

import com.example.Wordle.models.Role;
import com.example.Wordle.models.User;
import com.example.Wordle.repository.RoleRepository;
import com.example.Wordle.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class WordleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordleApplication.class, args);
	}

	@Bean
	CommandLineRunner run (RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder)
	{
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("USER"));
			Set<Role> roles = new HashSet<Role>();
			roles.add(adminRole);

			User admin = new User( "admin", passwordEncoder.encode("admin"), "admin@gmail.com", roles);
			userRepository.save(admin);
		};
	}

}
