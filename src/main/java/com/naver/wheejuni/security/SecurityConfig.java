package com.naver.wheejuni.security;

import com.naver.wheejuni.security.filters.FilterSkipMatcher;
import com.naver.wheejuni.security.filters.JwtAuthorizeFilter;
import com.naver.wheejuni.security.filters.UserLoginFilter;
import com.naver.wheejuni.security.handlers.LoginSuccessHandler;
import com.naver.wheejuni.security.providers.JwtAuthenticationProvider;
import com.naver.wheejuni.security.providers.UserLoginProvider;
import io.netty.handler.codec.http.cors.CorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${crossorigin.front-uri}")
    private String crossRequestUri;

    @Autowired
    @Lazy
    private UserLoginProvider userLoginProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.userLoginProvider)
                .authenticationProvider(this.jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable();

        http
                .headers()
                .frameOptions()
                .disable();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .authorizeRequests()
                .antMatchers("/h2-console**").permitAll();

        http
                .addFilterBefore(userLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizeFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin(this.crossRequestUri);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", configuration);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

    protected UserLoginFilter userLoginFilter() throws Exception {
        UserLoginFilter filter = new UserLoginFilter("/login", this.loginSuccessHandler);
        filter.setAuthenticationManager(authenticationManager());

        return filter;
    }

    protected JwtAuthorizeFilter jwtAuthorizeFilter() throws Exception {
        FilterSkipMatcher matcher = new FilterSkipMatcher(Arrays.asList("/login", "/api/info/**", "/api/user/**"), "/api/v1");
        JwtAuthorizeFilter filter = new JwtAuthorizeFilter(matcher, tokenExtractor);
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }


}
