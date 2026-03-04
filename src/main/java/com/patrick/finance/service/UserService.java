package com.patrick.finance.service;

import com.patrick.finance.dto.DtoTest;
import com.patrick.finance.dto.UserCreateDTO;
import com.patrick.finance.dto.UserResponseDTO;
import com.patrick.finance.dto.UserSummaryDTO;
import com.patrick.finance.entity.User;
import com.patrick.finance.exception.EmailAlreadyExistsException;
import com.patrick.finance.exception.GlobalExceptionHandler;
import com.patrick.finance.exception.UserNotFoundException;
import com.patrick.finance.repository.UserRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService
{
    @Autowired
//    private UserRepository repository;
//    private final PasswordEncoder encoder;

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }


    public UserResponseDTO createUser(UserCreateDTO dto) {

        // Verifica se o email já existe
        if (repository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("Email já cadastrado!");
        }
        // Criptografa senha
        String encryptedPassword = encoder.encode(dto.password());

        // Cria entidade
        User user = new User();
        user.setNickName(dto.nickName());
        user.setFullName(dto.fullName());
        user.setEmail(dto.email());
        user.setPassword(encryptedPassword);

        // Salva no banco
        User savedUser = repository.save(user);

        // Converte para DTO de resposta
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getNickName(),
                savedUser.getFullName(),
                savedUser.getEmail()
        );
    }

    private List<UserResponseDTO> converteDados(List<User> user){
        return user.stream()
                .map(s -> new UserResponseDTO(
                        s.getId(),
                        s.getNickName(),
                        s.getFullName(),
                        s.getEmail()))
                .collect(Collectors.toList());
    }

    private List<DtoTest> converteDadosComSenha(List<User> user){
        return user.stream()
                .map(s -> new DtoTest(
                        s.getId(),
                        s.getEmail(),
                        s.getPassword()))
                .collect(Collectors.toList());
    }

    public List<UserResponseDTO> obterTodosUsuarios() {
        return converteDados(repository.findAll());
    }
    public List<DtoTest> obterTodosUsuariosESenha() {
        return converteDadosComSenha(repository.findAll());
    }


    public UserSummaryDTO obterUserPorId(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()){
            User u = user.get();
            return new UserSummaryDTO(u.getId(), u.getNickName());
        }
        throw new UserNotFoundException("Usuario não encontrado!");
    }
}
