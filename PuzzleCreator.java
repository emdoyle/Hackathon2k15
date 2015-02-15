/*
 * Filename: PuzzleCreator.java
 * Description:  This class is instantiated to generate puzzles.
 * It randomly generates a finished board and then displays it
 * without the blocks placed.
 */
import java.util.Random;

public class PuzzleCreator {
	private static final int NUM_INPUTS = 2;
	
	private int[] inputArray;
	private char[] operatorArray;
	private SubBoard[] subBoardArray;
	private int SBcounter = 0;//counts number of subBoards
	private int solution = 0;
	private boolean hasSquared = false;
	
	private Random rgen = new Random();
	
	public PuzzleCreator(){
		randomizeInputs();
		randomizeOperators();
		subBoardArray = new SubBoard[(NUM_INPUTS*2)-1];
		for(int i = 0; i < NUM_INPUTS; i++){ //loops through inputs and creates subBoards
			createSubBoard(inputArray[i], rgen.nextInt(1)+1);
			//subBoard has length up to (not including) 4
		}

		/* account for divide by zero */
		while (subBoardArray[1].evaluateResult() == 0) {
			SubBoard newBoard = new SubBoard(subBoardArray[1].startNum, 0, subBoardArray[1].boardSize);
			newBoard = randomizeBoard(newBoard, subBoardArray[1].boardSize);
			subBoardArray[1] = newBoard;
		}

		int nextInput = evalOperator(subBoardArray[0].evaluateResult(),
				subBoardArray[1].evaluateResult(),
				operatorArray[0]);
		//now we have a second-level input
		createSubBoard(nextInput, rgen.nextInt(1)+1);//create subBoard on second-level input
		
		solution = subBoardArray[2].evaluateResult();
	}
	
	private void randomizeInputs(){
		int[] arr = new int[NUM_INPUTS];
		for(int i = 0; i < arr.length; i++){
			arr[i] = rgen.nextInt(15);
		}
		inputArray = arr;
	}

	public char getOperator() {
		return operatorArray[0];
	}
	
	/* evaluates an operation based on the operator passed in and the two values */
	private int evalOperator(int valOne, int valTwo, char operator) {

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
	
	/* selects an operator based on random selection */
	private char getRandomOperator(){
		int randNum = rgen.nextInt(9);
		
		if (randNum < 3) {
			return '+';
		}

		else if (randNum < 6) {
			return '-';
		}

		else if (randNum < 8) {
			return '|';
		}

		else if (randNum < 9 && !hasSquared) {
			hasSquared = true;
			return '^';
		}

		/* recursive call to find a non squared operator */
		else {
			return getRandomOperator();
		}
	}

	/* finds the operator for 2 subboards to execute */
	private char getSubBoardOperator(){
		int randNum = rgen.nextInt(4);
		
		switch(randNum){
		case 0:
			return '+';
		case 1:
			return '-';
		case 2:
			return '*';
		case 3:
			return '/';
		default:
			return ' ';
		}
	}
	
	/* finds an operator randomly */
	private void randomizeOperators(){
		char[] arr = new char[NUM_INPUTS-1];

		for(int i = 0; i < arr.length; i++){
			arr[i] = getSubBoardOperator();
		}
		operatorArray = arr;
	}
	
	private SubBoard randomizeBoard(SubBoard sb, int size){
		for(int i = 0; i < size; i++){
			sb.addBlock(getRandomOperator(), i);
		}
		return sb;
	}
	
	private void createSubBoard(int startNum, int size){
		SubBoard currBoard = new SubBoard(startNum, 0, size);
		currBoard = randomizeBoard(currBoard, size);
		subBoardArray[SBcounter] = currBoard;
		SBcounter++;
	}
	
	/* returns the actual solution to the problem */
	public int getSolution(){
		return solution;
	}
	
	public SubBoard[] getSubBoards(){
		return subBoardArray;
	}
	
	/* returns the bag of operators needed for the solution */
	public Bag getSolutionBag(){
		int totalSize = subBoardArray[0].boardSize +
                subBoardArray[1].boardSize +
                subBoardArray[2].boardSize;
		char[] solnBag = new char[totalSize];
		int iterator = 0;
		for(int j = 0; j < 3; j++){
			for(int i = 0; i < subBoardArray[j].boardSize; i++){
				solnBag[iterator] = subBoardArray[j].blocks[i].getOperator();
				// System.out.println(solnBag[iterator]);
				iterator++;
			}
		}
		/* creates the boolean array filled with true values */
		boolean[] solnDisplayArray = new boolean[totalSize];
		for(int w = 0; w < totalSize; w++){
			solnDisplayArray[w] = true;
		}
		return new Bag(solnBag, solnDisplayArray);
	}
}
