package com.aronkatona.model.troops;

public class Archer extends Troop {
	
	public Archer(){
		this.hp = 30;
		this.damage = 15;
		this.defense = 5;
		this.movementSpeed = 0.15;
		this.onWay = false;
		this.price = 8;
	}
	
	@Override
	public String toString(){
		return "Archer";
	}

}
