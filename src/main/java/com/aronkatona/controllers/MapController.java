package com.aronkatona.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aronkatona.model.user.User;
import com.aronkatona.service.Manager;

@Controller
public class MapController {

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String welcome(Model model,@RequestParam Map<String,String> reqPar) {	
		String user = reqPar.get("user");
		Manager manager = new Manager();
		
		boolean success = false;
		for(User u: manager.getUsers()){
			if (u.getName().equals(user)){
					System.out.println("sikeres bejelentkezés");
				success = true;
			}
		}
		
		if(success){
			model.addAttribute("mapSize",manager.getMap().getSize());
			return "map";
		}
		
		System.out.println("nincs ilyen user");
		return "redirect:/";
	}
}
