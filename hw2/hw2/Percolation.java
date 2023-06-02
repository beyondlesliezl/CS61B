package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int numOpenSite;
    private int width;
    private int allNumber;
    private static class Node {
        private final int id;
        private boolean isOpen; // True -> isOpen; False -> notOpen
        private boolean isfull; // True -> is full; False -> not full
        private Node(int num, boolean openOrNot, boolean fullOrNot) {
            id = num;
            isOpen = openOrNot;
            isfull = fullOrNot;
        }
    }
    private final Node[][] grid;
    private WeightedQuickUnionUF helpFull;

    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException("N must greater than 0");
        }

        grid = new Node[N][N];
        helpFull = new WeightedQuickUnionUF(N * N + 2);
        // use to check is or not union, N * N 是最上面的节点，N * N + 1下面节点
        int sum = 0;
        width = N;
        allNumber = N * N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = new Node(sum, false, false);
                sum++;
            }
        }

        for (int i = 0; i < N; i++) {
            helpFull.union(N * N, i);
            helpFull.union(N * N + 1, N * (N - 1) + i);
        }
    }

    private Node transform(int n) {
        int col = n % width;
        int row = n / width;
        return grid[row][col];
    }
    private void unionNew(int id) { // 附近的Node是否open, 如果有，那就联系起来
        if (id - width >= 0 && transform(id - width).isOpen) { // node 上方
            helpFull.union(id, id - width);
        }
        if (id + width <= (allNumber - 1) && transform(id + width).isOpen) { // node 下方
            helpFull.union(id, id + width);
        }
        if (id - 1 >= 0 && (id % width - (id - 1) % width) == 1 && transform(id - 1).isOpen) { // node 左侧
            helpFull.union(id, id - 1);
        }
        if (id + 1 <= (allNumber - 1) && ((id + 1) % width - id % width) == 1
                && transform(id + 1).isOpen) {
            helpFull.union(id, id + 1);
        }
        // is or not full

        if (helpFull.connected(grid.length * grid.length, id)) {
            transform(id).isfull = true;
        }
    }
    public void open(int row, int col) {       // open the site (row, col) if it is not open already
        if (!isOpen(row, col)) {
            grid[row][col].isOpen = true;
            if (row == 0) {
                grid[row][col].isfull = true;
            }
            unionNew(grid[row][col].id);
            numOpenSite++;
        }
    }

    public boolean isOpen(int row, int col) {  // is the site (row, col) open?
        return grid[row][col].isOpen;
    }
    public boolean isFull(int row, int col) {  // is the site (row, col) full?
        return (helpFull.connected(grid.length * grid.length, grid[row][col].id) && isOpen(row, col));
    }
    public int numberOfOpenSites() {           // number of open sites
        return numOpenSite;
    }
    public boolean percolates() {              // does the system percolate?
        return helpFull.connected(grid.length * grid.length,grid.length * grid.length + 1);
    }
    public static void main(String[] args) {   // use for unit testing (not required)

    }

}
