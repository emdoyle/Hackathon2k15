import java.util.Scanner;

public class Controller {
	public static void main(String[] args) {

		/*
		Board board = new Board(4, 2, 3);
		Scanner input = new Scanner(System.in);
		char[] operators = {'|', '^', '+'};
		boolean[] bools = {true, true, true};
		Bag bag = new Bag(operators, bools);
		boolean success = false;

		while (!success) {
			board.printBoard();
			System.out.println("Select Operator from current Bag: ");
			bag.printBag();

			char chosenOperator = input.next().charAt(0);

			if (!bag.checkBag(chosenOperator)) {
				System.err.println("Invalid input!");
				continue;
			}

			if (!bag.checkBag(chosenOperator)) {
				System.err.println("Operator not in bag!");
				continue;
			}

			System.out.print("Select a location from 1 to ");
			System.out.println(board.blocks.length);
			
			int chosenLocation = input.nextInt();

			if (chosenLocation < 1 || chosenLocation > board.blocks.length) continue;

			board.addBlock(chosenOperator, chosenLocation-1);

			bag.removeNextInstance(chosenOperator);

			if (board.isFull()) {
				success = board.evaluate();
				if (!success) {
					bag.resetBag();
					board.resetBoard();
					System.out.println("-----------------");
				} else board.printBoard();
			}
		}*/

		
		PuzzleCreator pc = new PuzzleCreator();
		SubBoard[] sb = pc.getSubBoards();
		/*sb[0].printBoard();
		System.out.println("----------");
		sb[2].printBoard();
		sb[1].printBoard();
*/

/*
		Board board = new Board(4, 2, 3);
		Scanner input = new Scanner(System.in);
		char[] operators = {'|', '^', '+'};
		boolean[] bools = {true, true, true};
		Bag bag = new Bag(operators, bools);*/

		boolean success = false;
		Scanner input = new Scanner(System.in);
		Bag bag = pc.getSolutionBag();
		bag.scramble();

		sb[0].resetBoard();
		sb[1].resetBoard();
		sb[2].resetBoard();

		while (!success) {
			/* print boards */
			sb[0].printBoard();
			System.out.print("----------------");
			System.out.print("| " + pc.getOperator() + " | ");
			sb[2].printOnlyEnd(pc.getSolution());
			sb[1].printBoard();

			System.out.println("Select Operator from current Bag: ");
			bag.printBag();

			char chosenOperator = input.next().charAt(0);

			if (!bag.checkBag(chosenOperator)) {
				System.err.println("Invalid input!");
				continue;
			}

			if (!bag.checkBag(chosenOperator)) {
				System.err.println("Operator not in bag!");
				continue;
			}

			System.out.println("Select a board: ");

			int chosenBoard = input.nextInt();

			if (chosenBoard < 0 || chosenBoard > 2 || sb[chosenBoard].isFull()) {
				System.err.println("Invalid board!");
				continue;
			}

			System.out.print("Select a location: ");
			
			int chosenLocation = input.nextInt();

			if (chosenLocation < 0 || chosenLocation >= sb[chosenBoard].blocks.length) {
				System.err.println("Location out of bounds!");
				continue;
			}

			if (!sb[chosenBoard].addBlock(chosenOperator, chosenLocation)) {
				continue;
			}

			bag.removeNextInstance(chosenOperator);

			if (sb[0].isFull() && sb[1].isFull() && sb[2].isFull()) {
				success = sb[2].evaluateResult() == pc.getSolution();
				if (!success) {
					bag.resetBag();
					sb[0].resetBoard();
					sb[1].resetBoard();
					sb[2].resetBoard();
					System.out.println("Incorrect answer!");
					System.out.println("-----------------");
				} else {
					/* print boards */
					sb[0].printBoard();
					System.out.print("----------------");
					System.out.print("| " + pc.getOperator() + " | ");
					sb[2].printOnlyEnd(pc.getSolution());
					sb[1].printBoard();
					System.out.println("Puzzle solved!");
				}	
			}
		}
	}
}