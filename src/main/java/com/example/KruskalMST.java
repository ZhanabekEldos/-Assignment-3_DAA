package com.example;

import java.util.*;

public class KruskalMST {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public long totalWeight = 0;
        public long comparisons = 0; // приближённый счётчик сравнений при сортировке
        public long unionOps = 0;
        public long findOps = 0;
        public long timeMs = 0;
    }

    public static Result run(Graph graph) {
        Result res = new Result();
        long start = System.nanoTime();

        // Берём рёбра исходного графа (каждое ребро в graph.edges уже один раз)
        List<Edge> edges = new ArrayList<>(graph.edges);

        // Сортируем ребра по весу, инкрементируя счётчик сравнений
        edges.sort((a, b) -> {
            res.comparisons++;
            return Integer.compare(a.weight, b.weight);
        });

        // Сопоставление вершины -> индекс [0..n-1] для UnionFind
        Map<String, Integer> idx = new HashMap<>();
        int i = 0;
        for (String v : graph.vertices) {
            idx.put(v, i++);
        }

        UnionFind uf = new UnionFind(graph.vertexCount());

        for (Edge e : edges) {
            int u = idx.get(e.u);
            int v = idx.get(e.v);

            int ru = uf.find(u);
            int rv = uf.find(v);
            // обновляем счётчик findOps (UnionFind ведёт свою статистику в полях)
            res.findOps = uf.findOps;

            if (ru != rv) {
                boolean merged = uf.union(ru, rv);
                res.unionOps = uf.unionOps;

                if (merged) {
                    res.mstEdges.add(e);
                    res.totalWeight += e.weight;
                    // остановимся, если уже V-1 рёбер
                    if (res.mstEdges.size() == graph.vertexCount() - 1) break;
                }
            }
        }

        // Финальная синхронизация счётчиков (на случай последних операций)
        res.findOps = uf.findOps;
        res.unionOps = uf.unionOps;

        res.timeMs = (System.nanoTime() - start) / 1_000_000;
        return res;
    }
}
