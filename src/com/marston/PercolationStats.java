package com.marston;

import java.util.Random;

public class PercolationStats {

    // 平均值、标准差、95%置信区间
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int trails) {
        Random random = new Random();
        double count = 0.0; // 开口率总和
        double[] opensize = new double[trails]; // 记录每次实验的开口率
        for (int i = 0; i < trails; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolate()) {
                // 随机打开一个块，直至首尾导通
                p.open(random.nextInt(n) + 1, random.nextInt(n) + 1);
            }
            count += (double)p.numberOfOpenSites() / (n * n);
            opensize[i] = (double)p.numberOfOpenSites() / (n * n);
        }
        mean = count / trails;

        double ss = 0.0; // 计算方差
        for (int i = 0; i < trails; i++) {
            ss += Math.pow(opensize[i] - mean, 2)/ (trails - 1);
        }

        stddev = Math.sqrt(ss);
        confidenceHi = mean + 1.96 * stddev / Math.sqrt(trails);
        confidenceLo = mean - 1.96 * stddev / Math.sqrt(trails);
    }

    public double mean() { return mean; }

    public double stddev() { return stddev; }

    public double confidenceLo() {return confidenceLo; }

    public double confidenceHi() { return confidenceHi; }

    public static void main(String[] args) {
    }
}
