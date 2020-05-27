package com.whistleblower.app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whistleblower.app.entity.UserEntity;
import com.whistleblower.app.modelDto.LoginResponse;
import com.whistleblower.app.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import static com.whistleblower.app.security.SecurityConstants.ROLE_USER;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

//    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserEntity creds = new ObjectMapper()
                    .readValue(request.getInputStream(), UserEntity.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) throws IOException {
        var user = ((User) authentication.getPrincipal());
        LoginResponse loginResponse = new LoginResponse();
        var entityUser = userRepository.findByUsernameIgnoreCase(((User) authentication.getPrincipal()).getUsername());
        var roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        var signingKey = SecurityConstants.JWT_SECRET.getBytes();

       final int TEN_DAYS = 864000000;
       final int FIFTEEN_MINUTES = 900000;
       var timeInMillis = TEN_DAYS;

       if(entityUser.getRole().equals(ROLE_USER)) timeInMillis = FIFTEEN_MINUTES;

        var token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + timeInMillis))
                .claim("rol", roles)
                .compact();

        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();


        loginResponse.setLastLogin(entityUser.getLastLogin());
        loginResponse.setToken(token);
        loginResponse.setPath("/" + entityUser.getRole().toLowerCase());
        loginResponse.setConsent(entityUser.isConsent());
        response.getWriter().write(mapper.writeValueAsString(loginResponse));

        entityUser.setLastLogin(new Date());
        userRepository.save(entityUser);
    }


}
