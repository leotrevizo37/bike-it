package com.bikeit.tracking.domain;

public record TrackingSessionPlan(
        boolean enabled,
        int uploadIntervalSeconds,
        boolean backgroundCollectionEnabled) {
}
