import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double results[];

    //perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n < 1 || trials < 1)
            throw new IllegalArgumentException();

        results = new double[trials];

        for (int x = 0; x < trials; x++) {
            results[x] = calculateResults(n);
        }

    }

    private double calculateResults(int n) {

        Percolation percolation = new Percolation(n);
        int openSites = percolation.numberOfOpenSites();

        while(!percolation.percolates()) {
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;
            if(!percolation.isOpen(row, col)) {
                percolation.open(row, col);
            }
        }

        return (double) openSites / (n*n);

    }

    //sample mean of percolation threshold
    public double mean() {

        return StdStats.mean(results);
    }

    //sample standard deviation of percolation threshold
    public double stddev() {

        return StdStats.stddev(results);
    }

    //low endpoint of 95% confidence interval
    public double confidenceLo() {

        return mean() - (1.96 * stddev() / Math.sqrt(results.length));
    }

    //high endpoint of 95% confidence interval
    public double confidenceHi() {

        return mean() + (1.96 * stddev() / Math.sqrt(results.length));
    }

    //test client
    public static void main (String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("The Mean is: " + percolationStats.mean());
        System.out.println("The Standard Deviation is: " + percolationStats.stddev());
        System.out.println("The 95% confidence interval is: " + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi());
    }
}
