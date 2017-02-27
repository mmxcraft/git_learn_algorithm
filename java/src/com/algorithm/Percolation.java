import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
   private WeightedQuickUnionUF uf;
   private boolean [] Mark;
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   {
        int ii;
         uf = new WeightedQuickUnionUF(n*n+2);
         for(ii=1; ii<= n; ii++) {
            uf.union(0,ii);
            uf.union(n*n+1, n*(n-1)+ii);
         }
        Mark = new boolean[n*n+2];
        Mark[0] = true;
        Mark[n*n+1] = true;
    }
   public void open(int row, int col)       // open site (row, col) if it is not open already
   {
      int [] dx = {-1,0,1,0};
      int [] dy = { 0,1,0,-1};
      int ii;
      if(row < 1 || col <1)
        throw new java.lang.IllegalArgumentException();
      else {
        Mark[(row-1)*n+col] = true;
      }
      for(ii=0; ii<4; ii++) {
        if((row-1+dx)>0 && (col+dy)>1 && Mark[(row-1+dx)*n+(col+dy)]) {
            uf.union((row-1)*dx+col, (row-1+dx)*n+(col+dy));
        }
      }
   }
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       return Mark[(row-1)*n+col];
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       return true;
   }
   public boolean percolates()              // does the system percolate?
   {
       return true;
   }
   public static void main(String[] args)   // test client (optional)
   {
       WeightedQuickUnionUF test;
       test = new WeightedQuickUnionUF(10);
       int ii;
       test.union(1,4);
       test.union(5,8);
       for(ii=0; ii < 10; ii++)
         System.out.println(test.find(ii));

   }
}
