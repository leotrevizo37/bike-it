package com.bikeit.recommendation.domain;

public record LegAssessment(
        String label,
        int score,
        String weatherSummary,
        String trafficSummary,
        String eventSummary) {
}
