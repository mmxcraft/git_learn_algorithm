
import java.util.*;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int [][] myBoard;
    private int blank_row, blank_col;

    public Board(int[][] blocks) // construct a board from an n-by-n array of blocks
    {
        int len = blocks.length;
        myBoard = new int [len][len];
        for(int ii = 0; ii < len; ii++)
            for(int jj = 0; jj < len; jj++) {
                myBoard[ii][jj] = blocks[ii][jj];
                if(blocks[ii][jj] == 0) {blank_row=ii; blank_col=jj;}
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
        for(int ii = 0; ii < len; ii++)
            for(int jj = 0; jj < len; jj++)
            {
                if(myBoard[ii][jj] != ii*len + jj)
                    outNum++;
            }
        return outNum;
    }
    public int manhattan() // sum of Manhattan distances between blocks and goal
    {
        int ii, jj;
        int result = 0;
        for (ii = 0; ii < dimension(); ii++) {
            for (jj = 0; jj < dimension(); jj++) {
                if (myBoard[ii][jj] == 0)
                    continue;
                else {
                    int kk = (dimension() * ii + jj + 1) - myBoard[ii][jj];
                    if (kk == 0)
                        continue;
                    else {
                        int rows_move = kk / dimension();
                        int cols_move = kk % dimension();
                        result = result + rows_move + cols_move;
                    }
                }
            }
        }

        return result;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        int len = dimension();
        for(int ii = 0; ii < len; ii++)
            for(int jj = 0; jj < len; jj++)
            {
                if(myBoard[ii][jj] != ii*len + jj + 1)
                    return false;
            }
        return true;
    }
    public Board twin()    // a board that is obtained by exchanging any pair of blocks
    {
        if(blank_row == dimension() && blank_col == dimension() && hamming() == 2)
            return this;

        int [][]  twinBlocks = new int [dimension()] [dimension()];
        int tmpPos = -1;
        for(int ii = 0; ii < dimension(); ii ++)
        {
            if(tmpPos != -1 && twinBlocks[0][ii] != 0)
            {
                int tmpValue = twinBlocks[0][ii];
                twinBlocks[0][ii] = twinBlocks[0][tmpPos];
                twinBlocks[0][tmpPos] = tmpValue;
                break;
            }

            if(twinBlocks[0][ii] != 0 )
                tmpPos = ii;
        }
        return new Board(twinBlocks);
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;

        for(int ii = 0; ii < dimension(); ii++)
            for (int jj = 0; jj < dimension(); jj++)
            {
                if(this.myBoard[ii][jj] != that.myBoard[ii][jj])
                    return false;
            }

        return true;
    }
    public Queue<Board> neighbors()     // all neighboring boards
    {
        int dimSize = dimension();
        int [][] neighborBlocks = new int[dimSize][dimSize];
        for(int ii = 0; ii < dimSize; ii++)
            for(int jj = 0; jj < dimSize; jj++)
            {
                if(neighborBlocks[ii][jj] !=0)
                    continue;
                else{

                }
            }


        //Queue<Board> queBoard = new Queue<Board>(myBoard);

        Queue<Board> all_neighbors =  new LinkedList<Board>();
        int rows[] = {0,-1,0,1};
        int cols[] = {-1,0,1,0};

        for(int ii = 0 ; ii < 4; ii++) {
            int cur_row = blank_row + rows[ii];
            int cur_col = blank_col + cols[ii];
            if(cur_row >= 0 && cur_row < dimension() && cur_col >= 0 && cur_col < dimension()) {
                int [][]  tmpblock = new int [dimension()] [dimension()];
                tmpblock = myBoard;
                tmpblock[blank_row] [blank_col] = tmpblock[cur_row][cur_col];
                tmpblock[cur_row][cur_col] = 0;
                Board newBoard = new Board(tmpblock);
                all_neighbors.offer(newBoard);
            }
        }
        return all_neighbors;
    }
    public String toString()               // string representation of this board (in the output format specified below)
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
        class BoardCmp implements Comparator<Board> {
            public int compare(Board p1, Board p2) {
                return p1.manhattan() - p2.manhattan();
            }
        }

        BoardCmp cmp = new BoardCmp();
        MinPQ<Board> BoardList = new MinPQ<Board>(cmp);
        int [][] test = {{3,2,1}, {4, 0, 5}, {7,6,8}};
        int [][] test1 = {{1,2,3}, {4,5,6}, {7,8,0}};

        LinkedList<Board> usedBoardList = new LinkedList<Board>();

        Board B1 = new Board(test);
        Board B2 = new Board(test1);
        StdOut.printf("B2.manhattan= %d\n", B2.manhattan());

        usedBoardList.add(B1);
        usedBoardList.add(B2);

        StdOut.printf("index=%d\n",usedBoardList.indexOf(B2));

        StdOut.print(B2);

        BoardList.insert(new Board(test));
        BoardList.insert(new Board(test1));




    }
}
