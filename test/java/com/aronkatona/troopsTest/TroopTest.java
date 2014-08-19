package com.aronkatona.troopsTest;

import com.aronkatona.model.troops.Warrior;

import junit.framework.TestCase;

public class TroopTest extends TestCase{
	
	public void testPitagoras(){
		int sourceX = 3;
		int sourceY = 4;
		int targetX = 5;
		int targetY = 2;
		Warrior warrior = new Warrior();
		assertEquals(2.8284271247461903,warrior.distanceFromTargetToSource(sourceX, sourceY, targetX, targetY));
		
		assertEquals(14.317821063276353,warrior.distanceFromTargetToSource(1, 2, 7, -11));
	}
	
	public void testBonusTime(){
		double distance = 1.5;
		double movementSpeed = 0.1;
		Warrior warrior = new Warrior();
		assertEquals(15.0,warrior.bonusTime(distance, movementSpeed));
		assertEquals(24.0,warrior.bonusTime(1.20, 0.05));
	}
}
