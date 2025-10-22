package com.example;

import java.util.*;

public class KruskalMST {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public long totalWeight = 0;
        public long comparisons = 0; // comparisons during sorting and checking
        public long unionOps = 0;
        public long findOps = 0;
        public long timeMs = 0;
    }

    public static Result run(Graph graph) {
        Result res = new Result();
        long start = System.nanoTime();

        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);

        // map vertex -> index
        Map<String, Integer> idx = new HashMap<>();
        int i = 0;
        for (String v : graph.vertices) idx.put(v, i++);

        UnionFind uf = new UnionFind(graph.vertexCount());

        for (Edge e : edges) {
            int u = idx.get(e.u);
            int v = idx.get(e.v);
            // Проверка соединения (find)
            int ru = uf.find(u);
            int rv = uf.find(v);
            res.findOps += uf.findOps; // аккумулируем
            uf.findOps = 0;
            res.comparisons++; // проверка ru == rv
            if (ru != rv) {
                boolean merged = uf.union(ru, rv);
                res.unionOps += uf.unionOps;
                uf.unionOps = 0;
                if (merged) {
                    res.mstEdges.add(e);
                    res.totalWeight += e.weight;
                    if (res.mstEdges.size() == graph.vertexCount() - 1) break;
                }
            }
        }

        res.timeMs = (System.nanoTime() - start) / 1_000_000;
        return res;
    }
}
