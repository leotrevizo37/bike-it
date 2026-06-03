package com.bikeit.weather.application;

import com.bikeit.shared.domain.GeoPoint;
import com.bikeit.weather.domain.WeatherSnapshot;
import com.bikeit.weather.infrastructure.WeatherProviderClient;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherProviderClient providerClient;

    public WeatherService(WeatherProviderClient providerClient) {
        this.providerClient = providerClient;
    }

    public WeatherSnapshot getConditions(GeoPoint point, LocalDateTime observedAt) {
        return providerClient.fetch(point, observedAt);
    }
}
