package com.marston;

public class Percolation {

    WeightedUF wuf;
    boolean[] open;
    boolean[] full;
    private int N;
    private int opensize;
    public Percolation(int N) {
        this.N = N;
        wuf = new WeightedUF(N * N + 2);
        open = new boolean[N * N];
        full = new boolean[N * N];
        opensize = 0;
    }

    public void open(int i, int j) {
        if (!isOpen(i, j))
            open[(i - 1) * N + j] = true;
        if (i == 0) wuf.union(1, (i - 1) * N + j + 1);
    }

    public boolean isOpen(int i, int j) {
        return open[(i - 1) * N + j];
    }

    public boolean isFull(int i, int j) {
        return full[(i - 1) * N + j];
    }

    public int numberOfOpenSites() {
        return opensize;
    }

    public boolean percolate() {
        return wuf.connected(1, N * N + 2);
    }

}
