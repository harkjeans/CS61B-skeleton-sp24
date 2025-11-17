import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int n;
    private boolean[][] grid;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF ufBackwash;
    private final int virtualTop;
    private final int virtualBottom;
    private int openSitesCount;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive");
        }

        this.n = N;
        this.grid = new boolean[N][N];
        this.openSitesCount = 0;

        this.uf = new WeightedQuickUnionUF(N * N + 2);
        this.ufBackwash = new WeightedQuickUnionUF(N * N + 1);

        this.virtualTop = N * N;
        this.virtualBottom = N * N + 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);

        if (isOpen(row, col)) {
            return;
        }

        grid[row][col] = true;
        openSitesCount++;

        int index = getIndex(row, col);

        if (row == 0) {
            uf.union(index, virtualTop);
            ufBackwash.union(index, virtualTop);
        }

        if (row == n - 1) {
            uf.union(index, virtualBottom);
        }

        connectIfOpen(row, col, row - 1, col);
        connectIfOpen(row, col, row + 1, col);
        connectIfOpen(row, col, row, col - 1);
        connectIfOpen(row, col, row, col + 1);
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            return false;
        }

        int index = getIndex(row, col);
        return ufBackwash.connected(index, virtualTop);
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    public boolean percolates() {
        return uf.connected(virtualTop, virtualBottom);
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException(
                    "Index (" + row + ", " + col + ") is not between 0 and " + (n - 1)
            );
        }
    }

    private int getIndex(int row, int col) {
        return row * n + col;
    }

    private void connectIfOpen(int row1, int col1, int row2, int col2) {
        if (row2 >= 0 && row2 < n && col2 >= 0 && col2 < n && isOpen(row2, col2)) {
            int index1 = getIndex(row1, col1);
            int index2 = getIndex(row2, col2);
            uf.union(index1, index2);
            ufBackwash.union(index1, index2);
        }
    }
}
