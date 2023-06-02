package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] X;
    int T;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("arguments error");
        }

        X = new double[T];
        this.T = T;

        for (int i = 0; i < T; i++) {
            Percolation examp = pf.make(N);
                do {
                    int x = StdRandom.uniform(N);
                    int y = StdRandom.uniform(N);
                    examp.open(x, y);
                } while (!examp.percolates());
            X[i] = (double) examp.numberOfOpenSites() / (N * N);
        }
    }
    public double mean() {
        // sample mean of percolation threshold
        return StdStats.mean(X);
    }
    public double stddev() {
        return StdStats.stddev(X);
    }
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(T);
    }
    public double confidenceHigh() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(T);
    }

}
