/*
 * Filename: Block.java
 * Description: Contains all info related to "blocks" which are the operators */

public class Block {

	/* color: for gui; empty: to determine if the block has been instantiated */
	private String color;
	private char operator;
	private boolean empty;
	
	/* constructor sets the block to empty by default */
	public Block(char operator, boolean empty){
		setUpBlock(operator);
		setEmpty(empty);
	}
	
	public String getColor(){
	  return color;
	}
	
	private void setColor(String newColor){
		color = newColor;
	}
	
	public char getOperator(){
		return operator;
	}
	
	/* combined operator setter and color setter */
	public void setUpBlock(char newOperator){
		operator = newOperator;
		empty = false;
		switch(newOperator){
		case '+':
			setColor("RED");
			break;
		case '-':
			setColor("BLUE");
			break;
		case '^':
			setColor("GREEN");
			break;
		case '|':
			setColor("ORANGE");
			break;
		default:
			setColor("BLACK");
			empty = true;
			break;
		}
	}
	
	public boolean isEmpty(){
		return empty;
	}
	
	public void setEmpty(boolean newEmpty){
		empty = newEmpty;
	}
	
}
