package com.aronkatona.model.map;

import java.util.ArrayList;
import java.util.List;

import com.aronkatona.model.troops.Archer;
import com.aronkatona.model.troops.Troop;
import com.aronkatona.model.troops.Warrior;

public class Map {
	
	private Field[][] fields;
	private int size;
	
	public Map(int size){
		this.size = size;
		fields = new Field[size][size];
		for(int i = 0; i < size; ++i){
			for(int j = 0; j < size; ++j){
				fields[i][j] = new Field();
			}
		}
		
		List<Troop> oasisTroops = new ArrayList<Troop>();
		oasisTroops.add(new Warrior());
		oasisTroops.add(new Archer());
		fields[0][0].setOasis(new Oasis(50, oasisTroops));
		fields[0][1].setLucky(new Lucky(300));
		
		
	}

	public Field[][] getFields() {
		return fields;
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
