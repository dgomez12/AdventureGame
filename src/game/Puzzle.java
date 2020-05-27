package game;

public class Puzzle {

	private String puzzleID, puzzleName, puzzleDescription, solution, hint;
	private Item item;
	
	
	public String getPuzzleID() {
		return puzzleID;
	}
	public void setPuzzleID(String puzzleID) {
		this.puzzleID = puzzleID;
	}
	public String getPuzzleName() {
		return puzzleName;
	}
	public void setPuzzleName(String puzzleName) {
		this.puzzleName = puzzleName;
	}
	public String getPuzzleDescription() {
		return puzzleDescription;
	}
	public void setPuzzleDescription(String puzzleDescription) {
		this.puzzleDescription = puzzleDescription;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
