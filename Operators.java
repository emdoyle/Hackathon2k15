public class Operators {
	char[] operators;
	int currentIndex;

	public Operators(int size, char[] theOps) {
		operators = theOps;
		currentIndex = 0;
	}

	public int evalOp(int valOne, int valTwo) {
		if (currentIndex >= operators.length) {
			return 0;
		}

		int result = evalOperator(valOne, valTwo, operators[currentIndex]);
		currentIndex++;
		return result;
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

}