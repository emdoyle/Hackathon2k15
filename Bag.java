import java.util.Random;
/*
 * Filename: Bag.java
 * Description: Holds the 'bag' of operators to be chosen for the solution */

public class Bag {
	private char[] charBag;
	private boolean[] bagDisplay; //keeps track of which elems in bag should
								//be displayed
	
	public Bag(char[] bag, boolean[] bagDisplay){
		this.charBag = bag;
		this.bagDisplay = bagDisplay;
	}
	
	/* prints the bag, checking the boolean array for empty values */
	public void printBag(){
		for(int i = 0; i < charBag.length; i++){
			if(i != 0 && bagDisplay[i]){
				System.out.print(" ");
			}
			if(bagDisplay[i]){
				System.out.print(charBag[i]);
			}
			if(i == charBag.length - 1){
				System.out.println("");
			}
		}
	}
	
	/* resets all the boolean values to true, for displaying */
	public void resetBag(){
		for(int i = 0; i < bagDisplay.length; i++){
			bagDisplay[i] = true;
		}
	}

	/* checks to see if that operator is in the bag */
	public boolean checkBag(char operator) {
		for (int i = 0; i < charBag.length; i++) {
			if (charBag[i] == operator && bagDisplay[i]) return true;
		}
		return false;
	}

	/* removes the next instance of an operator */
	public void removeNextInstance(char operator) {
		for (int i = 0; i < charBag.length; i++) {
			if (charBag[i] == operator && bagDisplay[i]) {
				deleteItem(operator);
				break;
			}
		}
	}

	public void scramble() {
		Random rand = new Random();
		for (int i = charBag.length-1; i > 0; i--) {
			int index = rand.nextInt(i+1);
			char a = charBag[index];
			charBag[index] = charBag[i];
			charBag[i] = a;
		}
	}

	/* gets the index for a certain operator */
	public int getIndexAtItem(char operator) {
		for (int i = 0; i < charBag.length; i++) {
			if (charBag[i] == operator && bagDisplay[i]) return i;
		}
		return 0;
	}

	/* delete the items */
	public void deleteItem(char operator) {
		bagDisplay[getIndexAtItem(operator)] = false;
	}
	
	public char[] getCharBag(){
		return charBag;
	}
	
	public void setCharBag(char[] newArr){
		charBag = newArr;
	}
	
	public boolean[] getBagDisplay(){
		return bagDisplay;
	}
	
	public void setBagDisplay(boolean[] newArr){
		bagDisplay = newArr;
	}
}
