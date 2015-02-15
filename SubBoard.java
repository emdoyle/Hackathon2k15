/* Filename: SubBoard.java
 * Description: Board for the display of the sub boards of the game that
 *				are used to evaluate a sections of the board
 */

public class SubBoard extends Board {


	/* Board constructor initializes an array of empty blocks into every space */
	public SubBoard(int startNum, int endNum, int size) {
		super(startNum, endNum, size);
	}

	/* displays the board on the command prompt */
	public void printBoard() {
		System.out.print("| " + startNum + " | ");
		for (int i = 0; i < blocks.length; i++) {
			System.out.print(blocks[i].getOperator() + " | ");
		}
		System.out.println();
	}

	/* getter for total */
	public int getTotal() {
		return endNum;
	}


	/* calls the evalOperator function and determines the outcome */
	public int evaluateResult() {
		if (!isFull()) {
			System.err.println("Board not filled!");
			return 0;
		}

		else {
			endNum = startNum;
			for (int i = 0; i < blocks.length; i++) {
				endNum = evalOperator(endNum, 1, blocks[i].getOperator());
			}
		}
		return endNum;
	}
}