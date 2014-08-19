package com.aronkatona.controllers;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aronkatona.model.troops.Archer;
import com.aronkatona.model.troops.Troop;
import com.aronkatona.model.troops.Warrior;
import com.aronkatona.model.user.User;
import com.aronkatona.service.Manager;

@Controller
public class MapController {

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String welcome(Model model, @RequestParam Map<String, String> reqPar) {
		String userName = reqPar.get("user");
		Manager manager = new Manager();
		System.out.println(manager.getMap().getSize());
		boolean success = false;
		User user = null;
		for (User u : manager.getUsers()) {
			if (u.getName().equals(userName)) {
				System.out.println("sikeres bejelentkezés");
				user = u;
				success = true;
				break;
			}
		}

		if (success) {
			for (int i = 0; i < manager.getMap().getSize(); ++i) {
				for (int j = 0; j < manager.getMap().getSize(); ++j) {
					if (manager.getMap().getFields(i, j).getOasis() != null
							&& user.getX() == -1 && user.getY() == -1) {
						manager.getMap().getFields(i, j).setUser(user);
						user.setX(i);
						user.setY(j);
						i = manager.getMap().getSize();
						break;
					}
				}
			}

			model.addAttribute("X", user.getX());
			model.addAttribute("Y", user.getY());
			model.addAttribute("userName", userName);
			model.addAttribute("mapSize", manager.getMap().getSize());
			model.addAttribute("fields", manager.getMap().getFields());
			return "map";
		}

		System.out.println("nincs ilyen user");
		return "redirect:/";
	}

	@RequestMapping(value = "/backToMap.{i}.{j}.{userName}")
	public String backToMap(Model model, @PathVariable int i,
			@PathVariable int j, @PathVariable String userName) {
		Manager manager = new Manager();
		User user = null;
		for (User u : manager.getUsers()) {
			if (u.getName().equals(userName))
				user = u;
		}

		model.addAttribute("X", user.getX());
		model.addAttribute("Y", user.getY());
		model.addAttribute("userName", user.getName());
		model.addAttribute("mapSize", manager.getMap().getSize());
		model.addAttribute("fields", manager.getMap().getFields());
		return "map";
	}

	@RequestMapping(value = "/onOasis.{i}.{j}.{userName}")
	public String onOasis(Model model, @PathVariable int i,
			@PathVariable int j, @PathVariable String userName) {
		Manager manager = new Manager();

		int numberOfWarriors = 0;
		int numberOfArchers = 0;
		for (Troop t : manager.getMap().getFields(i, j).getOasis().getTroops()) {
			if (t instanceof Warrior)
				numberOfWarriors++;
			if (t instanceof Archer)
				numberOfArchers++;
		}

		model.addAttribute("userName", userName);
		model.addAttribute("gold", manager.getMap().getFields(i, j).getOasis()
				.getGold());
		model.addAttribute("numberOfWarriors", numberOfWarriors);
		model.addAttribute("numberOfArchers", numberOfArchers);
		model.addAttribute("X", i);
		model.addAttribute("Y", j);
		return "onOasis";
	}

	@RequestMapping(value = "/onLucky.{i}.{j}.{userName}")
	public String onLucky(Model model, @PathVariable int i,
			@PathVariable int j, @PathVariable String userName) {
		Manager manager = new Manager();
		model.addAttribute("userName", userName);
		model.addAttribute("gold", manager.getMap().getFields(i, j).getLucky()
				.getGold());
		model.addAttribute("X", i);
		model.addAttribute("Y", j);
		return "onLucky";
	}

	@RequestMapping(value = "/inVillage.{i}.{j}.{userName}")
	public String inVillage(Model model, @PathVariable int i,
			@PathVariable int j, @PathVariable String userName) {
		Manager manager = new Manager();

		int numberOfWarriors = 0;
		int numberOfArchers = 0;
		int numberOfWarriorsOnWay = 0;
		int numberOfArchersOnWay = 0;
		
		User user = null;
		for(User u: manager.getUsers()){
			if(u.getName().equals(userName)) user = u;
		}
		System.out.println(new Date());
		System.out.println("------------------------------");
		for(Troop t: user.getTroops()){
			/*if(t.isOnWay() && t.getDate().after(new Date())){
				t.setOnWay(false);
			}*/
			if(!(t.isOnWay() && t.getDate().after(new Date()))){
				t.setOnWay(false);
				t.setDateToNull();
			} else {
				
			}
		}
		
		for (Troop t : user.getTroops()) {			
			if (t instanceof Warrior && !t.isOnWay())	numberOfWarriors++;
			else if (t instanceof Archer && !t.isOnWay()) numberOfArchers++;
			else if (t instanceof Warrior && t.isOnWay())	numberOfWarriorsOnWay++;
			else if (t instanceof Archer && t.isOnWay()) numberOfArchersOnWay++;
		}

		model.addAttribute("userName", userName);
		model.addAttribute("X", i);
		model.addAttribute("Y", j);
		model.addAttribute("gold",user.getGold());
		model.addAttribute("numberOfWarriors", numberOfWarriors);
		model.addAttribute("numberOfArchers", numberOfArchers);
		model.addAttribute("numberOfWarriorsOnWay", numberOfWarriorsOnWay);
		model.addAttribute("numberOfArchersOnWay", numberOfArchersOnWay);
		
		return "inVillage";
	}

	@RequestMapping(value = "/buyTroops.{i}.{j}.{userName}")
	public String buyTroops(Model model, @PathVariable int i,
			@PathVariable int j, @PathVariable String userName) {
		model.addAttribute("userName", userName);
		model.addAttribute("X", i);
		model.addAttribute("Y", j);
		return "buyTroops";
	}

	@RequestMapping(value = "/boughtTroops.{i}.{j}.{userName}", method = RequestMethod.GET)
	public String boughtTroops(Model model, @PathVariable int i,
			@PathVariable int j,
			@RequestParam("numberOfWarriors") int numberOfWarriors,
			@RequestParam("numberOfArchers") int numberOfArchers,
			@PathVariable("userName") String userName) {
		Manager manager = new Manager();
		for (int w = 0; w < numberOfWarriors; ++w)
			manager.getMap().getFields(i, j).getUser().addTroop(new Warrior());
		for (int a = 0; a < numberOfArchers; ++a)
			manager.getMap().getFields(i, j).getUser().addTroop(new Archer());

		return "redirect:/inVillage.{i}.{j}.{userName}";
	}

	@RequestMapping(value = "/sendUnit.{i}.{j}.{userName}")
	public String sendUnit(Model model, @PathVariable int i,
						   @PathVariable int j, @PathVariable String userName) {
		model.addAttribute("userName", userName);
		model.addAttribute("X", i);
		model.addAttribute("Y", j);
		return "sendUnit";
	}
	
	@RequestMapping(value="/sentUnit.{i}.{j}.{userName}", method = RequestMethod.GET)
	public String sentUnit(Model model, @PathVariable int i,
			@PathVariable int j,
			@RequestParam("numberOfWarriors") int numberOfWarriors,
			@RequestParam("numberOfArchers") int numberOfArchers,
			@PathVariable("userName") String userName){
		
		Manager manager = new Manager();
		User user = null;
		for(User u: manager.getUsers()){
			if(u.getName().equals(userName)) user = u;
		}
		
		System.out.println(user.getX() +"," + user.getY());
		
		System.out.println(i + "," + j);
		
		for(Troop t: user.getTroops()){
			if(!t.isOnWay() && t instanceof Warrior && numberOfWarriors!=0){
				t.setOnWay(true);
				t.setDate(user.getX(),user.getY(),i,j);
				numberOfWarriors--;
			}
			else if(!t.isOnWay() && t instanceof Archer && numberOfArchers!=0){
				t.setOnWay(true);
				t.setDate(user.getX(),user.getY(),i,j);
				numberOfArchers--;
			}
		}
		
		
		//luckyba
		if(manager.getMap().getFields(i, j).getLucky() != null
		&& manager.getMap().getFields(i, j).getOasis() == null
		&& manager.getMap().getFields(i, j).getUser() == null){
			user.addGold(manager.getMap().getFields(i, j).getLucky().getGold());
			manager.getMap().getFields(i, j).getLucky().setGold(0);
		}
	
		
		//oasisba
		
		return "redirect:/inVillage.{i}.{j}.{userName}";
		
	}
}
