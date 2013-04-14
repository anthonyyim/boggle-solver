import java.util.LinkedList;


public class Node {
  char value;
  boolean visited;
  LinkedList<Node> neighbors;

  public Node(char value, boolean visited, LinkedList<Node> neighbors) {
    this.value = value;
    this.visited = visited;
    this.neighbors = neighbors;
  }

  public static Node buildGraph(String input) {
    //do something
  }
}
