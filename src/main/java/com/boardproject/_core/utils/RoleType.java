package com.boardproject._core.utils;

public enum RoleType {
    GENERAL("새싹 회원"),
    VIP("우수 회원");

    private String label;

    RoleType(String label) {
        this.label = label;
    }
}
