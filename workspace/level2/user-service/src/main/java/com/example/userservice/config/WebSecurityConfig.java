package com.example.userservice.config;

import com.example.userservice.filter.AuthenticationFilter;
import com.example.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    // 모든 요청에 대한 인증처리를 할 것이다. 단 IP에 의한 허용은 있다.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //http.authorizeRequests().antMatchers("/users/**").permitAll();
        http.authorizeRequests().antMatchers("/**")
//            .hasIpAddress("192.168.0.112") // <- 각자의 IP로 변경필요
            .hasIpAddress("172.20.10.7") // <- 각자의 IP로 변경필요
            .and()
            .addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
//        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
//        authenticationFilter.setAuthenticationManager(authenticationManager());
        AuthenticationFilter authenticationFilter =
                new AuthenticationFilter(userService, env, authenticationManager());
        return authenticationFilter;
    }
}
