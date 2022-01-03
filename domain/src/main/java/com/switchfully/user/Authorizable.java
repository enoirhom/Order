package com.switchfully.user;

import java.util.UUID;

public interface Authorizable {
    boolean isAuthorized(Role expectedRole);
    boolean isAuthorized(Role expectedRole, UUID userId);
}
