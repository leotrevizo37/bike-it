package com.bikeit.platform.api.recommendation;

import com.bikeit.recommendation.application.RecommendationService;
import com.bikeit.recommendation.domain.CommuteRecommendation;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/preview")
    public CommuteRecommendation preview(
            @RequestParam(defaultValue = "demo-user") String userId,
            @RequestParam(defaultValue = "2026-06-03T08:00:00")
            @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime outboundAt,
            @RequestParam(defaultValue = "2026-06-03T18:00:00")
            @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime returnAt) {
        return recommendationService.preview(userId, outboundAt, returnAt);
    }
}
