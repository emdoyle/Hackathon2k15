/* Filename: Board.java
 * Description: Board for the display of the game which includes the Block[],
 * 				current top index, beginning number and ending number 
 */

public class Board {
	Block[] blocks;
	int startNum;
	int endNum;
	int boardSize;

	/* Board constructor initializes an array of empty blocks into every space */
	public Board(int start, int end, int size) {
		blocks = new Block[size];
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = new Block(' ', true);
		}

		startNum = start;
		endNum = end;
		boardSize = size;
	}

	/* check if block[] is empty */
	public boolean isEmpty() {
		for (int i = 0; i < blocks.length; i++) {
			if (!blocks[i].isEmpty()) return false;
		}
		return true;
	}

	/* check if block[] is full */
	public boolean isFull() {
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].isEmpty()) return false;
		}
		return true;
	}

	/* displays the board on the command prompt */
	public void printBoard() {
		System.out.print("| " + startNum + " | ");
		for (int i = 0; i < blocks.length; i++) {
			System.out.print(blocks[i].getOperator() + " | ");
		}
		System.out.println(endNum + " |");
	}

	/* adds a block: returns true for a successful add; false otherwise */
	public boolean addBlock(char operator, int index) {
		if (index < 0 || index >= blocks.length) {
			System.err.println("Invalid index!");
			return false;
		}

		if (!(blocks[index].isEmpty())) {
			System.err.println("Space filled!");
			return false;
		}

		if (isFull()) {
			System.err.println("Error: full board!");
			return false;
		}

		blocks[index].setUpBlock(operator);

		return true;
	}

	/* deletes a block if the array is not empty */
	public void deleteBlock(int index) {
		if (index < 0 || index >= blocks.length) {
			System.err.println("Invalid index!");
		}

		else if (!isEmpty()) {
			blocks[index].setUpBlock(' ');
		}
	}

	/* reset the board and delete all the current blocks */
	public void resetBoard() {
		for (int i = 0; i < blocks.length; i++) {
			deleteBlock(i);
		}
	}

	/* evaluates an operation based on the operator passed in and the two values */
	public int evalOperator(int valOne, int valTwo, char operator) {

		switch (operator) {
			case '+':
				return valOne + valTwo;
			case '-':
				return valOne - valTwo;
			case '*':
				return valOne * valTwo;
			case '/':
				return valOne / valTwo;
			case '^':
				return valOne * valOne;
			case '|':
				return valOne / 10;
		}
		return 0;
	}

	/* calls the evalOperator function and determines the outcome */
	public boolean evaluate() {
		int total = 0;
		if (!isFull()) {
			// System.err.println("Board not filled!");
			return false;
		}

		else {
			total = startNum;
			for (int i = 0; i < blocks.length; i++) {
				total = evalOperator(total, 1, blocks[i].getOperator());
			}
		}

		if (total == endNum) {
			System.out.println("Puzzle solved!");
			return true;
		}

		System.out.println("Incorrect answer!");
		return false;
	}
}