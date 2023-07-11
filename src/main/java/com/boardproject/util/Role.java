package com.boardproject.util;

public enum Role {
    GENERAL("새싹 회원"),
    VIP("우수 회원");

    private String label;

    Role(String label) {
        this.label = label;
    }
}
