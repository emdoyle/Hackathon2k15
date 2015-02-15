import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
//controls the GUI of the game

public class Graphix extends JPanel implements MouseListener{
	private Bag operatorBag;
	private JFrame window;
	
	private int numMoves = 0;
	private char currSelectedOperator = ' ';
	private int positionOnSingleBoard = 4;
	private boolean drawingSingleBoard;
	private boolean drawingSubBoard;
	private Board singleBoard;
	private boolean restart = false;
	private boolean won = false;
	private boolean isPainted = false;
	private int levelCounter = 0;
	
	private Board[] boards = {new Board(4, 6, 2), new Board(20, 2, 1), new Board(19, 2, 2)};
	char[] arr1 = {'+', '+'};
	char[] arr2 = {'|'};
	char[] arr3 = {'|', '+'};
	boolean[] bool1 = {true, true};
	boolean[] bool2 = {true};
	boolean[] bool3 = {true, true};
	private Bag[] bags = {new Bag(arr1, bool1), new Bag(arr2, bool2),
			new Bag(arr3, bool3)};
	private String[] textArray = {
			"Squares marked with a '+' add 1 to your current total.",
			"You can also add multiple squares in a row.",
			"The patented slice operator slices off the ones digit.",
			"Sometimes there's more than one solution!"
	};
	
	
	public Graphix(Board board, Bag bag){
		window = new JFrame();
		window.setContentPane(this);
		window.setSize(800, 600);
		window.setVisible(true);
		addMouseListener(this);
		operatorBag = bag;
		setSize(800, 600);
		
		singleBoard = board;
		drawBoard(singleBoard);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("SansSerif", Font.BOLD, 25));
		g.drawString("Moves: " + numMoves, 350, 50);
		if(!isPainted){
			g.setFont(new Font("SansSerif", Font.BOLD, 24));
			g.drawString(textArray[levelCounter], 50, 300);
			isPainted = true;
		}
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
			g.setColor(Color.BLACK);
			g.drawRect(605, 150, 95, 100);
			g.setFont(new Font("SansSerif", Font.BOLD, 45));
			g.drawString("" + singleBoard.endNum, 630, 200);
		}
	}
	
	private void drawBlock(Block b, int x, int y, Graphics g){
		//g.setColor(b.getColor());
		//g.drawRect(x, y, 95, 100);
		if(!b.isEmpty()){
			g.setColor(b.getColor());
			drawOperator(b.getOperator(), x, y, g);
		}else{ g.setColor(Color.BLACK);}
		g.drawRect(x, y, 95, 100);
		
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
	
	private boolean operatorIsClicked(Point p){
		if(p.getY() >= 450){
			for(int i = 0; i < operatorBag.getCharBag().length; i++){
				if(p.getX() >= 50 + 100*i && p.getX() <= 50 + 100*(i+1)){
					currSelectedOperator = operatorBag.getCharBag()[i];
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean positionIsClicked(Point p){
		if(p.getY() >= 150 && p.getY() <= 250){
			for(int j = 0; j < singleBoard.blocks.length; j++){
				if(p.getX() >= (j+1)*(510/(singleBoard.blocks.length + 1)) - 47 + 120 &&
						p.getX() <= (j+2)*(510/(singleBoard.blocks.length + 1)) - 47 + 120){
					positionOnSingleBoard = j;
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		
		if(restart){
			singleBoard.resetBoard();
			currSelectedOperator = ' ';
			positionOnSingleBoard = 4;
			restart = false;
			if(won){
				numMoves = 0;	
				clearAll();
				if(levelCounter < 3){
					singleBoard = boards[levelCounter];
					drawingSingleBoard = true;
					operatorBag = bags[levelCounter];
					levelCounter++;
					isPainted = false;
				}else if(levelCounter == 3){
					window.setVisible(false);
					window.dispose();
					new GraphixTwo(new Puzzle());
				}
			}
		}else{
			//has an operator selected
			if(currSelectedOperator != ' ' && positionIsClicked(p)){
				if(singleBoard.addBlock(currSelectedOperator, positionOnSingleBoard)){
				numMoves++;
				operatorBag.deleteItem(currSelectedOperator);
				currSelectedOperator = ' ';
				positionOnSingleBoard = 4;}
			}else if(currSelectedOperator == ' '){
				if(p.getY() >= 450){
					for(int i = 0; i < operatorBag.getCharBag().length; i++){
						if(p.getX() >= 50 + 100*i && p.getX() <= 50 + 100*(i+1)
								&& operatorBag.getBagDisplay()[i]){
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
			}
		
		
			if(singleBoard.evaluate()){
				System.out.println("Congrats!");
				restart = true;
				won = true;
			}else if(singleBoard.isFull()){
				System.out.println("Next Time.");
				restart = true;
			}
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
	
	public char getCurrSelectedOperator(){
		return currSelectedOperator;
	}
	
	public int getPositionOnSingleBoard(){
		return positionOnSingleBoard;
	}
	
	private void clearAll(){
		
		numMoves = 0;
		currSelectedOperator = ' ';
		positionOnSingleBoard = 4;
		drawingSingleBoard = false;

		singleBoard.resetBoard();
		restart = false;
		won = false;
	}
	
}