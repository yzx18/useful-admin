//package com.yzx.gateway.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @className: SecurityConfig
// * @author: yzx
// * @date: 2025/8/7 20:54
// * @Version: 1.0
// * @description:
// */
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange(exchanges -> exchanges
//                        .pathMatchers("/oauth/**").permitAll()
//                        .pathMatchers("/actuator/**").permitAll()
//                        .pathMatchers("/api/users/register").permitAll()
//                        .pathMatchers("/api/users/login").permitAll()
//                        .anyExchange().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
//                        )
//                        .csrf().disable();
//
//        return http.build();
//    }
//
//    private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//        jwtConverter.setJwtGrantedAuthoritiesConverter(new JwtGrantedAuthoritiesConverter());
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtConverter);
//    }
//}