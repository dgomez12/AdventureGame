package game;

import java.util.ArrayList;

public class Player {

	private String playerName;
	private int health, initialAtkDmg;
	private ArrayList<Item> equipped;
//	private ArrayList<Item> inventory;
	
	public Player() {
		health = 100;
		initialAtkDmg = 10;
		equipped = new ArrayList<Item>();
//		inventory = new ArrayList<Item>();
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public ArrayList<Item> getEquipped() {
		return equipped;
	}
	public void setEquipped(ArrayList<Item> equipped) {
		this.equipped = equipped;
	}
//	public ArrayList<Item> getInventory() {
//		return inventory;
//	}
//	public void setInventory(ArrayList<Item> inventory) {
//		this.inventory = inventory;
//	}
	public int getInitialAtkDmg() {
		return initialAtkDmg;
	}
	public void setInitialAtkDmg(int initialAtkDmg) {
		this.initialAtkDmg = initialAtkDmg;
	}
	
}
