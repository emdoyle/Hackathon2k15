import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
//controls the GUI of the game

public class Graphix extends JPanel implements MouseListener{
	private Container contentPane;
	private JPanel bagPanel;
	private Bag operatorBag;
	
	private char currSelectedOperator = ' ';
	private int positionOnSingleBoard = 4;
	private boolean drawingSingleBoard;
	private boolean drawingSubBoard;
	private Board singleBoard;
	private SubBoard subBoard1, subBoard2, subBoard3;
	
	public Graphix(Puzzle p){
		
		addMouseListener(this);
		//operatorBag = p.puzzleBag;
		//operatorBag.scramble();
		char[] charArr = {'+', '+', '-'};
		boolean[] boolArr = {true, true, true};
		operatorBag = new Bag(charArr, boolArr);
		setSize(800, 600);
		
		singleBoard = new Board(10, 11, 3);
		drawBoard(singleBoard);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//draws the operators in the bag
		for(int i = 0; i < operatorBag.getCharBag().length; i++){
			if(operatorBag.getBagDisplay()[i]){
				g.drawRect(50 + 100*i, 450, 95, 100);
				drawOperator(operatorBag.getCharBag()[i], 50 + 100*i, 450, g);
			}
		}
		
		if(drawingSingleBoard){
			int numSquares = singleBoard.blocks.length + 2;
			int numBlocks = numSquares - 2;
			 //draws first one
			g.drawRect(50, 150, 95, 100);
			g.setFont(new Font("SansSerif", Font.BOLD, 45));
			g.drawString("" + singleBoard.startNum, 75, 200);
			
			for(int j = 0; j < singleBoard.blocks.length; j++){
				drawBlock(singleBoard.blocks[j], (j+1)*(510/(numBlocks + 1)) - 47 + 120, 150, g);
			
			}
			//draw last one
			g.drawRect(605, 150, 95, 100);
			g.setFont(new Font("SansSerif", Font.BOLD, 45));
			g.drawString("" + singleBoard.endNum, 630, 200);
		}
		
		if(drawingSubBoard){
			
		}
	}
	
	private void drawBlock(Block b, int x, int y, Graphics g){
		
		g.drawRect(x, y, 95, 100);
		if(!b.isEmpty()){
			drawOperator(b.getOperator(), x, y, g);
			System.out.println("Tried to draw an operator on a new block");
		}
		
	}
	
	private void drawOperator(char operator, int x, int y, Graphics g){
		switch(operator){
		case '+':
			drawPlus(x, y, g);
			break;
		case '-':
			drawMinus(x, y, g);
			break;
		case '^':
			drawCarat(x, y, g);
			break;
		case '|':
			drawSlice(x, y, g);
			break;
		case '*':
			drawAsterisk(x, y, g);
			break;
		case '/':
			drawSlash(x, y, g);
			break;
		default:
			drawPlus(x, y, g);
			break;
		}
	}
	
	private void drawSlash(int x, int y, Graphics g) {
		// TODO Auto-generated method stub
		
	}

	private void drawAsterisk(int x, int y, Graphics g) {
		// TODO Auto-generated method stub
		
	}

	private void drawSlice(int x, int y, Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 80));
		g.drawString("|", x + 40, y + 70);
	}

	private void drawCarat(int x, int y, Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 100));
		g.drawString("^", x + 20, y + 90);
	}

	private void drawMinus(int x, int y, Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 100));
		g.drawString("-", x + 30, y + 70);
	}

	private void drawPlus(int x, int y, Graphics g){
		g.setFont(new Font("SansSerif", Font.BOLD, 100));
		g.drawString("+", x + 20, y + 80);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked");
		Point p = e.getPoint();
		if(p.getY() >= 450){
			for(int i = 0; i < operatorBag.getCharBag().length; i++){
				if(p.getX() >= 50 + 100*i && p.getX() <= 50 + 100*(i+1)){
					currSelectedOperator = operatorBag.getCharBag()[i];
				}
			}
		}else if(p.getY() >= 150 && p.getY() <= 250){
			for(int j = 0; j < singleBoard.blocks.length; j++){
				if(p.getX() >= (j+1)*(510/(singleBoard.blocks.length + 1)) - 47 + 120 &&
						p.getX() <= (j+2)*(510/(singleBoard.blocks.length + 1)) - 47 + 120){
					positionOnSingleBoard = j;
				}
			}
		}
		if(currSelectedOperator != ' ' && positionOnSingleBoard != 4){
			singleBoard.addBlock(currSelectedOperator, positionOnSingleBoard);
			currSelectedOperator = ' ';
			positionOnSingleBoard = 4;
		}
		
		if(singleBoard.evaluate()){
			System.out.println("Congrats!");
		}else if(singleBoard.isFull()){
			System.out.println("Next Time.");
		}
		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
	
	public void drawBoard(Board b){
		//dealing with 1 input only
		//y is 150 for entire row
		drawingSingleBoard = true;
		singleBoard = b;
	}

	public void drawSubBoardArray(SubBoard[] b) {
		for(int i = 0; i < b.length; i++){
			drawSubBoard(i, b[i]);
		}
	}
	
	private void drawSubBoard(int num, SubBoard sb){
		drawingSubBoard = true;
		if(num == 0){
			subBoard1 = sb;
		}else if(num == 1){
			subBoard2 = sb;
		}else if(num == 2){
			subBoard3 = sb;
		}
	}
	
	public char getCurrSelectedOperator(){
		return currSelectedOperator;
	}
	
	public int getPositionOnSingleBoard(){
		return positionOnSingleBoard;
	}
	
}