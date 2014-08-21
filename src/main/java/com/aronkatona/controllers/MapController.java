package com.aronkatona.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
		boolean success = false;
		User user = null;
		for (User u : manager.getUsers()) {
			if (u.getName().equals(userName)) {
				user = u;
				success = true;
				break;
			}
		}

		if (success) {
			for (int i = 0; i < manager.getMap().getSize(); ++i) {
				for (int j = 0; j < manager.getMap().getSize(); ++j) {
					if (manager.getMap().getFields(i, j).getOasis() != null	&& user.getX() == -1 && user.getY() == -1) {
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
		else{
			model.addAttribute("notSuccessLogin", "notSuccessLogin");
			return "welcome";
		}
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
		
		for(Troop t: user.getTroops()){
			if(!(t.isOnWay() && t.getAttackDate() != null && t.getAttackDate().after(new Date()))){
				t.setAttackDateToNull();
			} else {
				
			}
		}

		for(Troop t: user.getTroops()){
			if(!(t.isOnWay() && t.getBackDate().after(new Date()))){
				t.setOnWay(false);
				t.setBackDateToNull();
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
		model.addAttribute("test",100);
		
		Date actDate = new Date();
		List<Integer> attackTimes = new ArrayList<Integer>();
		for(Troop t: user.getTroops()){
			if(t.getAttackDate() != null){
				int difference = (t.getAttackDate().getHours() * 3600 + 
					   t.getAttackDate().getMinutes()*60   +
					   t.getAttackDate().getSeconds()) -
					   (actDate.getHours() * 3600 +
					   actDate.getMinutes() * 60 +
					   actDate.getSeconds() );
				attackTimes.add(difference);
			} else attackTimes.add(0);
		}
		model.addAttribute("attackTimes",attackTimes);

	
		List<Integer> backTimes = new ArrayList<Integer>();
		for(Troop t: user.getTroops()){
			if(t.getBackDate() != null){
				int difference = (t.getBackDate().getHours() * 3600 + 
					   t.getBackDate().getMinutes()*60   +
					   t.getBackDate().getSeconds()) -
					   (actDate.getHours() * 3600 +
					   actDate.getMinutes() * 60 +
					   actDate.getSeconds() );
				backTimes.add(difference);
			} else backTimes.add(0);
		}
		model.addAttribute("backTimes",backTimes);
		
		return "inVillage";
	}

	@RequestMapping(value = "/buyTroops.{i}.{j}.{userName}")
	public String buyTroops(Model model, @PathVariable int i,
			@PathVariable int j, @PathVariable String userName) {
		
		Manager manager = new Manager();
		User user = null;
		for(User u: manager.getUsers()){
			if(u.getName().equals(userName)) user = u;
		}
		
		model.addAttribute("userName", userName);
		model.addAttribute("X", i);
		model.addAttribute("Y", j);
		model.addAttribute("gold", user.getGold());
		model.addAttribute("priceOfWarrior", new Warrior().getPrice());
		model.addAttribute("priceOfArcher", new Archer().getPrice());
		return "buyTroops";
	}

	@RequestMapping(value = "/boughtTroops.{i}.{j}.{userName}", method = RequestMethod.GET)
	public String boughtTroops(Model model, @PathVariable int i,
			@PathVariable int j,
			@RequestParam("numberOfWarriors") int numberOfWarriors,
			@RequestParam("numberOfArchers") int numberOfArchers,
			@PathVariable("userName") String userName) {
		
		
		Manager manager = new Manager();
		User user = null;
		for(User u: manager.getUsers()){
			if(u.getName().equals(userName)) user = u;
		}
		
		for (int w = 0; w < numberOfWarriors; ++w){
			user.getTroops().add(new Warrior());
			user.removeGold(new Warrior().getPrice());
		}
			
		for (int a = 0; a < numberOfArchers; ++a){
			user.getTroops().add(new Archer());
			user.removeGold(new Archer().getPrice());
		}
			

		return "redirect:/inVillage.{i}.{j}.{userName}";
	}
	
	
	

	@RequestMapping(value = "/sendUnit.{i}.{j}.{userName}")
	public String sendUnit(Model model, @PathVariable int i,
						   @PathVariable int j, @PathVariable String userName) {
		
		Manager manager = new Manager();
		User user = null;
		for(User u: manager.getUsers()){
			if(u.getName().equals(userName)) user = u;
		}
		
		int numberOfWarriors = 0;
		int numberOfArchers = 0;
		for (Troop t : user.getTroops()) {			
			if (t instanceof Warrior && !t.isOnWay())	numberOfWarriors++;
			else if (t instanceof Archer && !t.isOnWay()) numberOfArchers++;
		}
		
		model.addAttribute("numberOfWarriors",numberOfWarriors);
		model.addAttribute("numberOfArchers",numberOfArchers);
		model.addAttribute("userName", userName);
		model.addAttribute("X", i);
		model.addAttribute("Y", j);
		return "sendUnit";
	}
	
	@RequestMapping(value="/sentUnit.{i}.{j}.{userName}", method = RequestMethod.GET)
	public String sentUnit(Model model, @PathVariable int i,
			@PathVariable int j,
			@RequestParam("numberOfWarriors") int numberOfSentWarriors,
			@RequestParam("numberOfArchers") int numberOfSentArchers,
			@PathVariable("userName") String userName){
		
		Manager manager = new Manager();
		User user = null;
		for(User u: manager.getUsers()){
			if(u.getName().equals(userName)) user = u;
		}
		
		int tmpWarriors = numberOfSentWarriors;
		int tmpArchers = numberOfSentArchers;
		for(Troop t: user.getTroops()){
			if(!t.isOnWay() && t instanceof Warrior && tmpWarriors!=0){
				t.setOnWay(true);
				t.setDates(user.getX(),user.getY(),i,j);
				tmpWarriors--;
			}
			else if(!t.isOnWay() && t instanceof Archer && tmpArchers!=0){
				t.setOnWay(true);
				t.setDates(user.getX(),user.getY(),i,j);
				tmpArchers--;
			}
		}
		
		
		//luckyba
		if(manager.getMap().getFields(i, j).getLucky() != null	&& manager.getMap().getFields(i, j).getOasis() == null	&& manager.getMap().getFields(i, j).getUser() == null){
			
			user.addGold(manager.getMap().getFields(i, j).getLucky().getGold());
			manager.getMap().getFields(i, j).getLucky().setGold(0);
		}
	
		
		//oasisba
		if(manager.getMap().getFields(i, j).getOasis() != null   && manager.getMap().getFields(i, j).getLucky() == null	   && manager.getMap().getFields(i, j).getUser() == null){
			
			double damage = numberOfSentWarriors * new Warrior().getDamage() + numberOfSentArchers * new Archer().getDamage();
			double defense = 0;		
			for(Troop t : manager.getMap().getFields(i, j).getOasis().getTroops()){
				defense += t.getDefense();
			}
			
	
			//vmi bonuslogic is kéne, hogy ne az elso egysegeket toroljem, de hat most ez is megteszi
			//a játékos nyert
			if(damage > defense){				
				user.addGold(manager.getMap().getFields(i, j).getOasis().getGold());
				manager.getMap().getFields(i, j).getOasis().getTroops().clear();
				
				Iterator<Troop> it = user.getTroops().iterator();
				while(it.hasNext()){
					Troop troop = it.next();					

					if(defense >= troop.getDamage() && troop.isOnWay()){
						it.remove();
						defense-= troop.getDamage();
					}
				}

			}
			//a játékos elbukott
			else if(damage < defense){
				Iterator<Troop> it = user.getTroops().iterator();
				while(it.hasNext()){
					Troop troop = it.next();
					//valojaban csak azt kene ami odatart, de perpill gozom sincs..
					if(troop.isOnWay()) it.remove();
				}
				
				it = manager.getMap().getFields(i, j).getOasis().getTroops().iterator();
				while(it.hasNext()){
					Troop troop = it.next();
					if(damage >= troop.getDefense()){
						it.remove();
						damage-=troop.getDefense();
					}
				}
			}
			//mindenki elbukott
			else{
				manager.getMap().getFields(i, j).getOasis().getTroops().clear();

				Iterator<Troop> it = user.getTroops().iterator();
				while(it.hasNext()){
					Troop troop = it.next();
					if(troop.isOnWay()) it.remove();
				}
			}					
		}
		
		return "redirect:/inVillage.{i}.{j}.{userName}";
		
	}
}
