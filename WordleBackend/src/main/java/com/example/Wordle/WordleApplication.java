package com.example.Wordle;

import com.example.Wordle.models.Role;
import com.example.Wordle.models.User;
import com.example.Wordle.models.Word;
import com.example.Wordle.repository.RoleRepository;
import com.example.Wordle.repository.UserRepository;
import com.example.Wordle.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class WordleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordleApplication.class, args);
	}
	//Dont delete or tests don't work
//			@Bean
//			public PasswordEncoder passwordEncoder() {
//				return new BCryptPasswordEncoder(10,new SecureRandom());
//			}
	@Bean
	CommandLineRunner run (RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, WordRepository wordRepository)
	{
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isEmpty()) {
				Role adminRole = roleRepository.save(new Role("ADMIN"));
				roleRepository.save(new Role("USER"));
				Set<Role> roles = new HashSet<Role>();
				roles.add(adminRole);

				User admin = new User("admin", passwordEncoder.encode("admin"), "admin@gmail.com", roles);
				userRepository.save(admin);
			}
			if(wordRepository.findById(1).isEmpty()) {
				BufferedReader reader = new BufferedReader(new FileReader("slowa_5.txt"));
				String line;
				List<String> words = new ArrayList<>();
				while ((line = reader.readLine()) != null) {
					words.add(line);
				}
				reader.close();
				List<Word> wordEntities = new ArrayList<>();
				for (String word : words) {
					Word wordEntity = new Word();
					wordEntity.setWord(word);
					wordEntities.add(wordEntity);
				}
				wordRepository.saveAll(wordEntities);
			}
		};
	}

}
