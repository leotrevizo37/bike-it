package com.bikeit.commute.application;

import com.bikeit.commute.domain.CommuteProfile;
import com.bikeit.identity.application.IdentityService;
import com.bikeit.shared.domain.GeoPoint;
import org.springframework.stereotype.Service;

@Service
public class CommuteProfileService {

    private final IdentityService identityService;

    public CommuteProfileService(IdentityService identityService) {
        this.identityService = identityService;
    }

    public CommuteProfile loadProfile(String userId) {
        identityService.loadUser(userId);
        return new CommuteProfile(
                userId,
                "Home",
                "Work",
                new GeoPoint(19.4326, -99.1332),
                new GeoPoint(19.4270, -99.1677),
                10,
                15);
    }
}
