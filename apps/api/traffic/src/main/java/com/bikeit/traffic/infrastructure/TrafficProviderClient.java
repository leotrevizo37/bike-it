package com.bikeit.traffic.infrastructure;

import com.bikeit.shared.domain.GeoPoint;
import com.bikeit.traffic.domain.TrafficSnapshot;
import java.time.LocalDateTime;

public interface TrafficProviderClient {

    TrafficSnapshot fetch(GeoPoint origin, GeoPoint destination, LocalDateTime departureAt);
}
