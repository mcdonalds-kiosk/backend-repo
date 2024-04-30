package com.study.mcdonaldskiosk.constant;

public enum MemberRole {
    ADMIN(1),
    USER(2);

    private final int value;

    MemberRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MemberRole fromValue(int value) {
        for (MemberRole memberRole : MemberRole.values()) {
            if (memberRole.getValue() == value) {
                return memberRole;
            }
        }
        throw new IllegalArgumentException("Invalid value for MemberRole enum: " + value);
    }
}
