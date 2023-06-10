package com.md.ReactSpringbootWithNeo4j.config;

import com.md.ReactSpringbootWithNeo4j.services.NeoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final NeoUserDetailService neoUserDetailService;

    @Autowired
    public SecurityConfig(NeoUserDetailService neoUserDetailService) {
        this.neoUserDetailService = neoUserDetailService;
    }

    /**
     * Configures and returns a SecurityFilterChain instance that defines the security policy for the application.
     * The policy is stateless, meaning it does not use HTTP sessions, and uses HTTP Basic authentication.
     * CSRF protection is disabled, and CORS is enabled with default settings.
     * The policy requires authentication for requests to "api/v1/auth/me", while all other requests are permitted without authentication.
     * User details are loaded using the neoUserDetailService (username)
     * <p>
     * Password will be loaded from User class, which is inside model packages.
     * The User class implements UserDetails interface which then has getter methods for username and passwords
     *
     * @param httpSecurity a HttpSecurity instance used to build the SecurityFilterChain
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs during the creation of the SecurityFilterChain
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                // since we are creating API, the authentication has to be stateless (Not to hold any session data)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(
                                "api/v1/auth/me"
                        ).authenticated()
                        .anyRequest().permitAll()
                )
                .userDetailsService(neoUserDetailService) // This service is used to load user-specific data during security checks (username in our case)
                .httpBasic(Customizer.withDefaults()) // This enables HTTP Basic authentication with default settings.
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // React ports
        // TODO: make sure that the origin list comes from an env file
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://127.0.0.1:3000"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Request-Type", "Content-Type"));
        corsConfiguration.setExposedHeaders(Arrays.asList("X-Get-Header"));
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
