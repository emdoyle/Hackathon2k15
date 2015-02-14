public class Controller {
	public static void main(String[] args) {
		SubBoard sb = new SubBoard(15, 0, 3);
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
		sb2.printBoard();


	}
}