package com.bikeit.recommendation.domain;

import java.util.List;

public record CommuteRecommendation(
        String userId,
        int score,
        RecommendationDecision decision,
        List<String> reasons,
        LegAssessment outbound,
        LegAssessment inbound,
        int readinessScore) {
}
