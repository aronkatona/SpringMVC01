package com.aronkatona.model.map;

import java.util.List;

import com.aronkatona.model.troops.Troop;

public class Oasis {
	
	private int gold;
	private List<Troop> troops;
	
	public Oasis(int gold, List<Troop> troops){
		this.gold = gold;
		this.troops = troops;
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
