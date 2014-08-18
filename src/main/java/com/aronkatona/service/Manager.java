package com.aronkatona.service;

import java.util.ArrayList;
import java.util.List;

import com.aronkatona.model.map.Map;
import com.aronkatona.model.user.User;

public class Manager {

	private static List<User> users;
	private static Map map;
	
	static{
		users = new ArrayList<User>();
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
