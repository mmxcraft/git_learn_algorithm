import java.util.ArrayList;
import java.util.Collections;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private MinPQ<Node> boardList;
    private MinPQ<Node> twinBoardList;
    private boolean canResolve;
    private Node minNode;
    private int move;
    private ArrayList<Board> solutionBoardList;



   private class Node implements Comparable<Node> {
       private Board board;
       private int moves;
       private Node prev;
       private int priority = -1;

       Node(Board newBoard, int newMoves, Node p)
       {
           board = newBoard;
           moves = newMoves;
           prev = p;

       }

       public int priority()
       {
           if (priority >0)
               return priority;

           return moves + board.manhattan();
       }
       public int compareTo(Node that)
       {
           return this.priority() - that.priority();
       }

       public Board getBoard()   { return board; }
    }

   public Solver(Board initBoard)     // find a solution to the initial board (using the A* algorithm)
    {
        if (initBoard == null)
            throw new java.lang.NullPointerException();

        canResolve = false;
        minNode = null;

        boardList = new MinPQ<Node>();
        twinBoardList = new MinPQ<Node>();
        boardList.insert(new Node(initBoard, 0, null));
        twinBoardList.insert(new Node(initBoard.twin(), 0, null));

        solutionBoardList = new ArrayList<>();
        realSolution();

    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return canResolve;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if (canResolve)
            return move;
        else
            return -1;
    }
    public Iterable<Board> solution()
    {
        if (!canResolve)
            return null;

        while (minNode != null) {
            solutionBoardList.add(minNode.board);
            minNode = minNode.prev;
        }
        Collections.reverse(solutionBoardList);
        return solutionBoardList;
    }

    private void realSolution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        boolean alt = false;
        while (!boardList.isEmpty()) {
            if (!alt) {
             //    for (Node tmpBoard: boardList)
             //       StdOut.printf("moves=%d, priority=%d,%s",tmpBoard.moves,tmpBoard.priority(),tmpBoard.board);
                minNode = boardList.delMin();
                if (minNode.getBoard().isGoal()) {
                    canResolve = true;
                    move = minNode.moves;
                    break;
                }

                for (Board each: minNode.getBoard().neighbors())
                {
                    if (minNode.prev == null || !each.equals(minNode.prev.board)) {
                        boardList.insert(new Node(each, minNode.moves+1, minNode));
                    }
                }
                alt = true;
            } else {
                Node minTwinNode = twinBoardList.delMin();
                if (minTwinNode.board.isGoal())
                    break;

                for (Board each: minTwinNode.board.neighbors()) {
                    if (minTwinNode.prev == null || !each.equals(minTwinNode.prev.board))
                        twinBoardList.insert(new Node(each, minTwinNode.moves+1, minTwinNode));
                }
                alt = false;
            }
        }
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
