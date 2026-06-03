package com.bikeit.commute.domain;

import com.bikeit.shared.domain.GeoPoint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CommuteProfile(
        @NotBlank String userId,
        @NotBlank String originLabel,
        @NotBlank String destinationLabel,
        @NotNull GeoPoint originPoint,
        @NotNull GeoPoint destinationPoint,
        @PositiveOrZero int outboundBufferMinutes,
        @PositiveOrZero int returnBufferMinutes) {
}
