
import java.util.LinkedList;
import java.util.Queue;
import edu.princeton.cs.algs4.MinPQ;

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
        int dimension = dimension();
        int outNum = 0;
        for(int ii = 0; ii < len; ii++)
            for(int jj = 0; jj < len; jj++)
            {
                if(myBoard[ii][jj] != ii*dimension + jj)
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
                    int kk = myBoard[ii][jj] - (dimension() * ii + jj + 1);
                    if (kk == 0)
                        cointue;
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
        int dimension = dimension();
        for(int ii = 0; ii < len; ii++)
            for(int jj = 0; jj < len; jj++)
            {
                if(myBoard[ii][jj] != ii*dimension + jj)
                    return false;
            }
        return true;
    }
    public Board twin()    // a board that is obtained by exchanging any pair of blocks
    {
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

    }

    public static void main(String[] args) // unit tests (not graded)
    {

    }
}
