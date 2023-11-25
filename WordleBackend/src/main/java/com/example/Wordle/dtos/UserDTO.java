package com.example.Wordle.dtos;

import com.example.Wordle.enums.AppUserRole;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private AppUserRole appUserRole;
}
