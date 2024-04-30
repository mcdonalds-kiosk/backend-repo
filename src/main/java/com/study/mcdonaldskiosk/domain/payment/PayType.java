package com.study.mcdonaldskiosk.domain.payment;

public enum PayType {
    CARD("카드"),
    BANK_TRANSFER("계좌 이체"),
    VIRTUAL_ACCOUNT("가상 계좌"),
    MOBILE_PHONE("휴대폰");

    private final String description;

    PayType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
