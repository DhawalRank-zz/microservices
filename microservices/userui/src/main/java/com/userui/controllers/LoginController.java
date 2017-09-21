package com.userui.controllers;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(value="", method=RequestMethod.POST)
	public String isUserValid(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model) {
		String destinationUrl = "http://localhost:8181/api/users/" + username;
		HttpClient httpClient = new HttpClient();
		GetMethod url = new GetMethod(destinationUrl);
		String retVal = null;
		try {
			int statusCode = httpClient.executeMethod(url);
			if(statusCode == 200){
				BufferedReader rd = new BufferedReader(new InputStreamReader(url.getResponseBodyAsStream()));

				StringBuffer sb = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				JSONObject object = new JSONObject(sb.toString());
				String getPassword = object.optString("password", "");
				if(getPassword.equals(password)){
					retVal = "success";
					model.put("username", object.optString("username", ""));
				}
				else {
					retVal = "login";
					model.put("errorMessage", "Invalid Username or Password!!");
				}
			}
			else if(statusCode == 403){
				retVal = "login";
				model.put("errorMessage", "Invalid Username or password!!");
			}
			else{
				retVal = "login";
				model.put("errorMessage", "API responded with invalid status code of: " + statusCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			retVal = "login";
			model.put("errorMessage", "Connection to API Timed out.");
		}
		return retVal;
	}
}
