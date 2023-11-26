package com.example.Wordle.services.Auth;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.exceptions.CustomDataNotFoundException;
import com.example.Wordle.exceptions.ValidationException;
import com.example.Wordle.models.Role;
import com.example.Wordle.models.User;
import com.example.Wordle.repository.RoleRepository;
import com.example.Wordle.repository.UserRepository;
import com.example.Wordle.services.Token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthServiceImplementation implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        String encodedPassword = passwordEncoder.encode(userRegistrationDTO.getPassword());
        Optional<Role> userRole = roleRepository.findByAuthority("USER");

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole.get());
        User user = new User(userRegistrationDTO.getUsername(), encodedPassword, userRegistrationDTO.getEmail(),authorities);
        return userRepository.save(user);

    }

    @Override
    public UserLoginResponseDTO login(LoginDTO loginDTO) {
        try {
            Authentication auth = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            return new UserLoginResponseDTO(userRepository.findByUsername(loginDTO.getUsername()), token);
        } catch (Exception e) {
            return new UserLoginResponseDTO(null, null);
        }
    }
}
