package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF wq;
    int[][] grid;
    int size;
    int[] x = {0, 0, -1, 1};
    int[] y = {-1, 1, 0, 0};
    int number_of_site;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if (N <= 0){
            throw new IllegalArgumentException("N must > 0");
        }
        grid = new int[N][N];
        size = N;
        // 0 -> blocked
        wq = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                grid[i][j] = 0;
            }
        }
        number_of_site = 0;
    }

    private void is_out_of_range(int row, int col){
        if ((row < 0 || col < 0) || (row > size-1 || col > size-1)){
            throw new IndexOutOfBoundsException("index is out of prescribed range");
        }
    }

    private int xyTo1D(int row, int col){
        return row * size + col;
    }

    private void union_4dir(int row, int col){
        /*
        检查4个方向是不是open, 如果是，就union
         */
        for (int i = 0; i < 4; i++){
            if((row - x[i] < size  && col - y[i] < size) && (row - x[i] >= 0 && col - y[i] >= 0)){
                if (grid[row][col] != 0 && grid[row-x[i]][col - y[i]] != 0){
                    wq.union(xyTo1D(row, col), xyTo1D(row-x[i], col-y[i]));
                }
            }
        }
    }

    private void initial_union(int[][]grid, int row, int col){
        int i = row;
        int j = col;
        if (i == 0){
            if (grid[i][j] != 0){
                wq.union(xyTo1D(i, j), size * size);
                union_4dir(i, j);
            }
        }
        else if (i == size - 1) {
            if (grid[i][j] != 0){
                wq.union(xyTo1D(i, j), size * size + 1);
                union_4dir(i, j);
            }
        }
        else {
            union_4dir(i, j);
        }

    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        if (isOpen(row, col)){
            return;
        }
        grid[row][col] = 1;
        initial_union(grid, row, col);
        number_of_site++;
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        is_out_of_range(row, col);
        return grid[row][col] != 0;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return wq.connected(xyTo1D(row, col), size * size);
    }
    // number of open sites
    public int numberOfOpenSites(){
        return number_of_site;
    }
    // does the system percolate?
    public boolean percolates(){
        return wq.connected(size * size + 1, size * size );
    }
    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){
        Percolation p = new Percolation(3);
        p.open(0, 1);
        p.open(1, 1);
        System.out.println(p.numberOfOpenSites());
        System.out.println("p is full? " + p.isFull(1, 1));
        System.out.println(p.percolates());
        p.open(2, 1);
        System.out.println(p.percolates());


    }
}