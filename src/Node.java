import java.util.LinkedList;


public class Node {
  char value;
  boolean visited;
  LinkedList<Node> neighbors;

  public Node(char value, boolean visited) {
    this.value = value;
    this.visited = visited;
  }
  
  public void addNeighborList(LinkedList<Node> neighbors) {
    this.neighbors = neighbors;
  }
}
