package com.usersapi.services;
import java.util.List;

import com.usersapi.entity.User;
public interface IUsersService {
	
	List<User> getAllUsers();
	User getUserById(int id);
	User getUserByUserName(String username);
	boolean addUser(User user);
	void updateUser(User user);
	void deleteUser(int id);
}
