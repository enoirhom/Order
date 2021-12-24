package com.switchfully.user;

public interface Authorizable {
    boolean isAuthorized(Role expectedRole);
    boolean isAuthorized(Role expectedRole, String userId);
}
