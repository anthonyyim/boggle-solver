import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Solver {

  public static void solveWithDict(Node node) {
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
    
    System.out.println("\n" + "Begin solving...");
    // TODO (anthonyyim): put below in nested for loop to iterate through every starting position.
    String wordSoFar = String.valueOf(node.value);
    solveRecursively(dictionary, wordSoFar, node);
    System.out.println("\n" + "Finished solving.");
  }

  private static void solveRecursively(HashMap<String, String> dictionary, String wordSoFar, Node node) {
    // Base case - wordSoFar is a dict. word. If there a valid word within a longer valid word, the
    // longer word will not be found.
    // TODO (anthonyyim): add prefix checker.

    //System.out.println(wordSoFar);

    if (dictionary.containsKey(wordSoFar) && wordSoFar.length() >= 3){
      System.out.println("Answer: " + wordSoFar);
    } else if (wordSoFar.length() >= 16) {
      // Do nothing - second base case.  
    } else {
      for(Node neighbor : node.neighbors) {
        if (!neighbor.visited) {
          neighbor.visited = true;
          solveRecursively(dictionary, wordSoFar.concat(String.valueOf(neighbor.value)), neighbor);
          neighbor.visited = false;
        }
      }
    }
  }
}
