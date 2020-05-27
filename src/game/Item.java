package game;

public class Item {
	private String itemID, itemName, itemDescription, itemType;
	private int itemHealth, itemStrength;
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public int getItemHealth() {
		return itemHealth;
	}
	public void setItemHealth(int itemHealth) {
		this.itemHealth = itemHealth;
	}
	public int getItemStrength() {
		return itemStrength;
	}
	public void setItemStrength(int itemStrength) {
		this.itemStrength = itemStrength;
	}
	@Override
	public String toString() {
		return "\nItem name: " + itemName + "\nItem Description: " + itemDescription;
	}
	
	
}
