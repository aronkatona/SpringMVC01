package com.aronkatona.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aronkatona.model.troops.Archer;
import com.aronkatona.model.troops.Troop;
import com.aronkatona.model.troops.Warrior;

public class Map {
	
	private Field[][] fields;
	private int size;
	
	public Map(int size){
		Random rnd = new Random();
		this.size = size;
		fields = new Field[size][size];
		for(int i = 0; i < size; ++i){
			for(int j = 0; j < size; ++j){
				fields[i][j] = new Field();
				if(j % 2 == 0)	{
					List<Troop> oasisTroops = new ArrayList<Troop>();
					int W = rnd.nextInt(10);
					for(int k = 0; k < W ; ++k)
					oasisTroops.add(new Warrior());
					
					int A = rnd.nextInt(10);			
					for(int k = 0; k < A; ++k)
					oasisTroops.add(new Archer());
					
					int gold = rnd.nextInt(1000);
					fields[i][j].setOasis(new Oasis(gold, oasisTroops));
				}
				else {
					int gold = rnd.nextInt(1000);
					fields[i][j].setLucky(new Lucky(gold));
				}
			}
		}		
	}
	
	public Field[][] getFields(){
		return fields;
	}

	public Field getFields(int i, int j) {
		return fields[i][j];
	}

	public void setFields(Field[][] fields) {
		this.fields = fields;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

}
