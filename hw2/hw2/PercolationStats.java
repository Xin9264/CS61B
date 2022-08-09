package hw2;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.StdRandom;

import java.util.Random;

public class PercolationStats {
    int T;
    double[] result;
    Percolation p;
    int count;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N <= 0 || T <= 0){
            throw new IllegalArgumentException("N and T must > 0");
        }
        result = new double[T];
        for (int i = 0; i < T; i++){
            count = 0;
            p = pf.make(N);
            while (!p.percolates()){
                int x = StdRandom.uniform(0, N);
                int y = StdRandom.uniform(0, N);
                p.open(x, y);
                count ++;
            }
            result[i] = (double) count / T;
        }

    }
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(result);
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(result);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow(){
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHigh(){
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
