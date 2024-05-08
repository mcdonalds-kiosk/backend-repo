package com.study.mcdonaldskiosk.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TossPaymentConfig {
  @Value("${payment.toss.client_key}")
  private String clientKey;

  @Value("${payment.toss.secret_key}")
  private String secretKey;

  @Value("${payment.toss.success_url}")
  private String successUrl;

  @Value("${payment.toss.fail_url}")
  private String failUrl;
  public static final String URL = "https://api.tosspayments.com/v1/payments/";

}
