package com.patrick.finance.controller;
import com.patrick.finance.dto.UserResponseDTO;
import com.patrick.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;


    @GetMapping
    public List<UserResponseDTO> obterUser() {
        return service.obterTodosUsuarios();
    }
}
