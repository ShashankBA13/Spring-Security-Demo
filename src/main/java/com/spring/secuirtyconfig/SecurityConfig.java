package com.spring.secuirtyconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("SecurityConfig.configure for AuthenticationBuilder");
        auth.
                inMemoryAuthentication().
                withUser("Shashank").
                password("1234").
                roles("ADMIN", "USER") ;
        auth.inMemoryAuthentication().
                withUser("Test").
                password("12").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig.configure");
        http.csrf().disable();
        http.
                authorizeRequests().
                antMatchers("/admin/**").
                hasAnyRole("ADMIN").
                antMatchers("/home/**").hasAnyRole("USER").
                antMatchers("/test/**").permitAll().
                anyRequest().
                fullyAuthenticated().
                and().
                formLogin().
                and().
                httpBasic();
    }

}