package com.bikeit.shared.domain;

import java.time.LocalDateTime;

public record JourneyWindow(LocalDateTime outboundAt, LocalDateTime returnAt) {
}
