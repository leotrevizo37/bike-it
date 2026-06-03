package com.bikeit.weather.domain;

public record WeatherSnapshot(
        String summary,
        int precipitationProbability,
        double temperatureCelsius,
        double windSpeedKph) {
}
