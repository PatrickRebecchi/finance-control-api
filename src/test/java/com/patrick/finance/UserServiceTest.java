package com.patrick.finance;


import com.patrick.finance.dto.UserSummaryDTO;
import com.patrick.finance.entity.User;
import com.patrick.finance.exception.UserNotFoundException;
import com.patrick.finance.repository.UserRepository;
import com.patrick.finance.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void deveRetornarUsuarioQuandoEncontrado() {

        User user = new User();
        user.setId(1L);
        user.setNickName("Patrick");

        when(repository.findById(1L))
                .thenReturn(Optional.of(user));

        UserSummaryDTO resultado = service.obterUserPorId(1L);

        assertNotNull(resultado);
        assertEquals("Patrick", resultado.nickName());
        assertEquals(1L, resultado.id());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> service.obterUserPorId(1L)
        );
    }
}

