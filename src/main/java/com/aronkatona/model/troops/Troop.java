package com.aronkatona.model.troops;

public abstract class Troop {
	
	protected double hp;
	protected double damage;
	protected double defense;
	protected double movementSpeed;
	protected boolean onWay;
	
	
	public double getHp() {
		return hp;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}
	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}
	public double getDefense() {
		return defense;
	}
	public void setDefense(double defense) {
		this.defense = defense;
	}
	public double getMovementSpeed() {
		return movementSpeed;
	}
	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	public boolean isOnWay() {
		return onWay;
	}
	public void setOnWay(boolean onWay) {
		this.onWay = onWay;
	}
	
	

}
