package com.bikeit.traffic.application;

import com.bikeit.shared.domain.GeoPoint;
import com.bikeit.traffic.domain.TrafficSnapshot;
import com.bikeit.traffic.infrastructure.TrafficProviderClient;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class TrafficService {

    private final TrafficProviderClient providerClient;

    public TrafficService(TrafficProviderClient providerClient) {
        this.providerClient = providerClient;
    }

    public TrafficSnapshot getConditions(GeoPoint origin, GeoPoint destination, LocalDateTime departureAt) {
        return providerClient.fetch(origin, destination, departureAt);
    }
}
