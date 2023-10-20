package com.ms.training.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:5173/");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // ...
//                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1", "/training-maintenance/**")
                .permitAll()
        ;
        http.cors().configurationSource(corsConfigurationSource()).and();
    }

//    @Override
//    protected void registerAuthentication(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//        authManagerBuilder
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("ADMIN");
//    }
}
