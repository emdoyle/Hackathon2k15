
public class Board {
	Block[] blocks;
	int currentIndex;
	int startNum;
	int endNum;

	public Board(int start, int end, int size) {
		blocks = new Block[size];
		for (int i = 0; i < blocks.length; i++) {
			blocks[i] = new Block(' ', true);
		}

		currentIndex = -1;
		startNum = start;
		endNum = end;
	}

	public void printBoard() {
		System.out.print("| " + startNum + " | ");
		for (int i = 0; i < blocks.length; i++) {
			System.out.print(blocks[i].getOperator() + " | ");
		}
		System.out.println(endNum + " |");
	}

	public boolean addBlock(char operator) {
		if (currentIndex >= blocks.length-1) {
			System.err.println("Error: full board!");
			return false;
		}

		currentIndex++;
		blocks[currentIndex].setUpBlock(operator);

		return true;
	}

	public void deleteBlock() {
		if (currentIndex != -1) {
			blocks[currentIndex].setEmpty(true);
			currentIndex--;
		}
	}

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
		}
		return 0;
	}

	public boolean evaluate() {
		int total = 0;
		if (currentIndex < (blocks.length-1)) {
			System.err.println("Board not filled");
			return false;
		}

		else {
			total = startNum;
			System.out.println(total);
			for (int i = 0; i < blocks.length; i++) {
				total = evalOperator(total, 1, blocks[i].getOperator());
				System.out.println(total);
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