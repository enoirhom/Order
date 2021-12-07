package com.switchfully;

public interface Authorizable {
    boolean isAuthorized(Role expectedRole);
}
