
import java.util.Stack;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdOut;


public class Board {
    private int [][] myBoard;
    private int blankRow, blankCol;

    private class BoardCmp implements Comparator<Board> {
        public int compare(Board p1, Board p2) {
            return p2.manhattan() - p1.manhattan();
        }
    }

    public Board(int[][] blocks) // construct a board from an n-by-n array of blocks
    {
        int len = blocks.length;
        myBoard = new int [len][len];
        for (int ii = 0; ii < len; ii++)
            for (int jj = 0; jj < len; jj++) {
                myBoard[ii][jj] = blocks[ii][jj];
                if (blocks[ii][jj] == 0) {
                    blankRow = ii;
                    blankCol = jj;
                }
            }
    }
    public int dimension()                 // board dimension n
    {
        return myBoard.length;
    }
    public int hamming() // number of blocks out of place
    {
        int len = dimension();
        int outNum = 0;
        for (int ii = 0; ii < len; ii++)
            for (int jj = 0; jj < len; jj++)
            {
                if (myBoard[ii][jj] != ii*len+jj+1 && myBoard[ii][jj] != 0)
                    outNum++;
            }
        return outNum;
    }
    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        int ii, jj;
        int result = 0;
        int dimSize = dimension();
        for (ii = 0; ii < dimSize; ii++) {
            for (jj = 0; jj < dimSize; jj++) {
                if (myBoard[ii][jj] == 0)
                    continue;
                else {
                    int kk = Math.abs((dimSize * ii + jj + 1) - myBoard[ii][jj]);
                    if (kk == 0)
                        continue;
                    else {
                        int rowsMove = Math.abs((myBoard[ii][jj]-1) / dimSize - ii);
                        int colsMove = Math.abs((myBoard[ii][jj]-1) % dimSize - jj);
                        result = result + rowsMove + colsMove;
                    }
                }
            }
        }

        return result;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        int len = dimension();
        for (int ii = 0; ii < len; ii++)
            for (int jj = 0; jj < len; jj++)
            {
                if (myBoard[ii][jj] != ii*len+jj+1 && myBoard[ii][jj] != 0)
                    return false;
            }
        return true;
    }
    public Board twin()
    {
        int dimSize = dimension();
        int [][]  twinBlocks = new int [dimSize][dimSize];
        for (int ii = 0; ii < dimSize; ii++)
            for (int jj = 0; jj < dimSize; jj++)
                twinBlocks[ii][jj] = myBoard[ii][jj];

        int x1, x2, y1, y2;
        x1 = -1;
        x2 = -1;
        y1 = 0;
        y2 = 0;
        for (int ii = 0; ii < dimSize; ii++)
         for (int jj = 0; jj < dimSize; jj++) {
                if (twinBlocks[ii][jj] != 0 && x1 == -1) {
                    x1 = ii;
                    y1 = jj;
                    continue;
                }
                if (twinBlocks[ii][jj] != 0 && x2 == -1) {
                    x2 = ii;
                    y2 = jj;
                }
                if (x1 != -1 && x2 != -1) {
                    int tmpvlaue = twinBlocks[x1][y1];
                    twinBlocks[x1][y1] = twinBlocks[x2][y2];
                    twinBlocks[x2][y2] = tmpvlaue;
                    return new Board(twinBlocks);
                }
            }

            return null;
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        for (int ii = 0; ii < dimension(); ii++)
            for (int jj = 0; jj < dimension(); jj++)
            {
                if (this.myBoard[ii][jj] != that.myBoard[ii][jj])
                    return false;
            }

        return true;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
        int dimSize = dimension();
        Stack<Board> allNeighbors = new Stack<Board>();

       // Queue<Board> allNeighbors =  new LinkedList<Board>();
        int[] rows = {0, -1, 0, 1};
        int[] cols = {-1, 0, 1, 0};

        for (int ii = 0; ii < 4; ii++) {
            int curRow = blankRow + rows[ii];
            int curCol = blankCol + cols[ii];
            if (curRow >= 0 && curRow < dimension() && curCol >= 0 && curCol < dimension())
            {
                int [][]  tmpblock = new int[dimSize][dimSize];
                for (int kk = 0; kk < dimSize; kk++)
                    for (int jj = 0; jj < dimSize; jj++)
                        tmpblock[kk][jj] = myBoard[kk][jj];

                tmpblock[blankRow] [blankCol] = tmpblock[curRow][curCol];
                tmpblock[curRow][curCol] = 0;
                Board newBoard = new Board(tmpblock);
                allNeighbors.push(newBoard);
            }
        }
        return allNeighbors;
    }
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", myBoard[i][j]));
            }
            s.append("\n");
        }
        return s.toString();

    }

    public static void main(String[] args) // unit tests (not graded)
    {
        /* class BoardCmp implements Comparator<Board> {
            public int compare(Board p1, Board p2) {
                return p2.manhattan() - p1.manhattan();
            }
        }

        BoardCmp cmp = new BoardCmp();
        MinPQ<Board> BoardList = new MinPQ<Board>(cmp);*/




        int [][] test =  {{5, 8, 7}, {1, 4, 6}, {3, 0, 2}};
        int [][] test1 = {{ 2, 0, 3, 4}, { 1, 10, 6, 8}, {5, 9, 7, 12}, {13, 14, 11, 15} };
        // int [][] test2 = {{1,2,3}, {4,0,6}, {7,5,8}};
        // int [][] test2 = test1.clone();
        // test2[0][0] = -1;


        Board B1 = new Board(test);
       Board B2 = new Board(test1);

       StdOut.printf("B1.manhattan() =%d, B2.manhattan()=%d \n",B1.manhattan(),B2.manhattan());
        StdOut.print(B2.twin());
        StdOut.print(B2);

        /* Board B1 = new Board(test);
        Board B2 = new Board(test1);
        StdOut.printf("B2.manhattan= %d\n", B2.manhattan());

        usedBoardList.add(B1);
        usedBoardList.add(B2);

        StdOut.printf("index=%d\n",usedBoardList.indexOf(B2));

        StdOut.print(B2);

        BoardList.insert(new Board(test));
        BoardList.insert(new Board(test1)); */




    }
}
