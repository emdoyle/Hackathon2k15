public class Controller{
	public static void main(String[] args) {

		char[] tutOps = {'+'};
		boolean[] tutBools = {true};
		Bag tutBag = new Bag(tutOps, tutBools);
		Board tutorialBoard = new Board(2, 3, 1);

		new Graphix(tutorialBoard, tutBag);
	}
}