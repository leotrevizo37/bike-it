package com.bikeit.weather.infrastructure;

import com.bikeit.shared.domain.GeoPoint;
import com.bikeit.weather.domain.WeatherSnapshot;
import java.time.LocalDateTime;

public interface WeatherProviderClient {

    WeatherSnapshot fetch(GeoPoint point, LocalDateTime observedAt);
}
