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
	
	private Random rgen = new Random();
	
	public PuzzleCreator(){
		randomizeInputs();
		randomizeOperators();
		subBoardArray = new SubBoard[(NUM_INPUTS*2)-1];
		for(int i = 0; i < NUM_INPUTS; i++){ //loops through inputs and creates subBoards
			createSubBoard(inputArray[i], rgen.nextInt(4));
			//subBoard has length up to (not including) 4
		}
		int nextInput = evalOperator(subBoardArray[0].evaluateResult(),
				subBoardArray[1].evaluateResult(),
				operatorArray[0]);
		//now we have a second-level input
		createSubBoard(nextInput, rgen.nextInt(4));//create subBoard on second-level input
		
		solution = subBoardArray[2].evaluateResult();
	}
	
	private void randomizeInputs(){
		int[] arr = new int[NUM_INPUTS];
		for(int i = 0; i < arr.length; i++){
			arr[i] = rgen.nextInt(15);
		}
		inputArray = arr;
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
		}
		return 0;
	}
	
	private char getRandomOperator(){
		int randNum = rgen.nextInt(NUM_INPUTS);
		
		switch(randNum){
		case 0:
			return '+';
		case 1:
			return '-';
		default:
			return ' ';
		}
	}
	
	private void randomizeOperators(){
		char[] arr = new char[NUM_INPUTS-1];

		for(int i = 0; i < arr.length; i++){
			arr[i] = getRandomOperator();
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
	
	public int getSolution(){
		return solution;
	}
	
	public SubBoard[] getSubBoards(){
		return subBoardArray;
	}
}
