package pl.lukasik.shop.security.model;

import lombok.Getter;

@Getter
public enum UserRole {

    ROLE_ADMIN("ADMIN"),
    ROLE_CUSTOMER("CUSTOMER");

    private String value;
    UserRole(String value) {
        this.value = value;
    }


}
