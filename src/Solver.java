import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Solver {

  public static void solveWithDict(BoggleBoard board) {
    // Replace the File constructor's argument with the full path of where your new-line
    // separated dictionary word file is.
    File file = new File("/home/tacocat/Code/Boggle-Solver/src/english_dict.txt");
    HashMap<String, String> dictionary = new HashMap<String, String>();
    
    // Build dictionary.
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String word = scanner.nextLine();
        dictionary.put(word, word);
      } 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    /*
     * Solve recursively for each possible starting point on the Boggle board.
     */
    Node[][] boardArray = board.getBoggleBoardArray();

    System.out.println("\n" + "Begin solving...");

    for (int i = 0; i < boardArray.length; i++) {
      for (int j = 0; j < boardArray.length; j++) {
        if (boardArray[i][j].value != '0') {
          Node node = boardArray[i][j];
          String wordSoFar = String.valueOf(node.value);
          node.visited = true;
          solveRecursivelyWithoutPrefix(dictionary, wordSoFar, node);
          node.visited = false;
        }
      }
    }

    System.out.println("\n" + "Finished solving.");
  }

  public static void solveWithPrefixDict(BoggleBoard board) {
    File file =  new File("/home/tacocat/Code/Boggle-Solver/src/english_dict.txt");
    HashMap<String, String> dictionary = new HashMap<String, String>();
    
    // Build prefix dictionary.
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String word = scanner.nextLine();

        // Generate prefixes
        for (int i = 1; i <= word.length(); i++) {
          String substring = word.substring(0, i);
          if (i == word.length()) {
            dictionary.put(substring, "word");
          } else {
            dictionary.put(substring, "prefix");
          }
        }
      } 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    /*
     * Solve recursively for each possible starting point on the Boggle board.
     * TODO (anthonyyim): Duplicate code! Refactor!
     */
    Node[][] boardArray = board.getBoggleBoardArray();

    System.out.println("\n" + "Begin solving...");

    for (int i = 0; i < boardArray.length; i++) {
      for (int j = 0; j < boardArray.length; j++) {
        if (boardArray[i][j].value != '0') {
          Node node = boardArray[i][j];
          String wordSoFar = String.valueOf(node.value);
          node.visited = true;
          solveRecursivelyWithPrefix(dictionary, wordSoFar, node);
          node.visited = false;
        }
      }
    }

    System.out.println("\n" + "Finished solving.");
  }

  private static void solveRecursivelyWithoutPrefix(HashMap<String, String> dictionary, String wordSoFar, Node node) {
    //System.out.println(wordSoFar);
    
    if (wordSoFar.length() >= 16){
      // Base case
      if (dictionary.containsKey(wordSoFar)){
        System.out.println("Answer: " + wordSoFar);
      }
    } else {      
      if (dictionary.containsKey(wordSoFar) && wordSoFar.length() >= 3) {
        System.out.println("Answer: " + wordSoFar);
      }
      
      for(Node neighbor : node.neighbors) {
        if (!neighbor.visited) {
          neighbor.visited = true;
          solveRecursivelyWithoutPrefix(dictionary, wordSoFar.concat(String.valueOf(neighbor.value)), neighbor);
          neighbor.visited = false;
        }
      }
    }
  }

  private static void solveRecursivelyWithPrefix(HashMap<String, String> dictionary, String wordSoFar, Node node) {
    //System.out.println(wordSoFar);
 
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

      for(Node neighbor : node.neighbors) {
        if (!neighbor.visited) {
          neighbor.visited = true;
          solveRecursivelyWithPrefix(dictionary, wordSoFar.concat(String.valueOf(neighbor.value)), neighbor);
          neighbor.visited = false;
        }
      }
    }
  }
}
