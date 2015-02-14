
public class Block {

	private String color;
	private char operator;
	private boolean empty;
	
	public String getColor(){
	  return color;
	}
	
	private void setColor(String newColor){
		color = newColor;
	}
	
	public char getOperator(){
		return operator;
	}
	
	private void setOperator(char newOperator){
		operator = newOperator;
	}
	
	public boolean isEmpty(){
		return empty;
	}
	
	private void setEmpty(boolean newEmpty){
		empty = newEmpty;
	}
	
}
