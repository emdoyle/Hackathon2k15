import java.awt.Color;

/*
 * Filename: Block.java
 * Description: Contains all info related to "blocks" which are the operators */

public class Block {

	/* color: for gui; empty: to determine if the block has been instantiated */
	private Color c;
	private char operator;
	private boolean empty;
	
	/* constructor sets the block to empty by default */
	public Block(char operator, boolean empty){
		setUpBlock(operator);
		setEmpty(empty);
	}
	
	public Color getColor(){
	  return c;
	}
	
	private void setColor(Color newColor){
		c = newColor;
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
			setColor(Color.RED);
			break;
		case '-':
			setColor(Color.BLUE);
			break;
		case '^':
			setColor(Color.GREEN);
			break;
		case '|':
			setColor(Color.ORANGE);
			break;
		default:
			setColor(Color.BLACK);
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
