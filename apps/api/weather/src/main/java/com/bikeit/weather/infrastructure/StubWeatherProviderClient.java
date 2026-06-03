package com.bikeit.weather.infrastructure;

import com.bikeit.shared.domain.GeoPoint;
import com.bikeit.weather.domain.WeatherSnapshot;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class StubWeatherProviderClient implements WeatherProviderClient {

    @Override
    public WeatherSnapshot fetch(GeoPoint point, LocalDateTime observedAt) {
        int hour = observedAt.getHour();
        if (hour >= 17) {
            return new WeatherSnapshot("Cloudy return window", 45, 23.0, 18.0);
        }
        return new WeatherSnapshot("Clear morning window", 10, 19.5, 9.0);
    }
}
