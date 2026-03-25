package com.stellantis.operix.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "operix.jwt")
@Getter
@Setter
public class JwtConfig {
    private String secret;
    private long expiration;       // 15 min
    private long refreshExpiration; // 7 jours
}