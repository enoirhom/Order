package com.switchfully.security;

import com.switchfully.Admin;
import com.switchfully.Authorizable;
import com.switchfully.CustomerRepository;
import com.switchfully.Role;
import com.switchfully.security.exception.UnauthorizedAccessException;
import com.switchfully.security.exception.UnknownUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

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

    private Authorizable findAuthorizableWith(String authorization) {
        if (authorization == null) {
            throw new UnknownUserException("No authorization provided");
        }

        String decodeUsernamePassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodeUsernamePassword.substring(0, decodeUsernamePassword.indexOf(":"));

        if (email.equals(admin.getEmail())) {
            return admin;
        }

        if (customerRepository.getCustomerByEmail(email) != null) {
            return customerRepository.getCustomerByEmail(email);
        }

        throw new UnknownUserException("Email " + email + " is invalid.");
    }

    private void validateAccess(Authorizable authorizable, Role role) {
        if (!authorizable.isAuthorized(role)) {
            throw new UnauthorizedAccessException("Access to functionality denied.");
        }
    }

}