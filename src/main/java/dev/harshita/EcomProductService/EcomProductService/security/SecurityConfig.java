package dev.harshita.EcomProductService.EcomProductService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/product/admin/*").hasAuthority("ADMIN")
                                .requestMatchers("/category/admin/*").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
//                        .anyRequest().authenticated()
                )
                .cors().disable()
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> {
                                                jwtConfigurer.jwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
                                            }));
        return http.build();
    }


}
