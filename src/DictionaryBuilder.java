import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class DictionaryBuilder {

  public static HashMap<String, String> buildWordDictionary(String filePath) {
    // Replace the File constructor's argument with the full path of where a new-line
    // separated dictionary word file is.
    File file = new File(filePath);
    HashMap<String, String> dictionary = new HashMap<String, String>();
    
    // Build dictionary.
    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String word = scanner.nextLine();
        dictionary.put(word, "word");
      } 
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return dictionary;
  }

  public static HashMap<String, String> buildPrefixDictionary(String filePath) {
    // Replace the File constructor's argument with the full path of where a new-line
    // separated dictionary word file is.
    File file =  new File(filePath);
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

    return dictionary;
  }
}
