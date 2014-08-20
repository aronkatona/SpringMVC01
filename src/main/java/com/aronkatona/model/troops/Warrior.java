package com.aronkatona.model.troops;

public class Warrior extends Troop {
	
	public Warrior(){
		this.hp = 50;
		this.damage = 10;
		this.defense = 10;
		this.movementSpeed = 0.1;
		this.onWay = false;
		this.price = 10;
	}
	
	@Override
	public String toString(){
		return "Warrior";
	}

}
