package com.bikeit.recommendation.application;

import com.bikeit.commute.application.CommuteProfileService;
import com.bikeit.events.application.IncidentEventService;
import com.bikeit.events.domain.IncidentSignal;
import com.bikeit.history.application.TripHistoryService;
import com.bikeit.recommendation.domain.CommuteRecommendation;
import com.bikeit.traffic.application.TrafficService;
import com.bikeit.traffic.domain.TrafficSnapshot;
import com.bikeit.weather.application.WeatherService;
import com.bikeit.weather.domain.WeatherSnapshot;
import com.bikeit.identity.application.IdentityService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecommendationServiceTests {

    @Test
    void previewBuildsBoundedScore() {
        IdentityService identityService = new IdentityService();
        RecommendationService service = new RecommendationService(
                new CommuteProfileService(identityService),
                new WeatherService((point, observedAt) -> new WeatherSnapshot("Clear", 10, 20.0, 8.0)),
                new TrafficService((origin, destination, departureAt) -> new TrafficSnapshot(30, 25, 38, 1.2)),
                new IncidentEventService((origin, destination, departureAt) -> new IncidentSignal(false, 0, "None")),
                new TripHistoryService(identityService));

        CommuteRecommendation preview = service.preview(
                "demo-user",
                LocalDateTime.of(2026, 6, 3, 8, 0),
                LocalDateTime.of(2026, 6, 3, 18, 0));

        assertNotNull(preview);
        assertTrue(preview.score() >= 0 && preview.score() <= 100);
    }
}
