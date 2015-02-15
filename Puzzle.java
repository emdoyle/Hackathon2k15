/*
 * Filename: Puzzle.java
 * Description: Creates a puzzle instance, which contains an empty puzzle to be solved.  It
 * 				makes use of the puzzle creator to get randomly generated problems.
 */

public class Puzzle {

	private int firstBoardStartNum;
	private int firstBoardLength;
	private int secondBoardStartNum;
	private int secondBoardLength;
	private char theOperator;
	// private int resultOfOperation;
	private int finalBoardLength;
	private int solution;
	public Bag puzzleBag;
	private PuzzleCreator pc;
	public SubBoard[] subArr;

	public Puzzle() {
		/* create a new puzzle solution */
		pc = new PuzzleCreator();
		subArr = pc.getSubBoards();

		/* get the bag of operators */
		puzzleBag = pc.getSolutionBag();
		puzzleBag.scramble();

		/* set all the board lengths and start nums */
		firstBoardStartNum = subArr[0].startNum;
		firstBoardLength = subArr[0].blocks.length;
		secondBoardStartNum = subArr[1].startNum;
		secondBoardLength = subArr[1].blocks.length;
		finalBoardLength = subArr[2].blocks.length;

		/* get the operator and final result from the puzzle generator */
		theOperator = pc.getOperator();
		solution = pc.getSolution();

		/* reset the boards */
		resetAllBoards();
	}

	/* returns true if all the subboards are full; false otherwise */
	public boolean isPuzzleFull() {
		if (subArr[0].isFull() && subArr[1].isFull() && subArr[2].isFull()) return true;
		return false;
	}

	/* clears all the boards */
	public void resetAllBoards() {
		subArr[0].resetBoard();
		subArr[1].resetBoard();
		subArr[2].resetBoard();
	}

	/* prints out the puzzle */
	public void displayPuzzle() {
			subArr[0].printBoard();
			System.out.print("----------------");
			System.out.print("| " + pc.getOperator() + " | ");
			subArr[2].printOnlyEnd(pc.getSolution());
			subArr[1].printBoard();
	}

	/* checks if the last subboard is equal to the solution */
	public boolean isCorrect() {
		if (subArr[2].evaluateResult() == solution) {
			System.out.println("result: " + subArr[2].evaluateResult());
			System.out.println("solution: " + solution);
			return true;
		}
		return false;
	}


	/* Getters / Setters */
	public int getFirstBoardStartNum() {
		return firstBoardStartNum;
	}

	public int getFirstBoardLength() {
		return firstBoardLength;
	}

	public int getSecondBoardStartNum() {
		return secondBoardStartNum;
	}

	public int getSecondBoardLength() {
		return secondBoardLength;
	}

	public int getFinalBoardLength() {
		return finalBoardLength;
	}

	public int getTheOperator() {
		return theOperator;
	}

	public int getTheSolution() {
		return solution;
	}

}