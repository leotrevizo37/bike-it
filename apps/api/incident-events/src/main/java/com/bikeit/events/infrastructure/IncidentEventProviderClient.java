package com.bikeit.events.infrastructure;

import com.bikeit.events.domain.IncidentSignal;
import com.bikeit.shared.domain.GeoPoint;
import java.time.LocalDateTime;

public interface IncidentEventProviderClient {

    IncidentSignal fetch(GeoPoint origin, GeoPoint destination, LocalDateTime departureAt);
}
