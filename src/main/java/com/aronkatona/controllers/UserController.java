package com.aronkatona.controllers;


import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aronkatona.model.user.User;
import com.aronkatona.server.ServerRefresher;
import com.aronkatona.service.Manager;
import com.aronkatona.service.Manager;

@Controller
public class UserController {
	

	@RequestMapping(value = "/")
	public String welcome(Locale locale, Model model) {	
		//new ServerRefresher().start();
		return "welcome";
	}
	
	//bereggelt embert felvenni
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String afterSignUp(Model model,@RequestParam Map<String,String> reqPar){
		String user = reqPar.get("user");
		Manager manager = new Manager();
		
		for(User u: manager.getUsers()){
			if(u.getName().equals(user)){
				model.addAttribute("notSuccessSignUp","notSuccessSignUp");
				return "welcome";
			}
		}
		
		manager.getUsers().add(new User(user));

		model.addAttribute("successSignUp","successSignUp");
		return "welcome";
	}
	
	@RequestMapping(value="/signup")
	public String home(Model model){
		return "signup";
	}
	
	@RequestMapping(value="/logout")
	public String logout(Model model,HttpSession session){
		
		
		String nameOfUser = (String) session.getAttribute("nameOfUser");
		System.out.println(session.getAttribute("nameOfUser"));
		session.setAttribute("nameOfUser", "");
		System.out.println(session.getAttribute("nameOfUser"));
		
		return "welcome";
	}
	
}
