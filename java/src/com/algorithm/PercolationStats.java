import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    private Percolation percolation;
    private double [] perThreshold;
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if(n <= 0 || trials <= 0 )
            throw java.lang.IllegalArgumentException;
        percolation = new Percolation(n);
        perThreshold = new double[trails];
        int ii;
        for(ii = 0; ii < trials; ii++) {
            while(!percolation.percolates())
            {
                int jj = StdRandom.uniform(n) + 1;
                int kk = StdRandom.uniform(n) + 1;
                if(!percolation.isOpen(jj, kk))
                    percolation.open(jj, kk);
             }
        }

    }
    public double mean()                   // sample mean of percolation threshold
    {

    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {

    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {}
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {}

    public static void main(String[] args)        // test client (described below)
    {}
}

