package com.switchfully.security;

import com.switchfully.user.Admin;
import com.switchfully.user.Authorizable;
import com.switchfully.customer.CustomerRepository;
import com.switchfully.user.Role;
import com.switchfully.exception.UnauthorizedAccessException;
import com.switchfully.exception.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class SecurityService {
    private final CustomerRepository customerRepository;
    private final Admin admin;

    @Autowired
    public SecurityService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.admin = new Admin("admin@order.com");
    }

    public void validate(String authorization, Role role) {
        Authorizable user = findAuthorizableWith(authorization);
        validateAccess(user, role);
    }

    public void validate(String authorization, Role role, String userId) {
        Authorizable user = findAuthorizableWith(authorization);
        validateAccess(user, role, userId);
    }

    private Authorizable findAuthorizableWith(String authorization) {
        if (authorization == null) {
            throw new UnknownUserException("No authorization provided");
        }

        String decodeUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodeUsernamePassword.substring(0, decodeUsernamePassword.indexOf(":"));

        if (email.equals(admin.getEmail())) {
            return admin;
        }

        if (customerRepository.findCustomerByEmail(email).isPresent()) {
            return customerRepository.findCustomerByEmail(email).get();
        }

        throw new UnknownUserException("Email " + email + " is invalid.");
    }

    private void validateAccess(Authorizable authorizable, Role role) {
        if (!authorizable.isAuthorized(role)) {
            throw new UnauthorizedAccessException("Access to functionality denied.");
        }
    }

    private void validateAccess(Authorizable authorizable, Role role, String userId) {
        if (!authorizable.isAuthorized(role, UUID.fromString(userId))) {
            throw new UnauthorizedAccessException("Access to functionality denied.");
        }
    }
}
