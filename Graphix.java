import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
//controls the GUI of the game

public class Graphix extends JPanel implements MouseListener{
	private Container contentPane;
	private JPanel bagPanel;
	private Bag operatorBag;
	
	private char currSelectedOperator;
	private boolean drawingSingleBoard;
	private Board singleBoard;
	
	public Graphix(Puzzle p){
		
		addMouseListener(this);
		operatorBag = p.puzzleBag;
		operatorBag.scramble();
		setSize(800, 600);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//draws the operators in the bag
		for(int i = 0; i < operatorBag.getCharBag().length; i++){
			if(operatorBag.getBagDisplay()[i]){
				g.drawRect(50 + 100*i, 350, 95, 100);
				drawOperator(operatorBag.getCharBag()[i], 50 + 100*i, 350, g);
			}
		}
		
		if(drawingSingleBoard){
			int numSquares = singleBoard.blocks.length + 2;
			int startIndex = (7 - numSquares)/2; //integer division truncates 1.5 to 1 in case of 4 squares
			g.drawRect(50 + startIndex*100, 150, 95, 100); //draws first one
			JLabel startNumLabel = new JLabel("" + singleBoard.startNum);
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
		g.drawLine(x + 47, y + 10, x + 47, y + 90);
		
	}

	private void drawCarat(int x, int y, Graphics g) {
		g.drawLine(x + 10, y + 50, x + 47, y + 10);
		g.drawLine(x + 47, y + 10, x + 85, y + 50);
		
	}

	private void drawMinus(int x, int y, Graphics g) {
		g.drawLine(x + 10, y + 50, x + 85, y + 50);
	}

	private void drawPlus(int x, int y, Graphics g){
		g.drawLine(x + 47, y + 10, x + 47, y + 90);
		g.drawLine(x + 10, y + 50, x + 85, y + 50);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked");

		if(e.getPoint().getY() >= 350){
			for(int i = 0; i < operatorBag.getCharBag().length; i++){
				if(e.getPoint().getX() >= 50 + 100*i && e.getPoint().getX() <= 50 + 100*(i+1)){
					currSelectedOperator = operatorBag.getCharBag()[i];
				}
			}
		}
		System.out.println("CurrSelectedOperator: " + currSelectedOperator);
		
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

	private void drawSubBoardArray(SubBoard[] b) {
	
		
	}
	
}