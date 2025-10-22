package com.example;

import java.util.*;

public class Graph {
    public final List<String> vertices;
    public final List<Edge> edges;
    public final Map<String, List<Edge>> adj;

    public Graph(List<String> vertices, List<Edge> edges) {
        this.vertices = new ArrayList<>(vertices);
        this.edges = new ArrayList<>(edges);
        this.adj = new HashMap<>();
        for (String v : vertices) adj.put(v, new ArrayList<>());
        for (Edge e : edges) {
            // так как неориентированный
            adj.get(e.u).add(e);
            // добавляем зеркальное ребро для удобства обхода
            adj.get(e.v).add(new Edge(e.v, e.u, e.weight));
        }
    }

    public int vertexCount() { return vertices.size(); }
    public int edgeCount() { return edges.size(); } // исходные (каждое ребро один раз)
}
