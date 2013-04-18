/**
 * Main class for Boggle solver.
 * 
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class Main {

	public static void main(String[] args) {
	  BoggleBoard board = new BoggleBoard();
	  board.generateRandom();
	  
	  //Bug in prefix solver.
	  Solver.solveWithPrefixDict(board);
	  //Solver.solveWithWordDict(board);
	  
	  //Solver.solveWithPrefixDict(board);
	}
}
