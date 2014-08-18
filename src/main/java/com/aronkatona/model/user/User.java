package com.aronkatona.model.user;

import java.util.ArrayList;
import java.util.List;

import com.aronkatona.model.troops.Troop;

public class User {
	
	private String name;
	private int gold;
	private List<Troop> troops;
	
	public User(String name){
		this.name = name;
		this.gold = 100;
		troops = new ArrayList<Troop>();
	}
	
	public void addTroop(Troop troop){
		troops.add(troop);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public List<Troop> getTroops() {
		return troops;
	}
	public void setTroops(List<Troop> troops) {
		this.troops = troops;
	}
	
	

}
