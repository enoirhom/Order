package com.switchfully.user;

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
    public boolean isAuthorized(Role expectedRole, String userId) {
        return true;
    }
}
