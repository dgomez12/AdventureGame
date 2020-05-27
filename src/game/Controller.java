package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
	private Scanner scan;
	private Scanner user;
	private File file;
	private ArrayList<Room> rooms;
	private ArrayList<Item> items;
	private ArrayList<Puzzle> puzzles;
	private ArrayList<Monster> monsters;
	private int currentRoom = 0;
	private ArrayList<Item> inventory;
	private Player player;
	
	public Controller() {
		rooms = new ArrayList<Room>();
		items = new ArrayList<Item>();
		puzzles = new ArrayList<Puzzle>();
		inventory = new ArrayList<Item>();
		monsters = new ArrayList<Monster>();
		player = new Player();
	}
	public void newGame() throws FileNotFoundException {
		readItemFile();
		readMonsterFile();
		readPuzzleFile();
		readRoomFile();
		setUserName();
		welcomeMessage();
		roomTester();
		while (true) {
			play();
		}
	}
	public void welcomeMessage() {
		System.out.println("~~~~~~~~~~ Welcome to Denzel's Demo Game! ~~~~~~~~~~" + "\n\nType 'go' followed by a direction, 'north', 'south', 'east',"
				+ " or 'west' to navigate between 6 rooms.\n" + "Type 'show commands' for a list of more commands.");
	}
	public void play() throws FileNotFoundException {
		rooms.get(0).setVisitedRoom(true);
		user = new Scanner(System.in);
		System.out.println("\nWhat would you like to do?");
		String userInput = "";
		String line = user.nextLine();
		scan = new Scanner(line);
		String command = scan.next();
		if(!scan.hasNext()) {
			System.out.println("try again!");
			return;
		}else {
			userInput = scan.next();
			if(scan.hasNext()) {
				userInput = userInput + " " + scan.next();
			}
		}
//		userInput = combined[1];
//		System.out.println(command + " " + userInput);
		if(command.equalsIgnoreCase("examine")) {
			examineItem(userInput);
		}else if(command.equalsIgnoreCase("pickup")) {
			pickupItem(userInput);
		}else if(command.equalsIgnoreCase("drop")) {
			dropItem(userInput);
		}else if(command.equalsIgnoreCase("equip")) {
			equipItem(userInput);
		}else if(command.equalsIgnoreCase("unequip")) {
			unequipItem(userInput);
		}else if(command.equalsIgnoreCase("battle")) {
			battle(userInput);
		}else if(command.equalsIgnoreCase("inspect")) {
			inspectPuzzle(userInput);
		}else if(command.equalsIgnoreCase("solve")) {
			solvePuzzle(userInput);
		}else if(command.equalsIgnoreCase("go")) {
			navigate(userInput);
		}else if(command.equalsIgnoreCase("show") && userInput.equalsIgnoreCase("commands")) {
			showCommands();
		}else if(command.equalsIgnoreCase("check") && userInput.equalsIgnoreCase("inventory")) {
			checkInventory();
		}else if(command.equalsIgnoreCase("check") && userInput.equalsIgnoreCase("stats")) {
			checkStats();
		}else
			System.out.println("Invalid input, try again.");
	}
	
	
	public void readRoomFile() throws FileNotFoundException {
		rooms = new ArrayList<Room>();
		file = new File("Room.txt");
		scan = new Scanner(file);
		while (scan.hasNextLine()) {
			//System.out.println(scan.nextLine());
			
			Room x = new Room();
			String roomNumber = scan.nextLine();
			x.setRoomNumber(roomNumber);
			
			String roomName = scan.nextLine();
			x.setRoomName(roomName);
			
//			String desc = "";
//			String buffer = "--";
//			while (scan.nextLine()!=buffer) {
//				desc += scan.nextLine();
//			}
//			x.setRoomDescription(desc);
			String desc = scan.nextLine();
			x.setRoomDescription(desc);
			
			// dashes
			scan.nextLine();
			
			String line = scan.nextLine();
			Scanner sc = new Scanner(line);
			sc.next();
			int north = sc.nextInt();
			x.setNorth(north);
			
			line = scan.nextLine();
			sc = new Scanner(line);
			sc.next();
			int east = sc.nextInt();
			x.setEast(east);
			
			line = scan.nextLine();
			sc = new Scanner(line);
			sc.next();
			int south = sc.nextInt();
			x.setSouth(south);
			
			line = scan.nextLine();
			sc = new Scanner(line);
			sc.next();
			int west = sc.nextInt();
			x.setWest(west);
			
			scan.nextLine();
			scan.nextLine();
			scan.nextLine();
			
			rooms.add(x);
		}
		rooms.get(0).getRoomItems().add(items.get(3));
		rooms.get(1).getRoomItems().add(items.get(0));
		rooms.get(1).getroomPuzzles().add(puzzles.get(1));
		rooms.get(4).getroomPuzzles().add(puzzles.get(0));
		rooms.get(5).getroomPuzzles().add(puzzles.get(2));
		rooms.get(0).getRoomMonsters().add(monsters.get(2));
		rooms.get(2).getRoomMonsters().add(monsters.get(0));
		rooms.get(3).getRoomMonsters().add(monsters.get(1));
		
	}
	
	public void readItemFile() throws FileNotFoundException {
		items = new ArrayList<Item>();
		file = new File("Item.txt");
		scan = new Scanner(file);
		while (scan.hasNextLine()) {
			Item x = new Item();
			String itemID = scan.nextLine();
//			System.out.println(itemID + " id");
			x.setItemID(itemID);
			String itemName = scan.nextLine();
//			System.out.println(itemName + " name");
			x.setItemName(itemName);
//			String itemDescription = "";
//			while (!scan.nextLine().equals("--")) {
//				itemDescription += scan.nextLine();
//			}
			String itemDescription = scan.nextLine();
//			System.out.println(itemDescription + " desc");
			x.setItemDescription(itemDescription);
			scan.nextLine();
			String itemType = scan.nextLine();
//			System.out.println(itemType + " type");
			x.setItemType(itemType);
			String itemHealth = scan.nextLine();
//			System.out.println(itemHealth + " hp");
			x.setItemHealth(Integer.parseInt(itemHealth));
			String itemStrength = scan.nextLine();
//			System.out.println(itemStrength + " str");
			x.setItemStrength(Integer.parseInt(itemStrength));
			items.add(x);
		}
	}
	public void readPuzzleFile() throws FileNotFoundException {
		puzzles = new ArrayList<Puzzle>();
		file = new File("Puzzle");
		scan = new Scanner(file);
		while(scan.hasNextLine()) {
			Puzzle x = new Puzzle();
			String puzzleID = scan.nextLine();
//			System.out.println(puzzleID);
			x.setPuzzleID(puzzleID);
			String puzzleName = scan.nextLine();
//			System.out.println(puzzleName);
			x.setPuzzleName(puzzleName);
			String puzzleDesc = scan.nextLine();
//			System.out.println(puzzleDesc);
			x.setPuzzleDescription(puzzleDesc);
			String puzzleSolution = scan.nextLine();
//			System.out.println(puzzleSolution);
			x.setSolution(puzzleSolution);
			String puzzleHint = scan.nextLine();
//			System.out.println(puzzleHint);
			x.setHint(puzzleHint);
			String puzzleItem = scan.nextLine();
//			System.out.println(puzzleItem);
			puzzles.add(x);
		}
		puzzles.get(0).setItem(items.get(4));
		puzzles.get(1).setItem(items.get(6));
	}

	public void readMonsterFile() throws FileNotFoundException {
//		monsters = new ArrayList<Monster>();
		file = new File("Monster.txt");
		scan = new Scanner(file);
		while(scan.hasNextLine()) {
			Monster x = new Monster();
			String monsterID = scan.nextLine();
			x.setMonsterID(monsterID);
			
			String monsterName = scan.nextLine();
			x.setMonsterName(monsterName);
			
			String monsterDescription = scan.nextLine();
			x.setMonsterDescription(monsterDescription);
			
			String monsterHealth = scan.nextLine();
			x.setHealth(Integer.parseInt(monsterHealth));
			
			String monsterDamage = scan.nextLine();
			x.setAttack(Integer.parseInt(monsterDamage));
			
			String droppedItem = scan.nextLine();
			
			monsters.add(x);
		}
		monsters.get(0).setItem(items.get(2));
		monsters.get(1).setItem(items.get(1));
		monsters.get(2).setItem(items.get(0));
	}
	public void setUserName() {
		System.out.println("\nPlease type in a username.");
		Scanner scan = new Scanner(System.in);
		String playerName = scan.nextLine();
		player.setPlayerName(playerName);
	}
	
	public void pickupItem(String userInput) {
		boolean tf = false;
		if(rooms.get(currentRoom).getRoomItems().size()==0) {
			System.out.println("There are no items in this room.");
		}else {
		for(int i = 0; i < rooms.get(currentRoom).getRoomItems().size(); i++) {
			if(rooms.get(currentRoom).getRoomItems().get(i).getItemName().equalsIgnoreCase(userInput)) {
				inventory.add(rooms.get(currentRoom).getRoomItems().remove(i));
				System.out.println(userInput + " was added to your inventory.");
				tf = true;
			}
		}
			if(tf == false) {
			System.out.println("Check your input because " + userInput + " is not this room.");
			}
		}
	}
	public void dropItem(String userInput) {
		boolean tf = false;
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).getItemName().equalsIgnoreCase(userInput)) {
				rooms.get(currentRoom).getRoomItems().add(inventory.remove(i));
				System.out.println(userInput + " was dropped.");
				tf = true;
			}
		}
			if(tf==false) {
				System.out.println("Check your input because " + userInput + " is not in your inventory.");
			}
	}
	public void examineItem(String userInput) throws FileNotFoundException {
		boolean itemTF = false;
		for(int i = 0; i < monsters.size(); i++) {
			if(userInput.equalsIgnoreCase(monsters.get(i).getMonsterName())) {
				examineMonster(userInput);
				return;
			}
		}
		for (int i = 0; i < items.size(); i++) {
			if (userInput.equalsIgnoreCase(items.get(i).getItemName())) {
				System.out.println(items.get(i).getItemDescription());
				itemTF = true;
			}
		}
			if(itemTF==false) {
				System.out.println("That item doesn't exist.");
			}
	}
	public void equipItem(String userInput) {
		boolean tf = false;
		for(int i = 0; i < inventory.size(); i++) {
			if (userInput.equalsIgnoreCase(inventory.get(i).getItemName())) {
				if(inventory.get(i).getItemType().equalsIgnoreCase("weapon")) {
					int value = inventory.get(i).getItemStrength() + player.getInitialAtkDmg();
					player.setInitialAtkDmg(value);
					System.out.println(inventory.get(i).getItemName() + " was equipped.");
					player.getEquipped().add(inventory.remove(i));
					
					tf = true;
				} else {
					System.out.println("That item isn't equippable.");
				}
			}
		}
		if(tf == false) {
			System.out.println(userInput + " doesn't exist.");
		}
	}
	public void unequipItem(String userInput) {
		boolean tf = false;
		for(int i = 0; i < inventory.size(); i++) {
			if (userInput.equalsIgnoreCase(player.getEquipped().get(i).getItemName())) {
					int value = player.getInitialAtkDmg() - player.getEquipped().get(i).getItemStrength();
					player.setInitialAtkDmg(value);
					System.out.println(player.getEquipped().get(i).getItemName() + " was unequipped.");
					inventory.add(player.getEquipped().remove(i));
					
					tf = true;
			}
		}
		if(tf == false) {
			System.out.println(userInput + " doesn't exist.");
		}
	}
	public void examineMonster(String userInput) throws FileNotFoundException {
		if(rooms.get(currentRoom).getRoomMonsters().isEmpty()) {
			System.out.println("There are no monsters in this room.");
			return;
		}
		boolean tf = false;
		for(int i = 0; i < rooms.get(currentRoom).getRoomMonsters().size(); i++) {
			if(userInput.equalsIgnoreCase(rooms.get(currentRoom).getRoomMonsters().get(i).getMonsterName())) {
				System.out.println(rooms.get(currentRoom).getRoomMonsters().get(i).getMonsterDescription());
				tf = true;
				battle(userInput);
			}
		}
		if(tf == false) {
			System.out.println(userInput + " isn't in this room or doesn't exist.");
		}
	}
	
	public void battle(String userInput) throws FileNotFoundException {
			for(int i = 0; i < rooms.get(currentRoom).getRoomMonsters().size(); i++) {
				if(userInput.equalsIgnoreCase(rooms.get(currentRoom).getRoomMonsters().get(i).getMonsterName())) {
					String monsterName = rooms.get(currentRoom).getRoomMonsters().get(i).getMonsterName();
					System.out.println(player.getPlayerName() + " is about to go into battle with " + monsterName + 
							"\nWould you like to proceed?(Y/N)");
					Scanner user = new Scanner(System.in);
					String userMove = user.next();
					if(userMove.equalsIgnoreCase("n")) {
						System.out.println("The fight was cancelled. \nWhat would you like to do?");
						return;
					}else {
						int playerHealth = player.getHealth();
						int playerAtk = player.getInitialAtkDmg();
						int monsterHealth = rooms.get(currentRoom).getRoomMonsters().get(i).getHealth();
						int monsterAtk = rooms.get(currentRoom).getRoomMonsters().get(i).getAttack();
						System.out.println("The battle has started! You can 'attack', 'heal', or 'run'" + 
					"\nif you run then the fight will end and the monster will permanently leave the dungeon!");
						while(!(playerHealth <= 0) || !(monsterHealth <= 0)) {
							if(monsterHealth <= 0) {
								String itemName = rooms.get(currentRoom).getRoomMonsters().get(i).getItem().getItemName();
								inventory.add(rooms.get(currentRoom).getRoomMonsters().get(i).getItem());
								rooms.get(currentRoom).getRoomMonsters().remove(i);
								System.out.println("Congrats you won the battle and received " + itemName + "!");
								break;
							}else if(playerHealth <= 0){
								System.out.println("\nOh dear you died! That's Game Over");
								System.out.println("\nWould you like to start over?(Y/N)");
								String startOver = user.next();
								if(startOver.equalsIgnoreCase("N")) {
								System.exit(0);
								} else {
									newGame();
								}
							}
							System.out.println("\n" + player.getPlayerName() + " health: " + playerHealth + "\n" + monsterName + " health: " + monsterHealth);
							System.out.println("What would you like to do?");
							userMove = user.next();
							if(userMove.equalsIgnoreCase("attack")) {
								int playerDmgDone = (int) (Math.random() * ((playerAtk - 0) + 1));
								monsterHealth -= playerDmgDone;
								int monsterDmgDone = (int) (Math.random() * ((monsterAtk - 0) + 1));
								playerHealth -= monsterDmgDone;
								player.setHealth(playerHealth);
								System.out.println(player.getPlayerName() + " did " + playerDmgDone + " damage to " + monsterName);
								System.out.println(monsterName + " did " + monsterDmgDone + " damage to " + player.getPlayerName());
							}else if(userMove.equalsIgnoreCase("heal")) {
								for(int j = 0; j < inventory.size(); j++) {
									if(inventory.get(j).getItemName().equalsIgnoreCase("potion")) {
										playerHealth += 50;
										if(playerHealth > 100) {
											playerHealth = 100;
										}
										System.out.println("Your health went up by 50! (less if your health was higher than 50)");
										inventory.remove(j);
										break;
									}else {
										System.out.println("You have no potions left!");
									}
								}
							}else if(userMove.equalsIgnoreCase("run")) {
								System.out.println(player.getPlayerName() + " ran like a coward from " + monsterName);
								rooms.get(currentRoom).getRoomMonsters().remove(i);
								break;
							}
						}
					}
				}
			}
		}
	public void checkStats() {
		System.out.println("Current Health: " + player.getHealth());
		System.out.println("Current Attack Damage: " + player.getInitialAtkDmg());
		if(player.getEquipped().size()!=0) {
			for(Item x: player.getEquipped()) {
				System.out.println(x.getItemName());
			}
		}else {
			System.out.println("No items equipped.");
		}
	}
	public void checkInventory() {
		if(inventory.size()!=0) {
			for(Item x: inventory) {
				System.out.println(x.getItemName());
			}
		}else
			System.out.println("No items in inventory currently.");
	}
	public void showCommands() {
		System.out.println("Type 'inspect' or 'solve' followed by the puzzle name." + 
	"\n for example 'inspect puzzle 2'");
		System.out.println("\nType 'examine', 'drop', 'pickup', 'equip', or 'unequip' followed by the item name.");
		System.out.println("\nType 'battle' or 'examine' followed by the monsters name to battle or get description.");
		System.out.println("\nType 'check inventory' to see your current inventory or 'check stats' to see current health, attack dmg, and equipped items.");
	}
	public void itemTester() {
		System.out.println(items.get(3));
	}
	public void roomTester() {
		currentRoom = 0;
		System.out.println(rooms.get(0));
	}
	public void inspectPuzzle(String userInput) {
		boolean tf = false;
		for(int i = 0; i < rooms.get(currentRoom).getroomPuzzles().size(); i++) {
			if(rooms.get(currentRoom).getroomPuzzles().get(i).getPuzzleName().equalsIgnoreCase(userInput)) {
				System.out.println(rooms.get(currentRoom).getroomPuzzles().get(i).getPuzzleDescription());
				tf = true;
			}
		}
			if(tf==false) {
				System.out.println("That puzzle doesn't exist in this room. Make sure you're typing the correct input"
						+ ", 'inspect + puzzle's name'.");
			}
	}
	public void solvePuzzle(String userInput) {
		boolean tf = false;
		user = new Scanner(System.in);
		if(rooms.get(currentRoom).getroomPuzzles().size()==0) {
			System.out.println("There are no puzzles in this room.");
		} else {
		for(int i = 0; i < rooms.get(currentRoom).getroomPuzzles().size(); i++) {
			if(rooms.get(currentRoom).getroomPuzzles().get(i).getPuzzleName().equalsIgnoreCase(userInput)) {
				for(int j = 0; j < 3; j++) {
					System.out.println("\n" + rooms.get(currentRoom).getroomPuzzles().get(i).getPuzzleDescription());
					System.out.println("Type in your answer.");
					String solution = user.nextLine();
					if(rooms.get(currentRoom).getroomPuzzles().get(i).getSolution().equalsIgnoreCase(solution)) {
						inventory.add(rooms.get(currentRoom).getroomPuzzles().get(i).getItem());
						String droppedItem = "";
						if(rooms.get(currentRoom).getroomPuzzles().get(i).getItem()!=null) {
						droppedItem = rooms.get(currentRoom).getroomPuzzles().get(i).getItem().getItemName();
						System.out.println("Congratulations you solved the puzzle correctly! and received "+ droppedItem);
						} else {
							System.out.println("Congratulations you solved the puzzle correctly!");
						}
						rooms.get(currentRoom).getroomPuzzles().remove(i);
						break;
					}else if(j == 2) {
						System.out.println("You failed to solve the puzzle within 3 attempts! You Suck!");
						rooms.get(currentRoom).getroomPuzzles().remove(i);
					}
				}
				tf = true;
			}
			if(tf==false) {
				System.out.println("That puzzle doesn't exist in this room. Make sure you're typing the correct input"
						+ ", 'solve + puzzle's name'.");
				}
			}
		}
	}
	public void navigate(String userInput) {
		if (userInput.equalsIgnoreCase("north")) {
			if(rooms.get(currentRoom).getNorth() == -1) {
				System.out.println("There's no room that direction! Try a different direction.");
			} else
			currentRoom = rooms.get(currentRoom).getNorth()-1;
			System.out.println(rooms.get(currentRoom));
			if (rooms.get(currentRoom).isVisitedRoom() == true) {
				System.out.println("\nYou have visited this room before.");
			}
			rooms.get(currentRoom).setVisitedRoom(true);
		}
		else if (userInput.equalsIgnoreCase("east")) {
			if(rooms.get(currentRoom).getEast() == -1) {
				System.out.println("There's no room that direction! Try a different direction.");
			} else
			currentRoom = rooms.get(currentRoom).getEast()-1;
			System.out.println(rooms.get(currentRoom));
			if (rooms.get(currentRoom).isVisitedRoom() == true) {
				System.out.println("\nYou have visited this room before.");
			}
			rooms.get(currentRoom).setVisitedRoom(true);
		}
		else if (userInput.equalsIgnoreCase("south")) {
			if(rooms.get(currentRoom).getSouth() == -1) {
				System.out.println("There's no room that direction! Try a different direction.");
			} else
			currentRoom = rooms.get(currentRoom).getSouth()-1;
			System.out.println(rooms.get(currentRoom));
			if (rooms.get(currentRoom).isVisitedRoom() == true) {
				System.out.println("\nYou have visited this room before.");
			}
			rooms.get(currentRoom).setVisitedRoom(true);
		}
		else if (userInput.equalsIgnoreCase("west")) {
			if(rooms.get(currentRoom).getWest() == -1) {
				System.out.println("There's no room that direction! Try a different direction.");
			} else
			currentRoom = rooms.get(currentRoom).getWest()-1;
			System.out.println(rooms.get(currentRoom));
			if (rooms.get(currentRoom).isVisitedRoom() == true) {
				System.out.println("\nYou have visited this room before.");
			}
			rooms.get(currentRoom).setVisitedRoom(true);
		}
		else
			System.out.println("That's not a valid command. Try 'north', 'east', 'south', or 'west'.");
	}
}