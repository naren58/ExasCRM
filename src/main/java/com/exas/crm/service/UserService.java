package com.exas.crm.service;

import java.util.List;


import com.exas.crm.exception.UserNotFoundException;
import com.exas.crm.model.User;

public interface UserService {

	List<User> loadAllAuthenticatedUserData();

	User loadUserByEmail(String email) throws UserNotFoundException;

}
