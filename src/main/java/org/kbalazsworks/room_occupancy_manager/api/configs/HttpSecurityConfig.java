package org.kbalazsworks.room_occupancy_manager.api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class HttpSecurityConfig
{
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception
    {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(r -> {
                r.requestMatchers(HttpMethod.GET, "/api-docs").permitAll();
                r.requestMatchers(HttpMethod.GET, "/api-docs.yaml").permitAll();
                r.requestMatchers(HttpMethod.GET, "/api-docs/**").permitAll();

                r.requestMatchers(HttpMethod.GET, "/health/200ok").permitAll();

                r.requestMatchers(HttpMethod.POST, "/room-manager/book").permitAll();
                r.anyRequest().authenticated();
            });

        return http.build();
    }
}
