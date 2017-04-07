// source: http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
import java.util.Iterator;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;


public class Solver1 {
    private MinPQ<Node> searchQueue;
    private MinPQ<Node> searchQueue2; // for solvability detection
    private ArrayList<Board1> solution;
    private boolean solvable = false;
    private int moves;

    public Solver1(Board1 initial) {          // find a solution to the initial board (using the A* algorithm)
        if (initial == null)
            throw new java.lang.NullPointerException();

        searchQueue = new MinPQ<>();
        searchQueue.insert(new Node(initial, 0, null));
        Node minNode = null;

        searchQueue2 = new MinPQ<>();
        searchQueue2.insert(new Node(initial.twin(), 0, null));
        Node minNode2;

        boolean alt = false;    // used for switching back and forth
        for (;;) {
            if (!alt) {
                for (Node tmpBoard: searchQueue)
                    StdOut.printf("moves=%d, manhattan=%d,%s",tmpBoard.moves(),tmpBoard.manhattan(),tmpBoard.Board());

                minNode = searchQueue.delMin();
                if (minNode.board.isGoal()) { // problem solved from original initial
                    solvable = true;
                    moves = minNode.move;
                    break;
                }
                Iterable<Board1> neighbors = minNode.board.neighbors();
                for (Board1 each : neighbors) {
                    if (minNode.prev == null || !each.equals(minNode.prev.board)) {
                        searchQueue.insert(new Node(each, minNode.move + 1, minNode));
                    }
                }

                alt = true;
            }
            else { // alternative search from twin initial
                minNode2 = searchQueue2.delMin();
                if (minNode2.board.isGoal()) // problem solved from twin initial
                    break;

                Iterable<Board1> neighbors = minNode2.board.neighbors();
                for (Board1 each : neighbors) {
                    if (minNode2.prev == null || !each.equals(minNode2.prev.board)) {
                        searchQueue2.insert(new Node(each, minNode2.move + 1, minNode2));
                    }
                }

                alt = false;
            }

        }

        if (solvable) {
            solution = new ArrayList<>();
            Node cur = minNode;
            while (cur != null) {
                solution.add(cur.board);
                cur = cur.prev;
            }
        }
    }

    private class Node implements Comparable<Node> {
        private int move;
        private int manhattan = -1;
        private int hamming = -1;
        private Board1 board;
        private Node prev;

        public Node(Board1 b, int m, Node p) {
            board = b;
            prev = p;
            move = m;
        }

        public int manhattan() {
            if (manhattan > 0)
                return manhattan;

            return move + board.manhattan();
        }

        public int hamming() {
            if (hamming > 0)
                return hamming;

            return move + board.hamming();
        }

        public int compareTo(Node that) {
            // return hamming() - that.hamming();
            return manhattan() - that.manhattan();
        }
        public int moves() {
            return move;
        }

        public Board1 Board() {
            return board;
        }

    }

    public boolean isSolvable() {           // is the initial board solvable?
        return solvable;
    }

    public int moves() {                   // min number of moves to solve initial board; -1 if unsolvable
        if (!solvable)
            return -1;
        return moves;
    }

    public Iterable<Board1> solution() {     // sequence of boards in a shortest solution; null if unsolvable
        if (!solvable) {
            return null;
        }
        else {
            return new SolutionBoards();
        }
    }

    private class SolutionBoards implements Iterable<Board1> {
        public Iterator<Board1> iterator() {
            return new SolutionIter();
        }

        class SolutionIter implements Iterator<Board1> {
            private int index = solution.size() - 1;

            public boolean hasNext() {
                return index > -1;
            }

            public Board1 next() {
                return solution.get(index--);
            }
        }
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board1 initial = new Board1(blocks);

        // solve the puzzle
        Solver1 solver = new Solver1(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board1 board : solver.solution())
                StdOut.println(board);
        }
    }
}
