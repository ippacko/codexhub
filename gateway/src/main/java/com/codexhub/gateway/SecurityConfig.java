package com.codexhub.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(ex -> ex
                .pathMatchers("/auth/**", "/actuator/**").permitAll()
                .anyExchange().authenticated())
            .addFilterAt((exchange, chain) -> {
                String path = exchange.getRequest().getPath().value();
                if (path.startsWith("/auth/")) {
                    return chain.filter(exchange);
                }
                List<String> authz = exchange.getRequest().getHeaders().getOrEmpty("Authorization");
                if (authz.isEmpty() || !authz.get(0).startsWith("Bearer ")) {
                    return Mono.error(new RuntimeException("missing token"));
                }
                String token = authz.get(0).substring(7);
                try {
                    Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                        .build()
                        .parseClaimsJws(token);
                    return chain.filter(exchange);
                } catch (Exception e) {
                    return Mono.error(new RuntimeException("invalid token"));
                }
            }, ServerHttpSecurity.WebFilterChainServerWebExchangeMatcherOrder.AUTHENTICATION);
        return http.build();
    }
}
