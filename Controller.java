public class Controller {
	public static void main(String[] args) {
		Board board = new Board(13, 12, 3);
		board.addBlock('+');
		board.addBlock('-');
		board.addBlock('-');
		board.evaluate();
		board.printBoard();
	}
}