package com.exas.crm.config.security.oauth2;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exas.crm.config.security.oauth2.user.OAuth2UserInfo;
import com.exas.crm.config.security.oauth2.user.OAuth2UserInfoFactory;
import com.exas.crm.exception.OAuth2AuthenticationProcessingException;
import com.exas.crm.model.AuthProvider;
import com.exas.crm.model.User;
import com.exas.crm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2User extends DefaultOAuth2UserService{

	private static final Logger logger = Logger.getLogger(CustomOAuth2User.class.getName());

	private final UserRepository userRepo;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oauth2Request) throws OAuth2AuthenticationException {
		logger.info("CustomOAuth2User.loadUser");
		OAuth2User oauth2User = super.loadUser(oauth2Request);
		try {
			processOAuth2User(oauth2User, oauth2Request);
		} catch (org.springframework.security.core.AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new OAuth2AuthenticationProcessingException(ex.getMessage(), ex.getCause());
		}
		return oauth2User;
	}

	/*
	 * Validates: Authenticated user has email id and process the userDetails
	 */
	private void processOAuth2User(OAuth2User oauth2User, OAuth2UserRequest oauth2Request) {
		logger.info("CustomOAuth2User.processOAuth2User");
		OAuth2UserInfo oauth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oauth2User.getAttributes(),
				oauth2Request.getClientRegistration().getRegistrationId().toString());
		if (oauth2UserInfo.getEmail().isBlank())
			throw new OAuth2AuthenticationProcessingException("Email should not be blank");
		Optional<User> optionalUser = userRepo.findByEmail(oauth2UserInfo.getEmail());

		if (optionalUser.isEmpty()) {
			registerNewUser(oauth2Request, oauth2UserInfo);
		} else {
			User existinguser = optionalUser.get();
			updateUser(existinguser, oauth2UserInfo);
		}

	}

	private void updateUser(User existingUser, OAuth2UserInfo oauth2UserInfo) {
		logger.info("CustomOAuth2User.updateUser");
		existingUser.setName(oauth2UserInfo.getName());
		existingUser.setImageUrl(oauth2UserInfo.getImageUrl());
		userRepo.save(existingUser);
	}

	private void registerNewUser(OAuth2UserRequest oauth2Request, OAuth2UserInfo oauth2UserInfo) {
		logger.info("CustomOAuth2User.registerNewUser");
		logger.info(oauth2UserInfo.getName().toLowerCase());
		
		
		User user = User.builder().name(oauth2UserInfo.getName()).id(oauth2UserInfo.getId())
				.email(oauth2UserInfo.getEmail()).imageUrl(oauth2UserInfo.getImageUrl())
				.provider(AuthProvider.valueOf(oauth2Request.getClientRegistration().getRegistrationId().toUpperCase())).build();
		logger.info(user.toString());
		userRepo.save(user);
	}

}
