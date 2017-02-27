import java.util.*;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class Solver {
    private MinPQ<Board> BoardList;
    private Board initBoard;
    private int moves;
    public Solver(Board initial)     // find a solution to the initial board (using the A* algorithm)
    {
        class BoardCmp implements Comparator<Board> {
            public int compare(Board p1, Board p2) {
                return p1.manhattan() - p2.manhattan();
            }
        }

        BoardCmp cmp = new BoardCmp();
        BoardList = new MinPQ<Board>(cmp);
        BoardList.insert(initial);
        initBoard = initial;
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        if(initBoard.twin() == initBoard)
            return false;
        else
            return true;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return 0;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        LinkedList<Board> usedBoardList = new LinkedList<Board>();
        LinkedList<Board> SolutionBoardList = new LinkedList<Board>();

        //usedBoardList.add(initial);
        moves = 0;
        while (!BoardList.isEmpty()) {
            Board curBoard = BoardList.delMin();
            if(curBoard.isGoal())
                return SolutionBoardList;
            Queue<Board> allNeighbors = curBoard.neighbors();
            moves++;
            while (!allNeighbors.isEmpty()){
                Board newBoard = allNeighbors.poll();
                if (usedBoardList.indexOf(newBoard)== -1 ) {
                    BoardList.insert(newBoard);
                    usedBoardList.add(newBoard);
                }
            }
        }



        return null;

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