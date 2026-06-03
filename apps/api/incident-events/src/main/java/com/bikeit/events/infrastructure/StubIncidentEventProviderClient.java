package com.bikeit.events.infrastructure;

import com.bikeit.events.domain.IncidentSignal;
import com.bikeit.shared.domain.GeoPoint;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class StubIncidentEventProviderClient implements IncidentEventProviderClient {

    @Override
    public IncidentSignal fetch(GeoPoint origin, GeoPoint destination, LocalDateTime departureAt) {
        if (departureAt.getHour() >= 18) {
            return new IncidentSignal(true, 2, "Large event near the return corridor");
        }
        return new IncidentSignal(false, 0, "No abnormal incident pressure");
    }
}
