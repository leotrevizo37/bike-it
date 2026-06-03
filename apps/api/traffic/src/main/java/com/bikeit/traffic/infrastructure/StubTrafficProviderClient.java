package com.bikeit.traffic.infrastructure;

import com.bikeit.shared.domain.GeoPoint;
import com.bikeit.traffic.domain.TrafficSnapshot;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class StubTrafficProviderClient implements TrafficProviderClient {

    @Override
    public TrafficSnapshot fetch(GeoPoint origin, GeoPoint destination, LocalDateTime departureAt) {
        int hour = departureAt.getHour();
        if (hour >= 17) {
            return new TrafficSnapshot(56, 31, 42, 1.81);
        }
        return new TrafficSnapshot(33, 27, 39, 1.22);
    }
}
