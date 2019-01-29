package com.interview.graphs;

public class Edge implements Comparable<Edge> {

    private final int u;
    private final int v;
    private final double weight;

    public Edge(int u, int v, double weight) {
        if (u < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return u;
    }

    public int other(int vertex) {
        if(vertex == u) return v;
        else if(vertex == v) return u;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    public String toString() {
        return String.format("%d-%d %.5f", u, v, weight);
    }

    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        System.out.println(e);
    }
}
