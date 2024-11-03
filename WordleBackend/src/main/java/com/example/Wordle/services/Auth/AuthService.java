package com.example.Wordle.services.Auth;

import com.example.Wordle.dtos.LoginDTO;
import com.example.Wordle.dtos.UserEditDto;
import com.example.Wordle.dtos.UserLoginResponseDTO;
import com.example.Wordle.dtos.UserRegistrationDTO;
import com.example.Wordle.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    User registerUser(UserRegistrationDTO userRegistrationDTO);
    UserLoginResponseDTO login(LoginDTO loginDTO);
    User getAuthUser();
    User editUser(UserEditDto userEditDto);
    String deleteUser(User user);
}
