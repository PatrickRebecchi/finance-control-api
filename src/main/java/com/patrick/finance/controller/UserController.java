package com.patrick.finance.controller;
import com.patrick.finance.dto.DtoTest;
import com.patrick.finance.dto.UserCreateDTO;
import com.patrick.finance.dto.UserResponseDTO;
import com.patrick.finance.exception.EmailAlreadyExistsException;
import com.patrick.finance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/senha")
    public List<DtoTest> obterUserAndPassword() {
        return service.obterTodosUsuariosESenha();
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody @Valid UserCreateDTO dto) {
        UserResponseDTO response = service.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
