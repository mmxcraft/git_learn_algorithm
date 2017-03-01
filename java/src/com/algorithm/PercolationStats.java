import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    private Percolation percolation;
    private double [] perThreshold;
    private int siteSz;
    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        siteSz = n;
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        perThreshold = new double[trials];
        int ii;
        for (ii = 0; ii < trials; ii++) {
            percolation = new Percolation(n);
            while (!percolation.percolates())
            {
                int jj = StdRandom.uniform(n) + 1;
                int kk = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(jj, kk))
                    percolation.open(jj, kk);
             }
             double numOpensite = percolation.numberOfOpenSites();
             perThreshold[ii] = numOpensite/(n*n);
            percolation = null;
        }
    }
    public double mean()                   // sample mean of percolation threshold
    {
        return StdStats.mean(perThreshold);
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(perThreshold);
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return stddev() - 1.96*stddev()/Math.sqrt(siteSz);
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return stddev() + 1.96*stddev()/Math.sqrt(siteSz);
    }

    public static void main(String[] args)        // test client (described below)
    {
        int siteSz = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats perco  = new PercolationStats(siteSz, trials);
        StdOut.printf("mean = %f\n", perco.mean());
        StdOut.printf("stddev = %f\n", perco.stddev());
        StdOut.printf("95%% confidence interval = [ %f , %f ] \n", perco.confidenceLo(), perco.confidenceHi());

    }
}

