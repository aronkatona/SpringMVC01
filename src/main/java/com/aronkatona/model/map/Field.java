package com.aronkatona.model.map;

import com.aronkatona.model.user.User;

public class Field {
	
	private User user;
	private Oasis oasis;
	private Lucky lucky;
	
	public Field(){
		this.user = null;
		this.oasis = null;
		this.lucky = null;
	}
	
	public Field(User user){
		this.user = user;
	}
	
	public Field(Oasis oasis){
		this.oasis = oasis;
	}
	
	public Field(Lucky lucky){
		this.lucky = lucky;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Oasis getOasis() {
		return oasis;
	}
	public void setOasis(Oasis oasis) {
		this.oasis = oasis;
	}
	public Lucky getLucky() {
		return lucky;
	}
	public void setLucky(Lucky lucky) {
		this.lucky = lucky;
	}

	
}
