package game;

import java.util.ArrayList;

public class Room {
	
	private String roomNumber, roomName, roomDescription;
	private int north, east, south, west;
	private boolean visitedRoom = false;
	private ArrayList<Item> roomItems;
	private ArrayList<Monster> roomMonsters;
	private ArrayList<Puzzle> roomPuzzles;
	
	public Room() {
		roomItems = new ArrayList<Item>();
		roomMonsters = new ArrayList<Monster>();
		roomPuzzles = new ArrayList<Puzzle>();
	}
	
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public int getNorth() {
		return north;
	}
	public void setNorth(int north) {
		this.north = north;
	}
	public int getEast() {
		return east;
	}
	public void setEast(int east) {
		this.east = east;
	}
	public int getSouth() {
		return south;
	}
	public void setSouth(int south) {
		this.south = south;
	}
	public int getWest() {
		return west;
	}
	public void setWest(int west) {
		this.west = west;
	}
	public boolean isVisitedRoom() {
		return visitedRoom;
	}
	public void setVisitedRoom(boolean visitedRoom) {
		this.visitedRoom = visitedRoom;
	}
	@Override
	public String toString() {
		return "\nRoom: " + roomName + "\nRoom Description: " + roomDescription + "\nItems in room: " + printItemsInRoom() + "\nPuzzles in room: " + printPuzzlesInRoom() + "\nMonsters in room: " + printMonstersInRoom();
	}
	public ArrayList<Item> getRoomItems() {
		return roomItems;
	}
	public void setItems(ArrayList<Item> roomItems) {
		this.roomItems = roomItems;
	}
	public ArrayList<Monster> getRoomMonsters() {
		return roomMonsters;
	}
	public void setRoomMonsters(ArrayList<Monster> roomMonsters) {
		this.roomMonsters = roomMonsters;
	}
	public ArrayList<Puzzle> getroomPuzzles() {
		return roomPuzzles;
	}
	public void setRoomPuzzles(ArrayList<Puzzle> roomPuzzles) {
		this.roomPuzzles = roomPuzzles;
	}
	public String printItemsInRoom() {
		String itemList = "";
		if(roomItems.size()!=0) {
			for(Item x: roomItems) {
				itemList += x.getItemName() + " - ";
			}
			return itemList;
		}else
			return "None";
	}
	public String printPuzzlesInRoom() {
		String puzzleList = "";
		if(roomPuzzles.size()!=0) {
			for(Puzzle x: roomPuzzles) {
				puzzleList +=x.getPuzzleName() + " ";
			}
			return puzzleList;
		}else
			return "None";
	}
	public String printMonstersInRoom() {
		String monsterList = "";
		if(roomMonsters.size()!=0) {
			for(Monster x: roomMonsters) {
				monsterList +=x.getMonsterName() + " ";
			}
			return monsterList;
		}else
			return "None";
	}
}
