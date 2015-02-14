public class Board {
	char[] operators;
	int currentIndex;
	int startNum;
	int endNum;

	public Board(int start, int end, int size) {
		operators = new char[size];
		for (int i = 0; i < operators.length; i++) {
			operators[i] = ' ';
		}

		currentIndex = 0;
		startNum = start;
		endNum = end;
	}

	public void printBoard() {
		System.out.print("| " + startNum + " | ");
		for (int i = 0; i < operators.length; i++) {
			System.out.print(operators[i] + " | ");
		}
		System.out.println(endNum + " |");
	}

	public boolean addBlock(char operator) {
		if (currentIndex >= operators.length) {
			System.err.println("Error: full board!");
			return false;
		}

		operators[currentIndex] = operator;
		currentIndex++;
		return true;
	}

	public void deleteBlock() {
		if (currentIndex != 0) {
			operators[currentIndex] = ' ';
			currentIndex--;
		}
	}


}