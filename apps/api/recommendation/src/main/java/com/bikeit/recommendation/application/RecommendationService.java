package com.bikeit.recommendation.application;

import com.bikeit.commute.application.CommuteProfileService;
import com.bikeit.commute.domain.CommuteProfile;
import com.bikeit.events.application.IncidentEventService;
import com.bikeit.events.domain.IncidentSignal;
import com.bikeit.history.application.TripHistoryService;
import com.bikeit.history.domain.TripBaseline;
import com.bikeit.recommendation.domain.CommuteRecommendation;
import com.bikeit.recommendation.domain.LegAssessment;
import com.bikeit.recommendation.domain.RecommendationDecision;
import com.bikeit.traffic.application.TrafficService;
import com.bikeit.traffic.domain.TrafficSnapshot;
import com.bikeit.weather.application.WeatherService;
import com.bikeit.weather.domain.WeatherSnapshot;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private final CommuteProfileService commuteProfileService;
    private final WeatherService weatherService;
    private final TrafficService trafficService;
    private final IncidentEventService incidentEventService;
    private final TripHistoryService tripHistoryService;

    public RecommendationService(
            CommuteProfileService commuteProfileService,
            WeatherService weatherService,
            TrafficService trafficService,
            IncidentEventService incidentEventService,
            TripHistoryService tripHistoryService) {
        this.commuteProfileService = commuteProfileService;
        this.weatherService = weatherService;
        this.trafficService = trafficService;
        this.incidentEventService = incidentEventService;
        this.tripHistoryService = tripHistoryService;
    }

    public CommuteRecommendation preview(String userId, LocalDateTime outboundAt, LocalDateTime returnAt) {
        CommuteProfile profile = commuteProfileService.loadProfile(userId);
        TripBaseline baseline = tripHistoryService.loadBaseline(userId);

        WeatherSnapshot outboundWeather = weatherService.getConditions(profile.originPoint(), outboundAt);
        WeatherSnapshot returnWeather = weatherService.getConditions(profile.destinationPoint(), returnAt);

        TrafficSnapshot outboundTraffic = trafficService.getConditions(
                profile.originPoint(), profile.destinationPoint(), outboundAt);
        TrafficSnapshot returnTraffic = trafficService.getConditions(
                profile.destinationPoint(), profile.originPoint(), returnAt);

        IncidentSignal outboundEvents = incidentEventService.getSignals(
                profile.originPoint(), profile.destinationPoint(), outboundAt);
        IncidentSignal returnEvents = incidentEventService.getSignals(
                profile.destinationPoint(), profile.originPoint(), returnAt);

        int outboundScore = legScore(outboundWeather, outboundTraffic, outboundEvents);
        int returnScore = legScore(returnWeather, returnTraffic, returnEvents);
        int totalScore = Math.max(0, Math.min(100, (outboundScore + returnScore + baseline.readinessScore()) / 3));

        return new CommuteRecommendation(
                userId,
                totalScore,
                decisionFor(totalScore),
                buildReasons(outboundWeather, returnWeather, outboundTraffic, returnTraffic, outboundEvents, returnEvents),
                new LegAssessment(
                        "outbound",
                        outboundScore,
                        outboundWeather.summary(),
                        trafficSummary(outboundTraffic),
                        outboundEvents.narrative()),
                new LegAssessment(
                        "return",
                        returnScore,
                        returnWeather.summary(),
                        trafficSummary(returnTraffic),
                        returnEvents.narrative()),
                baseline.readinessScore());
    }

    private int legScore(WeatherSnapshot weather, TrafficSnapshot traffic, IncidentSignal signal) {
        int score = 100;
        score -= weather.precipitationProbability() / 3;
        score -= (int) Math.round(weather.windSpeedKph() / 2.0);
        score -= (traffic.driveMinutes() - traffic.typicalDriveMinutes());
        score -= signal.load() * 8;
        return Math.max(0, Math.min(100, score));
    }

    private RecommendationDecision decisionFor(int totalScore) {
        if (totalScore >= 72) {
            return RecommendationDecision.GO;
        }
        if (totalScore >= 52) {
            return RecommendationDecision.WATCH;
        }
        return RecommendationDecision.SKIP;
    }

    private List<String> buildReasons(
            WeatherSnapshot outboundWeather,
            WeatherSnapshot returnWeather,
            TrafficSnapshot outboundTraffic,
            TrafficSnapshot returnTraffic,
            IncidentSignal outboundEvents,
            IncidentSignal returnEvents) {
        List<String> reasons = new ArrayList<>();
        reasons.add("Outbound traffic multiplier: " + outboundTraffic.congestionIndex());
        reasons.add("Return traffic multiplier: " + returnTraffic.congestionIndex());
        reasons.add("Outbound precipitation risk: " + outboundWeather.precipitationProbability() + "%");
        reasons.add("Return precipitation risk: " + returnWeather.precipitationProbability() + "%");
        if (outboundEvents.active()) {
            reasons.add("Outbound event pressure detected");
        }
        if (returnEvents.active()) {
            reasons.add("Return event pressure detected");
        }
        return reasons;
    }

    private String trafficSummary(TrafficSnapshot trafficSnapshot) {
        return trafficSnapshot.driveMinutes()
                + " min drive vs "
                + trafficSnapshot.bikeMinutes()
                + " min bike";
    }
}
