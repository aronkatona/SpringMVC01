package com.aronkatona.model.troops;

import java.util.Date;

public abstract class Troop {
	
	protected double hp;
	protected double damage;
	protected double defense;
	protected double movementSpeed;
	protected boolean onWay;
	protected Date attackDate;
	protected Date backDate;
	protected int price;
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setDate(Date date) {
		this.attackDate = date;
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
	public Date getAttackDate() {
		return attackDate;
	}
	public Date getBackDate(){
		return backDate;
	}
	
	
	public void setDates(int sourceX, int sourceY, int targetX, int targetY) {
		
		double distance = distanceFromTargetToSource(sourceX,sourceY,targetX,targetY);
		
		int bonusTime = (int) bonusTime(distance,movementSpeed);
		Date attackDate = 	new Date(System.currentTimeMillis()+bonusTime*1000);		
		Date backDate = new Date(System.currentTimeMillis() + 2*bonusTime*1000);
		this.attackDate = attackDate;
		this.backDate = backDate;
		
	}
	
	public void setAttackDateToNull(){
		this.attackDate = null;
	}
	
	public void setBackDateToNull(){
		this.backDate = null;
	}
	
	public double distanceFromTargetToSource(int sourceX, int sourceY, int targetX, int targetY){
		return  Math.sqrt((Math.pow(sourceX - targetX, 2) + Math.pow(sourceY - targetY, 2)));
	}
	
	public double bonusTime(double distance, double movementSpeed){
		return  distance/movementSpeed;
	}
	
	
	
	

}
