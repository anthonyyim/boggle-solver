import java.util.LinkedList;

/**
 * Node representation of a single letter on a Boggle board. Used to build a 
 * (traversable) graph representation of a Boggle board.
 *
 * @author anthonyyim@gmail.com (Anthony Yim)
 */
public class Node {
  private char value;
  private boolean visited;
  private LinkedList<Node> neighbors;

  public Node(char value, boolean visited) {
    this.value = value;
    this.visited = visited;
  }

  public char getValue() {
    return value;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(boolean visited) {
    this.visited = visited;
  }

  public LinkedList<Node> getNeighbors() {
    return neighbors;
  }

  public void setNeighborList(LinkedList<Node> neighbors) {
    this.neighbors = neighbors;
  }
}
