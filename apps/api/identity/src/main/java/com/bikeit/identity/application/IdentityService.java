package com.bikeit.identity.application;

import com.bikeit.identity.domain.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class IdentityService {

    public UserAccount loadUser(String userId) {
        return new UserAccount(userId, "Demo Rider", true);
    }
}
