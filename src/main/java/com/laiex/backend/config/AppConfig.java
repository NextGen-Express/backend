package com.laiex.backend.config;

import com.google.maps.GeoApiContext;
import com.laiex.backend.db.StationRepository;
import com.laiex.backend.db.entity.StationEntity;
import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class AppConfig {
    private final StationRepository stationRepository;

    // read Stripe api key from application.yml
    @Value("${stripe.api-key}")
    private String stripeApiKey;
    private static List<StationEntity> stationList;
    public AppConfig(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @PostConstruct
    public void init() {
        // create three default stations
        stationRepository.insertStation("San Francisco", "236 W Portal Ave, San Francisco, CA 94127", 37.73874747540093, -122.4683741639298, StationEntity.status.available);
        stationRepository.insertStation("San Francisco", "3145 Geary Blvd, San Francisco, CA 94118", 37.787047187280024, -122.45343392870515, StationEntity.status.available);
        stationRepository.insertStation("San Francisco", "1198 S Van Ness Ave, San Francisco, CA 94110", 37.75872860740814, -122.41489106937792, StationEntity.status.available);
        stationList = stationRepository.findAll();
    }

    public static List<StationEntity> getStationList() {
        return stationList;
    }

    @Bean
    public void stripe() {
        Stripe.apiKey = stripeApiKey;
    }

    // read Google Map key from application.yml
    @Value("${googleMap.apiKey}")
    private String googleMapApiKey;

    @Bean
    public GeoApiContext geoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(googleMapApiKey)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/index.html", "/*.json", "/*.png", "/static/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login", "/register", "/logout").permitAll()
                        .anyRequest().authenticated()
            )
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .formLogin()
                .successHandler((req, res, auth) -> res.setStatus(HttpStatus.NO_CONTENT.value()))
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT));

        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
