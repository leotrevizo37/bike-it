package com.bikeit.identity.domain;

import jakarta.validation.constraints.NotBlank;

public record UserAccount(
        @NotBlank String userId,
        @NotBlank String displayName,
        boolean active) {
}
