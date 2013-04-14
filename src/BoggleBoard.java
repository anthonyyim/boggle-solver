import java.util.LinkedList;
import java.util.Random;


public class BoggleBoard {
  public static final int BOGGLE_SIZE = 4;
  public static final int BOARD_SIZE = BOGGLE_SIZE + 2;
  Node[][] boardArray;
  
  public BoggleBoard() {}
  
  public void generateRandom() {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    Random rand = new Random();

    // Fill in boggle board randomly and print board to standard out.
    boardArray = new Node[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        // Create Boggle board with padding of 0's around edge.
        if (i == 0 || i == BOARD_SIZE - 1 || j == 0 || j == BOARD_SIZE - 1) {
          boardArray[i][j] = new Node('0', false);
        } else {
          boardArray[i][j] = new Node(alphabet.charAt(rand.nextInt(alphabet.length())), false);
        }

        System.out.print(boardArray[i][j].value);
      }
      System.out.print("\n");
    }
    
    // Generate graph from randomly generated Boggle board.
    for (int i = 1; i < BOARD_SIZE-1; i++) {
      for (int j = 1; j < BOARD_SIZE-1; j++) {
        LinkedList<Node> neighborList = new LinkedList<Node>();

        // Top left neighbor
        if(boardArray[i-1][j-1].value != '0') {
          neighborList.add(boardArray[i-1][j-1]);
        }

        // Top mid neighbor
        if(boardArray[i-1][j].value != '0') {
          neighborList.add(boardArray[i-1][j]);
        }

        // Top right neighbor
        if(boardArray[i-1][j+1].value != '0') {
          neighborList.add(boardArray[i-1][j+1]);
        }

        // Mid left neighbor
        if(boardArray[i][j-1].value != '0') {
          neighborList.add(boardArray[i][j-1]);
        }

        // Mid right neighbor
        if(boardArray[i][j+1].value != '0') {
          neighborList.add(boardArray[i][j+1]);
        }

        // Bottom left neighbor
        if(boardArray[i+1][j-1].value != '0') {
          neighborList.add(boardArray[i+1][j-1]);
        }

        // Bottom mid neighbor
        if(boardArray[i+1][j].value != '0') {
          neighborList.add(boardArray[i+1][j]);
        }

        // Bottom right neighbor
        if(boardArray[i+1][j+1].value != '0') {
          neighborList.add(boardArray[i+1][j+1]);
        }
        
        boardArray[i][j].addNeighborList(neighborList);
      }
    }

    // Print graph to check method.
    // TODO (anthonyyim): remove this or move to private debug method.
    for (int i = 1; i < BOARD_SIZE-1; i++) {
      for (int j = 1; j < BOARD_SIZE-1; j++) {
        System.out.print(boardArray[i][j].value + ": ");
        String neighbors = "";
        for(Node node : boardArray[i][j].neighbors) {
          neighbors += node.value;
        }
        
        System.out.println(neighbors);
      }
    }
  }

  public Node getBoggleBoardGraph() {
    return boardArray[1][1];
  }
}
