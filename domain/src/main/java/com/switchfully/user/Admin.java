package com.switchfully.user;

import java.util.UUID;

public class Admin implements Authorizable {
    private final String email;

    public Admin(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAuthorized(Role role) {
        return true;
    }

    @Override
    public boolean isAuthorized(Role expectedRole, UUID userId) {
        return true;
    }
}
