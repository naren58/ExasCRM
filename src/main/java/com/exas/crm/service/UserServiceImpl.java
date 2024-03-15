package com.exas.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.exas.crm.exception.UserNotFoundException;
import com.exas.crm.model.User;
import com.exas.crm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;

	@Override
	public User loadUserByEmail(String email) {

		Optional<User> user = userRepo.findByEmail(email);

		return user.orElseThrow(
				() -> new UserNotFoundException("User with email Id:" +email+" not found in db"));
	}

	@Override
	public List<User> loadAllAuthenticatedUserData() {
		return userRepo.findAll();
	}

}
