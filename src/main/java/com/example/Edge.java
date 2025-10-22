package com.example;

public class Edge implements Comparable<Edge> {
    public final String u;
    public final String v;
    public final int weight;

    public Edge() { this.u = ""; this.v = ""; this.weight = 0; } // для Jackson
    public Edge(String u, String v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("(%s - %s : %d)", u, v, weight);
    }
}
