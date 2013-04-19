import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A class containing several different implementations of English dictionaries for
 * constant time lookups.
 *
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class DictionaryBuilder {

  /**
   * Builds a hash table of complete words given a text file of words. E.g. if file contains
   * three words:
   * 1. foo
   * 2. bar
   * 3. cats
   * 
   * Hash table will be of size 3 containing foo, bar, and cats.
   *
   * @param filePath The full path of a text file containing list of new line separated words. 
   * @return HashMap of all words in provided dictionary keyed on the words.
   */
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

  /**
   * Builds a hash table of all possible prefixes of each word (always include the first letter)
   * and the word itself. E.g. if the text file contains two words:
   * 1. foo
   * 2. bar
   * 
   * Hash table will be of size 6 containing f, fo, foo, b, ba, and bar.
   *
   * @param filePath The full path of a text file containing list of new line separated words.
   * @return HashMap of all prefixes and words in provided dictionary keyed on the prefixes 
   *   and words.
   */
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
          } else if (dictionary.containsKey(substring) != true){
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
