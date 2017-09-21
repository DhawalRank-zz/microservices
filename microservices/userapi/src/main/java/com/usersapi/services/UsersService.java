package com.usersapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usersapi.dao.IUsersDAO;
import com.usersapi.entity.User;

@Service
public class UsersService implements IUsersService {

	@Autowired
	private IUsersDAO usersDAO;
	
	@Override
	public List<User> getAllUsers() {
		return usersDAO.getAllUsers();
	}

	@Override
	public User getUserById(int id) {
		return usersDAO.getUserById(id);
	}

	@Override
	public synchronized boolean addUser(User user) {
		if(usersDAO.isUserPresent(user.getUsername())){
			return false;
		} else{
			usersDAO.addUser(user);
			return true;
		}		
	}

	@Override
	public User getUserByUserName(String username) {
		return usersDAO.getUserByUserName(username);
	};
	@Override
	public void updateUser(User user) {
		usersDAO.updateUser(user);
	}

	@Override
	public void deleteUser(int id) {
		usersDAO.deleteUser(id);
	}

}
