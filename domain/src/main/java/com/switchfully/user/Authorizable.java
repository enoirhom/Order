package com.switchfully.user;

public interface Authorizable {
    boolean isAuthorized(Role expectedRole);
}
