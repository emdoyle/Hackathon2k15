import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GraphixTwo extends JPanel implements MouseListener{
	private Bag operatorBag;
	private JFrame window;
	private SubBoard[] sbArray;
	private Puzzle puzzle;
	
	private int numMoves = 0;
	private boolean restart = false;
	private boolean won = false;
	private boolean drawingBoard = false;
	private char currSelectedOperator = ' ';
	private int positionOnBoard = -1;
	private int currentBoard = -1;

	public GraphixTwo(Puzzle puzzle){
		window = new JFrame();
		setSize(800,600);
		window.setContentPane(this);
		window.setSize(800, 600);
		window.setVisible(true);
		addMouseListener(this);
		
		this.puzzle = puzzle;
		operatorBag = puzzle.puzzleBag;
		sbArray = puzzle.subArr;
		drawBoard();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font("SansSerif", Font.BOLD, 25));
		g.drawString("Moves: " + numMoves, 350, 50);
		//draws the operators in the bag
		for(int i = 0; i < operatorBag.getCharBag().length; i++){
			if(operatorBag.getBagDisplay()[i]){
				g.drawRect(50 + 100*i, 450, 95, 100);
				drawOperator(operatorBag.getCharBag()[i], 50 + 100*i, 450, g);
			}
		}
		
		if(drawingBoard){
			int numBlocks = puzzle.getFirstBoardLength() + 2;
			 //draws first one
			g.drawRect(50, 150, 95, 100);
			g.setFont(new Font("SansSerif", Font.BOLD, 45));
			g.drawString("" + puzzle.getFirstBoardStartNum(), 75, 200);
			
			for(int j = 0; j < sbArray[0].blocks.length; j++){
				// System.out.println("Drawing a block");
				drawBlock(sbArray[0].blocks[j], 50 + 100*(j+1), 150, g);
			}
			
			//now draw board 1
			numBlocks = puzzle.getSecondBoardLength() + 2;
			
			g.drawRect(50, 350, 95, 100);
			g.setFont(new Font("SansSerif", Font.BOLD, 45));
			g.drawString("" + puzzle.getSecondBoardStartNum(), 75, 400);
			
			for(int h = 0; h < sbArray[1].blocks.length; h++){
				drawBlock(sbArray[1].blocks[h], 50 + 100*(h+1), 350, g);
			}
			
			//now draw the operator
			drawOperator(puzzle.getTheOperator(), 250, 250, g);
			
			//now the final subBoard
			numBlocks = sbArray[2].boardSize + 2;
			
			g.drawRect(350 + sbArray[2].blocks.length*100, 250, 95, 100);
			g.setFont(new Font("SansSerif", Font.BOLD, 45));
			g.drawString("" + sbArray[2].startNum, 375 + sbArray[2].blocks.length*100, 300);
			
			for(int t = 0; t < sbArray[2].blocks.length; t++){
				drawBlock(sbArray[2].blocks[t], 350 + 100*t, 250, g);
			}
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
	
	private void drawBlock(Block b, int x, int y, Graphics g){
		if(!b.isEmpty()){
			g.setColor(b.getColor());
			drawOperator(b.getOperator(), x, y, g);
		}else{ g.setColor(Color.BLACK);}
		g.drawRect(x, y, 95, 100);
		
	}
	
	private void drawSlash(int x, int y, Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 80));
		g.drawString("/", x + 40, y + 70);
	}

	private void drawAsterisk(int x, int y, Graphics g) {
		g.setFont(new Font("SansSerif", Font.BOLD, 80));
		g.drawString("*", x + 40, y + 70);
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
	
	private boolean positionClicked(Point p){
		System.out.println("Finding board clicked");
		System.out.println("X VALUE: " + p.getX() + "Y VALUE: " + p.getY());
		if(currentBoard == 0){
			if(p.getX() >= 150 && p.getX() <= 250){
				positionOnBoard = 0;
				return true;
			}else if((sbArray[currentBoard].boardSize == 2) && p.getX() >= 255 && p.getX() <= 355){
				positionOnBoard = 1;
				return true;
			}
		}else if(currentBoard == 1){
			if(p.getX() >= 150 && p.getX() <= 250){
				positionOnBoard = 0;
				return true;
			}else if((sbArray[currentBoard].boardSize == 2) && p.getX() >= 255 && p.getX() <= 355){
				positionOnBoard = 1;
				return true;
			}
		}else if(currentBoard == 2){
			if(p.getX() >= 355 && p.getX() <= 455){
				positionOnBoard = 0;
				return true;
			}else if((sbArray[currentBoard].boardSize == 2) && p.getX() >= 455 && p.getX() <= 555){
				positionOnBoard = 1;
				return true;
			}
		}
		System.out.println("Failed all cases in positionClicked.\npositionOnBoard: " + positionOnBoard + " currentBoard: " + currentBoard);
		return false;
	}
	
	private boolean boardIsClicked(Point p){
		System.out.println("Board clicked!");
		if(p.getY() >= 50 && p.getY() <= 400){
			if(p.getY() >= 150 && p.getY() <= 250 && p.getX() >= 150 && p.getX() <= 150 + 100*(sbArray[0].boardSize)){
				currentBoard = 0;
				return true;
			}else if(p.getY() >= 255 && p.getY() <= 355 && p.getX() >=350 && p.getX() <= 350 + 100*(sbArray[2].boardSize)){
				currentBoard = 2;
				return true;
			}else if(p.getY() >= 360 && p.getY() <= 460 && p.getX() >= 150 && p.getX() <= 150 + 100*(sbArray[1].boardSize)){
				currentBoard = 1;
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		
		if(restart){
			//singleBoard.resetBoard();
			currSelectedOperator = ' ';
			positionOnBoard = -1;
			restart = false;
			if(won){
				numMoves = 0;	
				clearAll();
			}
		}else{
			//has an operator selected
			if(currSelectedOperator != ' ' && boardIsClicked(p) && positionClicked(p)){
				if(sbArray[currentBoard].addBlock(currSelectedOperator, positionOnBoard)){
				numMoves++;
				operatorBag.deleteItem(currSelectedOperator);
				currSelectedOperator = ' ';
				positionOnBoard = -1;}
			}else if(currSelectedOperator == ' '){
				System.out.println("Searching for operator");
				if(p.getY() >= 450){
					for(int i = 0; i < operatorBag.getCharBag().length; i++){
						if(p.getX() >= 50 + 100*i && p.getX() <= 50 + 100*(i+1)
								&& operatorBag.getBagDisplay()[i]){
							currSelectedOperator = operatorBag.getCharBag()[i];
							System.out.println("currSelectedOperator: " + currSelectedOperator);
						}
					}
//				}else if(p.getY() >= 150 && p.getY() <= 250){
//					for(int j = 0; j < singleBoard.blocks.length; j++){
//						if(p.getX() >= (j+1)*(510/(singleBoard.blocks.length + 1)) - 47 + 120 &&
//								p.getX() <= (j+2)*(510/(singleBoard.blocks.length + 1)) - 47 + 120){
//							positionOnBoard = j;
//						}
//					}
//				}
			}
		
		
			if(puzzle.isPuzzleFull() && puzzle.getTheSolution() == sbArray[2].evaluateResult()){
				System.out.println("Congrats!");
				restart = true;
				won = true;
			}else if(puzzle.isPuzzleFull()){
				System.out.println("Next Time.");
				restart = true;
			}
		}
		
		repaint();
	}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public char getCurrSelectedOperator(){
		return currSelectedOperator;
	}
	
	public void drawBoard(){
		drawingBoard = true;
	}
	
	private void clearAll(){
		
		numMoves = 0;
		currSelectedOperator = ' ';
		positionOnBoard = 4;
		drawingBoard = false;

		//singleBoard.resetBoard();
		restart = false;
		won = false;
	}
	
}
