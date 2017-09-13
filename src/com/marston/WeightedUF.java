package com.marston;

import java.util.Scanner;

public class WeightedUF {

    private int componentNumber;
    private int n;
    private int[] id; // id[i]即为i的父节点
    private int[] sizeOfTree;  // 以i为根节点的数大小

    public WeightedUF(int n) {
        if (n < 0) throw new IllegalArgumentException("sites numbers < 0.");

        this.n = n;
        id = new int[n];
        sizeOfTree = new int[n];
        // 初始化id[i]=i
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sizeOfTree[i] = 1;
        }
        componentNumber = n;
    }

    public boolean connected(int p, int q) {
        if (p < 0 || p >= n || q < 0 || q >= n)
            throw new IllegalArgumentException("p or q out of range.");
        return (find(p) == find(q));
    }

    public int count() { return componentNumber; }

    public int find(int p) {
        if (p < 0 || p >= n) throw new IllegalArgumentException("p out of range.");

        // 查找根节点，每个节点的id即其父节点，查找过程中同时压缩路径
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }

        return p;
    }

    public void union(int p, int q) {
        if (p < 0 || p >= n || q < 0 || q >= n)
            throw new IllegalArgumentException("p or q out of range.");
        // 先查明是否为同一组成部分
        int rootp = find(p);
        int rootq = find(q);

        if (rootp == rootq) return;

        // 判断两树大小，将小树根节点连接到大树根节点上，即小树并入大树中，以减小树高
        if (sizeOfTree[rootp] < sizeOfTree[rootq]) { id[rootp] = rootq; sizeOfTree[rootq] += sizeOfTree[rootp]; }
        else { id[rootq] = rootp; sizeOfTree[rootp] += sizeOfTree[rootq]; }

        // 减少组成数目
        componentNumber--;
    }

    public static void main(String[] args) {
        // 获取输入，连接p、q节点
        Scanner sc = new Scanner(System.in);
        int p = sc.nextInt();
        int q = sc.nextInt();

        // 测试组件数目和首尾导通
        int N = 10;
        WeightedUF wuf = new WeightedUF(N);
        while(p != q) {
            wuf.union(p, q);
            System.out.println("Component number: " + wuf.count());
            System.out.println("Is 0 and N-1 connected: " + wuf.connected(0, N - 1));
            p = sc.nextInt();
            q = sc.nextInt();
        }
    }
}
