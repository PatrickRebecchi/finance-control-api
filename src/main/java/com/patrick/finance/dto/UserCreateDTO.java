package com.patrick.finance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateDTO (@NotBlank String nickName,
                             @NotBlank String fullName,
                             @NotBlank @Email String email,
                             @NotBlank
                             @Size(min = 6, message = "Senha precisa ter pelo menos 6 caracterios")
                             String password) {
}
