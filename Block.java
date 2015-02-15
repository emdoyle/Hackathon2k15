public class Block {

	private String color;
	private char operator;
	private boolean empty;
	
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
