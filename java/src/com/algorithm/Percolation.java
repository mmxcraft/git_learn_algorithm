import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private boolean [] mark;
    private int numOpenSites;
    private int siteSz;
    private boolean init;
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        int ii;
         uf = new WeightedQuickUnionUF(n*n+2);
         /* for (ii = 1; ii <= n; ii++) {
            uf.union(0, ii);
            uf.union( n*n + 1, n*(n-1)+ii);
         } */

        if (n < 1) throw new java.lang.IllegalArgumentException();
        mark = new boolean[n*n+2];
        mark[0] = true;
        mark[n * n + 1] = true;
        numOpenSites = 0;
        siteSz = n;
        init = false;
    }
    public void open(int row, int col)       // open site (row, col) if it is not open already
    {
        int [] dx = {-1, 0, 1, 0};
        int [] dy = { 0, 1, 0, -1};
        int ii;
        int n = siteSz;
        int newX=1;
        int newY=1;

        if (row < 1 || col < 1 || row > siteSz || col > siteSz)
            throw new java.lang.IndexOutOfBoundsException();

        if (isOpen(row, col)) return;
        init = false;

        if (row < 1 || col < 1 || row > n || col > n)
            throw new java.lang.IndexOutOfBoundsException();
        else {
            mark[(row - 1) * n + col] = true;
            numOpenSites++;
        }

        for (ii = 0; ii < 4; ii++) {
            newX = row + dx[ii];
            newY = col + dy[ii];
            if (newX <1 || newY <1 || newX > n || newY > n)
                continue;

            if (mark[(newX - 1) * n + newY]) {
                uf.union((row - 1) * n + col, (newX - 1) * n + newY);
            }

            if (row == 1) {
                uf.union(0, (row - 1) * n + col);
            }

            /* if (row == n || newX == n) {
                if (isFull(row, col) || isFull(newX, newY)) {
                    if(row == n)
                        uf.union(n*n + 1, (row-1)*n + col);
                    if(newX == n)
                        uf.union(n*n+1, (newX-1)*n+newY);
                }
            } */
        }
    }

   public boolean isOpen(int row, int col)  // is site (row, col) open?
   {
       if (row < 1 || col < 1 || row > siteSz || col > siteSz)
           throw new java.lang.IndexOutOfBoundsException();
       return mark[(row - 1) * siteSz + col];
   }
   public boolean isFull(int row, int col)  // is site (row, col) full?
   {
       if (row < 1 || col < 1 || row > siteSz || col > siteSz)
           throw new java.lang.IndexOutOfBoundsException();
       return uf.connected(0, (row - 1)* siteSz + col);

   }
    public     int numberOfOpenSites()
    {
        return numOpenSites;
    }
    public boolean percolates()              // does the system percolate?
   {
       if (!init) {
           int n = siteSz;
           for(int jj = 1; jj <= n; jj++) {
               if(isOpen(n,jj) && isFull(n,jj)) {
                   uf.union(n*n+1, (n-1)*n+jj);
               }
           }
           init = true;
       }

       return  uf.connected(0, siteSz * siteSz + 1);
   }
   public static void main(String[] args)   // test client (optional)
   {
       int [] allInts = In.readInts(args[0]);
       Percolation testPerco = new Percolation(allInts[0]);
       for (int ii = 1; ii < allInts.length; ii = ii + 2)
       {
           int p = allInts[ii];
           int q = allInts[ii+1];
           testPerco.open(p, q);
       }

       StdOut.printf("open site num=%d\n", testPerco.numberOfOpenSites());

       if (testPerco.percolates()) {
           StdOut.print("This site is percolates");
       } else {
           StdOut.print("Not percolates");
       }

   }
}
