package com.example.Wordle.services;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.SignupDTO;
import com.example.Wordle.dtos.UserDTO;
import com.example.Wordle.enums.AppUserRole;
import com.example.Wordle.models.User;
import com.example.Wordle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setPassword(signupDTO.getPassword());
        user.setEmail(signupDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        user.setAppUserRole(AppUserRole.USER);
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setUsername(createdUser.getUsername());
        return userDTO;
    }

    @Override
    public UserDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()-> EmailNotFoundException("Email not found"));
        if(user == null)
        {
            return null;
        }
        if(new BCryptPasswordEncoder().matches(loginDTO.getPassword(), user.getPassword()))
        {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
            return userDTO;
        }
        return null;
    }

    private RuntimeException EmailNotFoundException(String emailNotFound) {
        return new RuntimeException(emailNotFound);
    }
}
