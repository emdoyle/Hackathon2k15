import java.util.Arrays;

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

		currentIndex = -1;
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

		currentIndex++;
		operators[currentIndex] = operator;

		return true;
	}

	public void deleteBlock() {
		if (currentIndex != -1) {
			operators[currentIndex] = ' ';
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
		if (currentIndex < (operators.length-1)) {
			System.err.println("Board not filled");
			return false;
		}

		else {
			total = evalOperator(startNum, 1, operators[0]);
			for (int i = 0; i < operators.length-1; i++) {
				total = evalOperator(total, 1, operators[i]);
			}
		}

		if (total == endNum) {
			System.err.println("Puzzle solved!");
			return true;
		}

		System.err.println("Incorrect answer!");
		return false;
	}
}