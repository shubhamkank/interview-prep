package com.interview.graphs;

/* https://en.wikipedia.org/wiki/Disjoint-set_data_structure
   alpha (n) < 5 for any value of n that can be written in this physical universe,
   so the disjoint-set operations take place in essentially constant time */
public class UnionFind {

    private int[] parent;
    private int[] rank;
    private int count;

    public UnionFind(int n) {
        if(n < 0) {
            throw new IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    //Using path compression
    //Time complexity: O(alpha(n)) - amortized
    public int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    //Time complexity: O(alpha(n)) - amortized
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot == yRoot) {
            return;
        }

        if(rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if(rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
        count--;
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(10);
        unionFind.union(4, 3);
        unionFind.union(3, 8);
        unionFind.union(6, 5);
        unionFind.union(9, 4);
        unionFind.union(2, 1);
        unionFind.union(8, 9);
        unionFind.union(5, 0);
        unionFind.union(7, 2);
        unionFind.union(6, 1);
        unionFind.union(1, 0);
        unionFind.union(6, 7);
        System.out.println(unionFind.count());
    }
}
