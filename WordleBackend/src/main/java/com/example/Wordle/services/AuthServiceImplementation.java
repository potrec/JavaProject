package com.example.Wordle.services;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.models.Role;
import com.example.Wordle.models.User;
import com.example.Wordle.repository.RoleRepository;
import com.example.Wordle.repository.UserRepository;
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
        } catch (AuthenticationException e) {
            return new UserLoginResponseDTO(null, null);
        }
    }

//    @Override
//    public UserDTO registerUser(SignupDTO signupDTO) {
//        User user = new User();
//        user.setUsername(signupDTO.getUsername());
//        user.setPassword(signupDTO.getPassword());
//        user.setEmail(signupDTO.getEmail());
//        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
//        user.setAppUserRole(AppUserRole.USER);
//        User createdUser = userRepository.save(user);
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail(createdUser.getEmail());
//        userDTO.setUsername(createdUser.getUsername());
//        return userDTO;
//    }
//
//    @Override
//    public UserDTO login(LoginDTO loginDTO) {
//        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> EmailNotFoundException("Email not found"));
//        if(user == null)
//        {
//            return null;
//        }
//        if(new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.getPassword()))
//        {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setEmail(user.getEmail());
//            userDTO.setUsername(user.getUsername());
//            return userDTO;
//        }
//        return null;
//    }
//
//    private RuntimeException EmailNotFoundException(String emailNotFound) {
//        return new RuntimeException(emailNotFound);
//    }
}
