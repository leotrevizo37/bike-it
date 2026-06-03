package com.bikeit.events.application;

import com.bikeit.events.domain.IncidentSignal;
import com.bikeit.events.infrastructure.IncidentEventProviderClient;
import com.bikeit.shared.domain.GeoPoint;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class IncidentEventService {

    private final IncidentEventProviderClient providerClient;

    public IncidentEventService(IncidentEventProviderClient providerClient) {
        this.providerClient = providerClient;
    }

    public IncidentSignal getSignals(GeoPoint origin, GeoPoint destination, LocalDateTime departureAt) {
        return providerClient.fetch(origin, destination, departureAt);
    }
}
