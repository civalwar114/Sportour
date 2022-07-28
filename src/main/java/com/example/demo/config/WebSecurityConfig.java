package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@Configuration
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors() // WebMvcConfig占쏙옙占쏙옙 占싱뱄옙 占쏙옙占쏙옙占쏙옙占쏙옙占실뤄옙 占썩본 cors 占쏙옙占쏙옙.
                .and()
                .csrf()// csrf占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占� 占쏙옙占쏙옙占실뤄옙 disable
                .disable()
                .httpBasic()// token占쏙옙 占쏙옙占쏙옙球퓐占� basic 占쏙옙占쏙옙 disable
                .disable()
                .sessionManagement()  // session 占쏙옙占쏙옙占� 占싣댐옙占쏙옙 占쏙옙占쏙옙
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // /占쏙옙 /auth/** 占쏙옙灌占� 占쏙옙占쏙옙 占쏙옙占쌔듸옙 占쏙옙.
                .antMatchers("/", "/auth/**","/saram/**","/travinfo/**","/order/**","/fileup").permitAll() //占쏙옙占쏙옙占쏙옙 占쏙옙占� 占쏙옙灌占� 占싸깍옙占쏙옙占쏙옙 占쏙옙占쌔듸옙 占쏙옙占�
                .anyRequest() // /占쏙옙 /auth/**占싱울옙占쏙옙 占쏙옙占� 占쏙옙灌占� 占쏙옙占쏙옙 占쌔야듸옙.
                .authenticated();

        // filter 占쏙옙占�.
        // 占쏙옙 占쏙옙占쏙옙占쏙옙트占쏙옙占쏙옙
        // CorsFilter 占쏙옙占쏙옙占쏙옙 占식울옙
        // jwtAuthenticationFilter 占쏙옙占쏙옙占싼댐옙.
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
        return http.build();
    }
    // 2022 - 07 -13 占쏙옙占쏙옙占쏙옙占쏙옙트 aws 책 占쏙옙占쎈에占쏙옙 占싫되댐옙 占싸븝옙占쏙옙 占쏙옙慣占� 占쏙옙占쏙옙 占쌜쇽옙占쌔쇽옙 占쏙옙占쏙옙
    //占쏙옙占쏙옙占쏙옙 占쏙옙큐占쏙옙티 占쏙옙占쏙옙占쏙옙트占쏙옙 占쏙옙占쏙옙占쏙옙 占싫되댐옙 占싸븝옙占쏙옙 @Bean 처占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 占쌔곤옙
    //占싸깍옙占쏙옙 占쏙옙占쏙옙占쏙옙 basic.disable占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
    //占쏙옙占쏙옙 占쌍쇽옙처占쏙옙 permiaAll 占쏙옙 占쏙옙占쏙옙占쏙옙 占십울옙 占쏙옙占쏙옙 占쏙옙占쏙옙
}

