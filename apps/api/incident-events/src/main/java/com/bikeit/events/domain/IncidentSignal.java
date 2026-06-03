package com.bikeit.events.domain;

public record IncidentSignal(
        boolean active,
        int load,
        String narrative) {
}
