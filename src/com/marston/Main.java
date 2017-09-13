package com.marston;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        // 获取输入格子大小和实验次数
        System.out.println("Input n size and trail numbers: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), trails = sc.nextInt();

        // 实验和输出
        PercolationStats ps = new PercolationStats(n, trails);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo()
                + ", " + ps.confidenceHi() + "]");
    }
}
