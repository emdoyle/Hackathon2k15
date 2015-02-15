import java.util.Scanner;

public class Controller {
	public static void main(String[] args) {
		
		/*SubBoard sb = new SubBoard(15, 0, 3);
		SubBoard sb2 = new SubBoard(20, 0, 2);
		sb.addBlock('+', 0);
		sb.addBlock('-', 1);
		sb.addBlock('+', 2);

		sb2.addBlock('+', 0);
		sb2.addBlock('+', 1);

		int resOne = sb.evaluateResult();
		int resTwo = sb2.evaluateResult();
		int multBoth = resOne * resTwo;

		Board board = new Board(multBoth, 30, 4);

		sb.printBoard();
		System.out.print("-------------------");
		board.printBoard();
		sb2.printBoard();*/

		Board board = new Board(10, 12, 4);
		Scanner input = new Scanner(System.in);
		char[] operators = {'+', '-', '+', '+'};
		boolean[] bools = {true, true, true, true};
		Bag bag = new Bag(operators, bools);
		boolean success = false;

		while (!success) {
			board.printBoard();
			System.out.println("Current Bag: ");
			bag.printBag();

			System.out.println("Select operator: + -");
			char chosenOperator = input.next().charAt(0);

			if (chosenOperator != '+' && chosenOperator != '-') {
				System.err.println("Invalid input!");
				continue;
			}

			if (!bag.checkBag(chosenOperator)) {
				System.err.println("Operator not in bag!");
				continue;
			}

			System.out.print("Now select a location from 1 to ");
			System.out.println(board.blocks.length);
			
			int chosenLocation = input.nextInt();

			if (chosenLocation < 1 || chosenLocation > board.blocks.length) continue;

			board.addBlock(chosenOperator, chosenLocation-1);

			System.out.println("Removing an instance");
			bag.removeNextInstance(chosenOperator);

			if (board.isFull()) {
				success = board.evaluate();
				if (!success) {
					bag.resetBag();
				}
				board.printBoard();
			}


		}

		//char[] operators = {'*', '/', '*'};
		//Operators ops = new Operators(3, operators);

	}
}