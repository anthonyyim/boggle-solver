import java.util.Iterator;


public class BoggleBoardIterator implements Iterator {
  private Node[][] boardArray;
  private boolean hasNextNode = false;
  private Node nextNode;
  private int rowIndex = 1;
  private int colIndex = 1;

  public BoggleBoardIterator(Node[][] boardArray) {
    this.boardArray = boardArray;
    if (boardArray[rowIndex][colIndex].getValue() != '0'
         && boardArray[rowIndex][colIndex] != null) {
      hasNextNode = true;
      nextNode = boardArray[rowIndex][colIndex];
    }
  }

  public boolean hasNext() {
    return hasNextNode;
  }

  public Node next() {
    Node retNode = nextNode;
    calcNextNode();
    return retNode;
  }

  private void calcNextNode() {
    colIndex++;
    if (boardArray[rowIndex][colIndex].getValue() == '0') {
      colIndex = 1;
      rowIndex++;
    }

    if (boardArray[rowIndex][colIndex].getValue() != '0') {
      nextNode = boardArray[rowIndex][colIndex];
      hasNextNode = true;
    } else {
      hasNextNode = false;
    }
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }
}
