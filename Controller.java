public class Controller {
	public static void main(String[] args) {
		Board board = new Board(13, 12, 3);
		board.addBlock('+', 2);
		board.addBlock('-', 1);
		board.addBlock('-', 0);
		board.evaluate();
		board.printBoard();
	}
}