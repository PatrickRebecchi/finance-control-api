package com.patrick.finance.dto;

public record ErrorResponse(
        int status,
        String message) {}

