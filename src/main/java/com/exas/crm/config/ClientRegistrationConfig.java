package com.exas.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Configuration
public class ClientRegistrationConfig {
	
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		return new InMemoryClientRegistrationRepository(this.googleClientRegistration(),this.facebookClientRegistration());
	}
	

	private ClientRegistration googleClientRegistration() {
		return ClientRegistration.withRegistrationId("google")
				.clientId("1038290133762-aoi2ic60efu8de71c8o6oefkqm7gvuot.apps.googleusercontent.com")
				.clientSecret("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")
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri("http://localhost:8080/login/oauth2/code/{registrationId}")
				.scope("openid", "profile", "email", "address","phone")
				.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
				.tokenUri("https://www.googleapis.com/oauth2/v4/token")
				.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
				.userNameAttributeName(IdTokenClaimNames.SUB).jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
				.clientName("crm-google-login").build();
	}
	
	private ClientRegistration facebookClientRegistration() {
		return ClientRegistration.withRegistrationId("facebook")
			.clientId("368660299401945")
			.clientSecret("0e8c1cf9221a193582f44751b3abb806")
			.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
			.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
			.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
			.scope("email", "public_profile")
			.authorizationUri("https://www.facebook.com/dialog/oauth")
			.tokenUri("https://graph.facebook.com/v9.0/oauth/access_token")
			.userInfoUri("https://graph.facebook.com/me")
			.userNameAttributeName("id")
			.clientName("crm-facebook-login")
			.build();
	}

}
