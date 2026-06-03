package com.bikeit.history.application;

import com.bikeit.history.domain.TripBaseline;
import com.bikeit.identity.application.IdentityService;
import org.springframework.stereotype.Service;

@Service
public class TripHistoryService {

    private final IdentityService identityService;

    public TripHistoryService(IdentityService identityService) {
        this.identityService = identityService;
    }

    public TripBaseline loadBaseline(String userId) {
        identityService.loadUser(userId);
        return new TripBaseline(82, 4, 41);
    }
}
