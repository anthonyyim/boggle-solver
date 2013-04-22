/**
 * Main class for Boggle solver.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class Main {

	public static void main(String[] args) {
	  BoggleBoard board = new BoggleBoard();
	  
	  Solver.solveWithWordDict(board);
	  Solver.solveWithPrefixDict(board);
	}
}
