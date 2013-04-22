/**
 * Main class for Boggle solver.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class Main {

	public static void main(String[] args) {
	  BoggleBoard board = new BoggleBoard();
	  
	  // TODO (anthonyyim@gmail.com): move generate random into constructor of Boggle board.
	  board.generateRandom();
	  
	  Solver.solveWithWordDict(board);
	  Solver.solveWithPrefixDict(board);
	}
}
