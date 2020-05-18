package com.whistleblower.app.security;

import com.whistleblower.app.repository.UserRepository;
import com.whistleblower.app.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.whistleblower.app.security.SecurityConstants.*;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;


    public WebSecurity(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login"))
        .and()
                .authorizeRequests()
                // Create new issue and user
                .antMatchers(HttpMethod.POST, CREATE_NEW_ISSUE).permitAll()
                //User controller
                .antMatchers(HttpMethod.GET, USER_URL_ROOT + GET_ALL_LAWYERS)
                .hasAuthority(ROLE_ADMIN)
                //Issue Controller
                .antMatchers(HttpMethod.POST, ISSUE_URL_ROOT + ASSIGN_ISSUE)
                .hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, ISSUE_URL_ROOT + GET_ALL_ISSUES_FOR_ADMIN)
                .hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, ISSUE_URL_ROOT + GET_ALL_ISSUES_FOR_LAWYER)
                .hasAuthority(ROLE_LAWYER)
                .antMatchers(HttpMethod.POST, ISSUE_URL_ROOT + CHANGE_ISSUE_STATUS)
                .hasAnyAuthority(ROLE_LAWYER, ROLE_ADMIN)
                //Postbox Controller
                .antMatchers(HttpMethod.POST, POSTBOX_URL_ROOT + POSTBOX_SEND_BY_LAWYER)
                .hasAuthority(ROLE_LAWYER)
                .antMatchers(HttpMethod.POST, POSTBOX_URL_ROOT + POSTBOX_REPLY_BY_USER)
                .hasAuthority(ROLE_USER)
                .antMatchers(HttpMethod.POST, POSTBOX_URL_ROOT + POSTBOX_GET_ALL_FOR_LAWYER)
                .hasAuthority(ROLE_LAWYER)
                .antMatchers(HttpMethod.POST, POSTBOX_URL_ROOT + POSTBOX_GET_ALL_FOR_USER)
                .hasAuthority(ROLE_USER)
                //Category Controller
                .antMatchers(HttpMethod.POST, CATEGORY_URL_ROOT + "/**")
                .hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, CATEGORY_URL_ROOT + GET_CATEGORIES)
                .permitAll()

                // IssueStatus Controller
                .antMatchers(HttpMethod.GET, ISSUE_STATUS_URL_ROOT + GET_ALL_ISSUE_STATUS)
                .hasAnyAuthority(ROLE_ADMIN,ROLE_LAWYER)


              //  .anyRequest().permitAll()
               // .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(userRepository, authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }




}
