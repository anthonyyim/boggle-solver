import java.util.HashMap;


/**
 * Class contains several different algorithms for solving Boggle.
 *
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class Solver {
  private static long iterationCounterForWordSolver = 0L;
  private static long iterationCounterForPrefixSolver = 0L;

  public static void solveWithWordDict(BoggleBoard board) {
    HashMap<String, String> dictionary = DictionaryBuilder
        .buildWordDictionary("/home/tacocat/Code/Boggle-Solver/src/english_dict.txt");

    System.out.println("\n" + "Begin solving using hashmap of words...");

    // Solve recursively for each possible starting point on the Boggle board.
    for(Node node : board) {
      String wordSoFar = String.valueOf(node.getValue());
      node.setVisited(true);
      solveRecursivelyWithWord(dictionary, wordSoFar, node);
      node.setVisited(false);
    }
    
    System.out.println("\n" + "Finished solving.");
    System.out.println("\n" + "Num of iterations: " + iterationCounterForWordSolver);
    System.out.println("Dictionary size: " + dictionary.size());
  }

  public static void solveWithPrefixDict(BoggleBoard board) {
    HashMap<String, String> dictionary = DictionaryBuilder
        .buildPrefixDictionary("/home/tacocat/Code/Boggle-Solver/src/english_dict.txt");

    System.out.println("\n" + "Begin solving using hashmap of prefixes and words (w/ pruning)...");

    // Solve recursively for each possible starting point on the Boggle board.
    for (Node node : board) {
      String wordSoFar = String.valueOf(node.getValue());
      node.setVisited(true);
      solveRecursivelyWithPrefix(dictionary, wordSoFar, node);
      node.setVisited(false);
    }

    System.out.println("\n" + "Finished solving.");
    System.out.println("\n" + "Num of iters: " + iterationCounterForPrefixSolver);
    System.out.println("Dictionary size: " + dictionary.size());
  }

  /*
   * Recursively find all combinations of letters that make up a word. Halting (hitting a base
   * case) when a 16 letter combination is formed or when there is no additional unused
   * (unvisited) letter to be added to the current combination. 
   */
  private static void solveRecursivelyWithWord(HashMap<String, String> dictionary, String wordSoFar, Node node) {
    //System.out.println(wordSoFar);
    iterationCounterForWordSolver++;

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

  /*
   * Recursively find all combinations of letters that make up a word. Halting (hitting a base
   * case) when a 16 letter combination is formed or when there is no additional unused
   * (unvisited) letter to be added to the current combination. In addition, do not continue to recurse
   * if the current combination of letters cannot possibly form a word (i.e. no such prefix exists).
   */
  private static void solveRecursivelyWithPrefix(HashMap<String, String> dictionary, String wordSoFar, Node node) {
    //System.out.println(wordSoFar);
    iterationCounterForPrefixSolver++;
    
    if (dictionary.containsKey(wordSoFar) != true) {
      // Base case (do nothing) - no such prefix or word in dictionary.
    } else {
      if (dictionary.get(wordSoFar).equals("word") && wordSoFar.length() >= 3) {
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
