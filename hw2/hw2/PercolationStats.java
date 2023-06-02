package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] X;
    public PercolationStats(int N, int T, PercolationFactory pf) {   // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("arguments error");
        }

        Percolation examp = pf.make(N);
        X = new double[T];

        for (int i = 0; i < T; i++) {
            examp.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
            X[i] = examp.numberOfOpenSites() / N * N;
        }

    }
    public double mean() {                                           // sample mean of percolation threshold
        return StdStats.mean(X);
    }
    public double stddev() {
        return StdStats.stddev(X);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(X.length);
    }                                  // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(X.length);
    }                                 // high endpoint of 95% confidence interval

}
