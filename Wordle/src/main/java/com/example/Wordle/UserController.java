package com.example.Wordle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listStudents = userRepo.findAll();

        return "users";
    }
}
