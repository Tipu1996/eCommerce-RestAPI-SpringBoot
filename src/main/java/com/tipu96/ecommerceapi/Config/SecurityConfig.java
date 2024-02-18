package com.tipu96.ecommerceapi.Config;

import com.tipu96.ecommerceapi.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtService jwtService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET,"/api/v1/users").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/users/**").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/users/**").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/items/id").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/items/id").authenticated()
                        .requestMatchers(HttpMethod.POST,"/api/v1/items").authenticated()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());

        http.addFilterBefore(new JwtAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
//                        .requestMatchers(HttpMethod.GET,"").authenticated()