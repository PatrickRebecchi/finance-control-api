package com.patrick.finance.dto;

public record UserResponseDTO(Long id,
                              String nickName,
                              String fullName,
                              String email) {
}
