package com.exas.crm.config.security.oauth2.user;

import java.util.Map;
import java.util.logging.Logger;

import com.exas.crm.exception.OAuth2AuthenticationProcessingException;
import com.exas.crm.model.AuthProvider;

public class OAuth2UserInfoFactory {

	private static final Logger logger = Logger.getLogger(OAuth2UserInfoFactory.class.getName());
	
	public static OAuth2UserInfo getOAuth2UserInfo(Map<String, Object> attributes, String registrationId) {
		logger.info("OAuth2UserInfoFactory.getOAuth2UserInfo");
		if(registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.toString())) {
			logger.info("Selected Google as the Provider");
			return new GoogleOAuth2UserInfo(attributes);
		}
		else if(registrationId.equalsIgnoreCase(AuthProvider.FACEBOOK.name())) {
			logger.info("Selected Facebook as the Provider");
			return new FacebookOAuth2UserInfo(attributes);
		}
		else
			throw new OAuth2AuthenticationProcessingException(registrationId+": This provider is not supported");
			
	}
	

}
