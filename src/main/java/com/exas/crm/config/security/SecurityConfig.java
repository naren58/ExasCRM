package com.exas.crm.config.security;

import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.exas.crm.config.security.oauth2.CustomOAuth2User;


@Configuration
public class SecurityConfig {
	private static final Logger logger = Logger.getLogger(SecurityConfig.class.getName());

	private final CustomOAuth2User customOAuth2User;
	public SecurityConfig(CustomOAuth2User customOAuth2User) {
        this.customOAuth2User = customOAuth2User;
        logger.info("CustomOAuth2User bean injected successfully.");
    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/login").permitAll()
				.anyRequest().authenticated())
				.oauth2Login(oauth2 -> oauth2
						.loginPage("/login")
						.defaultSuccessUrl("/success", true)
						.userInfoEndpoint(userInfo -> userInfo
						        .userService(customOAuth2User)
						    ));
		return http.build();
	}

}
