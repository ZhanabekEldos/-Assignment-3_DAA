package com.example;

import java.util.*;

public class PrimMST {
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public long totalWeight = 0;
        public long comparisons = 0; // ключевые сравнения / relax operations
        public long pqOps = 0; // вставки/удаления из очереди
        public long timeMs = 0;
    }

    public static Result run(Graph graph) {
        Result res = new Result();
        long start = System.nanoTime();

        if (graph.vertices.isEmpty()) {
            res.timeMs = (System.nanoTime() - start) / 1_000_000;
            return res;
        }

        String startV = graph.vertices.get(0);
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(startV, startV, 0));
        res.pqOps++;

        while (!pq.isEmpty() && visited.size() < graph.vertexCount()) {
            Edge e = pq.poll();
            res.pqOps++;
            String v = e.v;
            if (visited.contains(v)) {
                res.comparisons++;
                continue;
            }
            visited.add(v);
            if (!(e.u.equals(e.v) && e.weight == 0)) {
                res.mstEdges.add(e);
                res.totalWeight += e.weight;
            }

            for (Edge adjEdge : graph.adj.getOrDefault(v, Collections.emptyList())) {
                // если сосед ещё не в MST — предлагать ребро
                res.comparisons++;
                if (!visited.contains(adjEdge.v)) {
                    pq.add(adjEdge);
                    res.pqOps++;
                }
            }
        }

        res.timeMs = (System.nanoTime() - start) / 1_000_000;
        return res;
    }
}
