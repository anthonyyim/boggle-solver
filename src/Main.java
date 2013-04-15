/**
 * 
 */

/**
 * @author Anthony Yim (anthonyyim@gmail.com)
 * Board: the entity 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	  BoggleBoard board = new BoggleBoard();
	  board.generateRandom();
	  
	  Solver.solveWithDict(board);
	  
	  //Solve.solveWithPrefixTree(board);
	}
}
