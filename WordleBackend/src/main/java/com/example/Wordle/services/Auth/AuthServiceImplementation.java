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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new ValidationException(Collections.singletonList("No user with given username is found"),"Invalid Credentials")));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            throw new ValidationException(Collections.singletonList("Wrong password for this user"),"Invalid Credentials");
        }
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            String token = tokenService.generateJwt(auth);

            return new UserLoginResponseDTO(user, token);
        } catch (Exception e) {
            return new UserLoginResponseDTO(null, null);
        }
    }

    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userName = jwt.getSubject();
        return userRepository.findByUsername(userName).orElseThrow(() -> new CustomDataNotFoundException("No user with given username "+ userName+" is found"));
    }

    public User editUser(User user) {
        return userRepository.save(user);
    }
}
