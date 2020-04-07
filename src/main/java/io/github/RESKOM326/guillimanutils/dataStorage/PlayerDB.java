package io.github.RESKOM326.guillimanutils.dataStorage;

public class PlayerDB {
	private int ID;
	private String name;
	
	public PlayerDB(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
