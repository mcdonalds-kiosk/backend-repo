package com.study.mcdonaldskiosk.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value; // Make sure this is the import
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TossPaymentConfig {
    @Value("${payment.toss.test_client_api_key}")
    private String testClientApiKey;

    @Value("${payment.toss.test_secret_api_key}")
    private String testSecretKey;

    @Value("${payment.toss.success_url}")
    private String successUrl;

    @Value("${payment.toss.fail_url}")
    private String failUrl;

    public static final String URL = "https://api.tosspayments.com/v1/payments/";
}

