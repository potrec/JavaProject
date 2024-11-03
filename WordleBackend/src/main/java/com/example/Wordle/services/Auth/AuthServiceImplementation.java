package com.example.Wordle.services.Auth;

import com.example.Wordle.dtos.*;
import com.example.Wordle.exceptions.DataNotFoundException;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    public UserLoginResponseDTO login(LoginDTO loginDTO) throws ValidationException, DataNotFoundException {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new ValidationException(Collections.singletonList("No user with given username is found"),"Invalid Credentials"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new ValidationException(Collections.singletonList("Invalid Password"), "Invalid Credentials");
        }
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );

            String token = tokenService.generateJwt(auth);
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.id = user.getUserId();
            userResponseDTO.username = user.getUsername();
            userResponseDTO.email = user.getEmail();

            return new UserLoginResponseDTO(userResponseDTO, token);
        } catch (Exception e) {
            throw new DataNotFoundException(e.getMessage());
        }
    }

    public User getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userName = jwt.getSubject();
        return userRepository.findByUsername(userName).orElseThrow(() -> new DataNotFoundException("No user with given username "+ userName+" is found"));
    }

    public User editUser(UserEditDto userEditDto) {
        User authUser = getAuthUser();
        Optional<User> userByEmail = userRepository.findByEmail(userEditDto.getEmail());

        if(userByEmail.isPresent() && !userByEmail.get().getUserId().equals(authUser.getUserId())){
            throw new ValidationException(Collections.singletonList("Email is already taken"), "Email is already taken");
        }

        Optional<User> userByUsername = userRepository.findByUsername(userEditDto.getUsername());
        if(userByUsername.isPresent() && !userByUsername.get().getUserId().equals(authUser.getUserId())){
            throw new ValidationException(Collections.singletonList("Username is already taken"), "Username is already taken");
        }

        authUser.setEmail(userEditDto.getEmail());
        authUser.setUsername(userEditDto.getUsername());
        authUser.setPassword(passwordEncoder.encode(userEditDto.getPassword()));

        return userRepository.save(authUser);
    }

    public String deleteUser(User user){
        userRepository.delete(user);

        return "User deleted successfully";
    }
}
