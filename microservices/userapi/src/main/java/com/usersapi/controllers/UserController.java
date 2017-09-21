package com.usersapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.usersapi.entity.User;
import com.usersapi.services.IUsersService;

@Controller
@RequestMapping("api/users/")
public class UserController {

	@Autowired
	private IUsersService userService;
	
	@RequestMapping(value = "{username}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username){
		User user = userService.getUserByUserName(username);
		if(user == null){
			return new ResponseEntity<User>(user, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> userList = userService.getAllUsers();
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user, UriComponentsBuilder builder) {
		boolean isAdded = userService.addUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if(!isAdded){
			return new ResponseEntity<String>("{\"status\": 400, \"message\": \"Bad Request\"}", headers, HttpStatus.BAD_REQUEST);
		}		
		String retString = "{\"username\": \"" + user.getUsername() + "\", \"password\": \"" + user.getPassword() + "\"}";
		System.out.println(retString);
		return new ResponseEntity<String>(retString, headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "updateUser", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		userService.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
