package com.aronkatona.model.troops;

import java.util.Date;

public abstract class Troop {
	
	protected double hp;
	protected double damage;
	protected double defense;
	protected double movementSpeed;
	protected boolean onWay;
	protected Date date;
	protected int price;
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setDate(Date date) {
		this.date = date;
	}
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
	public Date getDate() {
		return date;
	}
	
	
	public void setDate(int sourceX, int sourceY, int targetX, int targetY) {
		
		double distance = distanceFromTargetToSource(sourceX,sourceY,targetX,targetY);
		
		int bonusTime = (int) bonusTime(distance,movementSpeed);
		Date actDate = 	new Date(System.currentTimeMillis()+bonusTime*1000);		
		
		this.date = actDate;
		
	}
	
	public void setDateToNull(){
		this.date = null;
	}
	
	public double distanceFromTargetToSource(int sourceX, int sourceY, int targetX, int targetY){
		return  Math.sqrt((Math.pow(sourceX - targetX, 2) + Math.pow(sourceY - targetY, 2)));
	}
	
	public double bonusTime(double distance, double movementSpeed){
		return  distance/movementSpeed;
	}
	
	
	
	

}
