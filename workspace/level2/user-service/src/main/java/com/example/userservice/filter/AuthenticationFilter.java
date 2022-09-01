package com.example.userservice.filter;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.RequestLogin;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Description 로그인 인증 및 JWT 생성
 * */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment env;

    public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.env = env;
        super.setAuthenticationManager(authenticationManager);
    }

    /** @Description 요청 들어온 로그인 정보를 토큰으로 만들어서 반환한다. */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin requestLogin =
                    new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getEmail(),
                            requestLogin.getPassword(),
                            new ArrayList<>()
                    ));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                      FilterChain chain, Authentication authResult) throws IOException, ServletException
    {
        String userName = ((User)authResult.getPrincipal()).getUsername();

        /* 유저 아이디 + JWT 알고리즘 값 + 토큰 시크릿 값을 합쳐서 하나의 값의 토큰을 생성한다.*/
        UserDto userDetails = userService.getUserDetailsByEmail(userName); String token = Jwts.builder()
            .setSubject(userDetails.getUserId())
            .setExpiration(new Date(System.currentTimeMillis() +
                    Long.parseLong(env.getProperty("token.expiration_time"))))
                        .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512,
                    env.getProperty("token.secret")) .compact();
        System.out.println("===> 생성된 토큰 " + token);

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());
    }
}
