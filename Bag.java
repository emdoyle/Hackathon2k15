
public class Bag {
	private char[] charBag;
	private boolean[] bagDisplay; //keeps track of which elems in bag should
								//be displayed
	
	public Bag(char[] bag, boolean[] bagDisplay){
		this.charBag = bag;
		this.bagDisplay = bagDisplay;
	}
	
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
	
	public void resetBag(){
		for(int i = 0; i < bagDisplay.length; i++){
			bagDisplay[i] = true;
		}
	}

	public boolean checkBag(char operator) {
		for (int i = 0; i < charBag.length; i++) {
			if (charBag[i] == operator && bagDisplay[i]) return true;
		}
		return false;
	}

	public void removeNextInstance(char operator) {
		for (int i = 0; i < charBag.length; i++) {
			if (charBag[i] == operator && bagDisplay[i]) {
				deleteItem(operator);
				break;
			}
		}
	}

	public int getIndexAtItem(char operator) {
		for (int i = 0; i < charBag.length; i++) {
			if (charBag[i] == operator) return i;
		}
		return 0;
	}

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
