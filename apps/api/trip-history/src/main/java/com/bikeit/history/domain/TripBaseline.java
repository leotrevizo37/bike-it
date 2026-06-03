package com.bikeit.history.domain;

public record TripBaseline(
        int readinessScore,
        int recentBikeTrips,
        int averageBikeMinutes) {
}
