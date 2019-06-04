package ua.petproject.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    public static final String ADMIN_ACCESS = "admin/";

    @Override
    public String getAuthority() {
        return name();
    }
}
