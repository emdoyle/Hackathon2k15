public class Controller {
	public static void main(String[] args) {
		Board board = new Board(13, 15, 2);
		board.addBlock('+');
		board.addBlock('+');
		board.evaluate();
		board.printBoard();
	}
}