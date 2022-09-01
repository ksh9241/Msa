package com.example.apigatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
/**
 * @Description 부트 2.5부터 위 어노테이션을 추가하여 csrf 설정을 해줘야 한다.
 * */
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity security) {
        return security.csrf()
                        .disable()
                        .build();
    }
}
