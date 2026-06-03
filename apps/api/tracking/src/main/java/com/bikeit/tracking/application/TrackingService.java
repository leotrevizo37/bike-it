package com.bikeit.tracking.application;

import com.bikeit.commute.application.CommuteProfileService;
import com.bikeit.identity.application.IdentityService;
import com.bikeit.tracking.domain.TrackingSessionPlan;
import org.springframework.stereotype.Service;

@Service
public class TrackingService {

    private final IdentityService identityService;
    private final CommuteProfileService commuteProfileService;

    public TrackingService(IdentityService identityService, CommuteProfileService commuteProfileService) {
        this.identityService = identityService;
        this.commuteProfileService = commuteProfileService;
    }

    public TrackingSessionPlan buildSessionPlan(String userId) {
        identityService.loadUser(userId);
        commuteProfileService.loadProfile(userId);
        return new TrackingSessionPlan(true, 15, true);
    }
}
