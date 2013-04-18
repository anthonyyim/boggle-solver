import java.util.HashMap;

/**
 * Solver class.
 *
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class Solver {
  private static long iterationCounterForWordSolver = 0L;
  private static long iterationCounterForPrefixSolver = 0L;
  
  public static void solveWithWordDict(BoggleBoard board) {
    HashMap<String, String> dictionary = DictionaryBuilder
        .buildWordDictionary("/home/tacocat/Code/Boggle-Solver/src/english_dict.txt");

    System.out.println("\n" + "Begin solving...");
    Node[][] boardArray = board.getBoggleBoardArray();

    // Solve recursively for each possible starting point on the Boggle board.
    // TODO (anthonyyim): Refactor BoggleBoard to implement Enumeration interface.
    for (int i = 0; i < boardArray.length; i++) {
      for (int j = 0; j < boardArray.length; j++) {
        if (boardArray[i][j].getValue() != '0') {
          Node node = boardArray[i][j];
          String wordSoFar = String.valueOf(node.getValue());
          node.setVisited(true);
          solveRecursivelyWithWord(dictionary, wordSoFar, node);
          node.setVisited(false);
        }
      }
    }

    System.out.println("\n" + "Finished solving.");

    System.out.println("\n" + "Num of iterations: " + iterationCounterForWordSolver);
    System.out.println("Dictionary size: " + dictionary.size());
  }

  public static void solveWithPrefixDict(BoggleBoard board) {
    HashMap<String, String> dictionary = DictionaryBuilder
        .buildPrefixDictionary("/home/tacocat/Code/Boggle-Solver/src/english_dict.txt");

    /*
     * Solve recursively for each possible starting point on the Boggle board.
     * TODO (anthonyyim): Duplicate code! Refactor!
     */
    Node[][] boardArray = board.getBoggleBoardArray();

    System.out.println("\n" + "Begin solving...");

    for (int i = 0; i < boardArray.length; i++) {
      for (int j = 0; j < boardArray.length; j++) {
        if (boardArray[i][j].getValue() != '0') {
          Node node = boardArray[i][j];
          String wordSoFar = String.valueOf(node.getValue());
          node.setVisited(true);
          solveRecursivelyWithPrefix(dictionary, wordSoFar, node);
          node.setVisited(false);
        }
      }
    }

    System.out.println("\n" + "Finished solving.");
    System.out.println("\n" + "Num of iters: " + iterationCounterForPrefixSolver);
    System.out.println("Dictionary size: " + dictionary.size());
  }

  private static void solveRecursivelyWithWord(HashMap<String, String> dictionary, String wordSoFar, Node node) {
    //System.out.println(wordSoFar);
    iterationCounterForWordSolver++;

    if (wordSoFar.length() >= 16){
      // Base case
      if (dictionary.containsKey(wordSoFar)){
        System.out.println("Answer: " + wordSoFar);
      }
    } else {      
      if (dictionary.containsKey(wordSoFar) && wordSoFar.length() >= 3) {
        System.out.println("Answer: " + wordSoFar);
      }
      
      for(Node neighbor : node.getNeighbors()) {
        if (!neighbor.isVisited()) {
          neighbor.setVisited(true);
          solveRecursivelyWithWord(dictionary, wordSoFar.concat(String.valueOf(neighbor.getValue())), neighbor);
          neighbor.setVisited(false);
        }
      }
    }
  }

  private static void solveRecursivelyWithPrefix(HashMap<String, String> dictionary, String wordSoFar, Node node) {
    //System.out.println(wordSoFar);
    iterationCounterForPrefixSolver++;
    
    if (wordSoFar.length() >= 16) {
      // Base case #1 - max Boggle word length
      if (dictionary.containsKey(wordSoFar)) {
        if (dictionary.get(wordSoFar).equals("word")) {
          System.out.println("Answer: " + wordSoFar);
        }
      }
    } else if (!dictionary.containsKey(wordSoFar)) {
      // Base case #2 - no such prefix or word in dictionary.
      // Do nothing.
    } else {
      if (dictionary.get(wordSoFar).equals("word")) {
        System.out.println("Answer: " + wordSoFar);
      }

      for(Node neighbor : node.getNeighbors()) {
        if (!neighbor.isVisited()) {
          neighbor.setVisited(true);
          solveRecursivelyWithPrefix(dictionary, wordSoFar.concat(String.valueOf(neighbor.getValue())), neighbor);
          neighbor.setVisited(false);
        }
      }
    }
  }
}
