package com.bikeit.traffic.domain;

public record TrafficSnapshot(
        int driveMinutes,
        int typicalDriveMinutes,
        int bikeMinutes,
        double congestionIndex) {
}
