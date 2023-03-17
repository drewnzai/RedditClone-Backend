package com.drew.Reddit;

import com.drew.Reddit.services.UserDetailsServiceImpl;
import com.drew.Reddit.utils.JwtAuthenticationEntryPoint;
import com.drew.Reddit.utils.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthenticationEntryPoint entryPoint;

    private final JwtFilter jwtFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .cors()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // add filter that will handle the verification of JW Token
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/subreddit")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/posts")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/posts/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/comments")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/comments/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/trending")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**")
                .permitAll()
                .antMatchers("/v2/api-docs",
                                "/configuration/ui",
                                "/swagger-resources/**",
                                "/configuration/security",
                                "/swagger-ui.html",
                                "/webjars/**")
                        .permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
