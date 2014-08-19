package com.aronkatona.service;

import java.util.ArrayList;
import java.util.List;

import com.aronkatona.model.map.Map;
import com.aronkatona.model.troops.Archer;
import com.aronkatona.model.troops.Troop;
import com.aronkatona.model.troops.Warrior;
import com.aronkatona.model.user.User;

public class Manager {

	private static List<User> users;
	private static Map map;
	
	static{
		users = new ArrayList<User>();
		User asd = new User("asd");
		for(int i = 0; i < 5; ++i)
		asd.addTroop(new Warrior());
		for(int i = 0; i < 3; ++i)
		asd.addTroop(new Archer());
		users.add(asd);
		
		
		User qwe = new User("qwe");
		List<Troop> qweTroops = new ArrayList<Troop>();
		for(int i = 0; i < 8; ++i)
		qwe.addTroop(new Warrior());
		for(int i = 0; i < 4; ++i)
		qwe.addTroop(new Archer());;
		users.add(qwe);
		map = new Map(5);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	
	
	
}
