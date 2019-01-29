package com.interview.graphs;

public class DirectedEdge {

    private final int u;
    private final int v;
    private final double weight;

    public DirectedEdge(int u, int v, double weight) {
        if (u < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int from() {
        return u;
    }

    public int to() {
        return v;
    }

    public double weight() {
        return weight;
    }

    public String toString() {
        return u + "->" + v + " " + String.format("%5.2f", weight);
    }

    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        System.out.println(e);
    }
}
