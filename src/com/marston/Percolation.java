package com.marston;

import java.util.Scanner;

public class Percolation {

    private WeightedUF wuf;
    private boolean[] open; // 开口标志
    private int N;
    private int opensize; // 记录开口数目

    public Percolation(int N) {
        this.N = N;
        // 0代表第一个虚拟节点，N*N+1代表最后一个虚拟节点，1～N*N代表格子
        wuf = new WeightedUF(N * N + 2);
        open = new boolean[N * N];
        opensize = 0;
    }

    public void open(int i, int j) {
        if (isOpen(i, j)) return;

        open[(i - 1) * N + j - 1] = true;
        opensize++;
        // 将第一行开口与第一个虚拟节点连接，最后一行与最后一个虚拟节点连接
        if (i == 1) wuf.union(0, j);
        else if (i == N) wuf.union(N * N + 1, (i - 1) * N + j);
        searchAround(i, j);
    }

    // 连接第i行第j列和第p行第q列元素
    private void connect(int i, int j, int p, int q) {
        // 校验是否为相邻格子
        if (Math.abs(i - p) + Math.abs(j - q) != 1)
            throw new IllegalArgumentException("illegal connect");
        wuf.union((i - 1) * N + j, (p - 1) * N + q);
    }

    // 搜索(i, j)上下左右4个块，如果相邻块已打开，则(i, j)与之相连成一个组成
    private void searchAround(int i, int j) {
        if (i > 1 && isOpen(i - 1, j)) connect(i, j, i - 1, j);
        if (i < N && isOpen(i + 1, j)) connect(i, j, i + 1, j);
        if (j > 1 && isOpen(i, j - 1)) connect(i, j, i, j - 1);
        if (j < N && isOpen(i, j + 1)) connect(i, j, i, j + 1);

    }

    public boolean isOpen(int i, int j) {
        return open[(i - 1) * N + j - 1];
    }

    public boolean isFull(int i, int j) {
        return wuf.connected(0, (i - 1) * N + j);
    }

    public int numberOfOpenSites() {
        return opensize;
    }

    public boolean percolate() {
        return isFull(N, N + 1);
    }

    public static void main(String[] args) {
        // 获取输入，为（i，j）格子开口
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int j = sc.nextInt();

        // 测试导通和开口数
        Percolation p = new Percolation(5);
        while(i != 0 && j != 0) {
            p.open(i, j);
            System.out.println("Percolation status:" + p.percolate());
            System.out.println("Open number: " + p.numberOfOpenSites());
            i = sc.nextInt();
            j = sc.nextInt();
        }
    }

}
