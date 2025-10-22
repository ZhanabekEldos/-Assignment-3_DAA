package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        // Читаем input.json из resources (если хочешь, передавай путь через args)
        String resourcePath = "/input.json";
        if (args.length > 0) resourcePath = args[0];

        ObjectMapper mapper = new ObjectMapper();
        InputStream is = Main.class.getResourceAsStream(resourcePath);
        if (is == null) {
            System.err.println("Не найден " + resourcePath + " в resources. Убедись, что файл лежит в src/main/resources");
            return;
        }
        InputModel model = mapper.readValue(is, InputModel.class);

        Graph graph = new Graph(model.vertices, model.edges);

        System.out.println("Исходный граф: |V| = " + graph.vertexCount() + ", |E| = " + graph.edgeCount());
        System.out.println("Edges: " + graph.edges);

        System.out.println("\n--- Запуск Prim ---");
        PrimMST.Result primRes = PrimMST.run(graph);
        System.out.println("MST edges (Prim): " + primRes.mstEdges);
        System.out.println("Total weight (Prim): " + primRes.totalWeight);
        System.out.println("Comparisons/relax ops (Prim): " + primRes.comparisons);
        System.out.println("PQ ops (inserts/polls) (Prim): " + primRes.pqOps);
        System.out.println("Time (ms) (Prim): " + primRes.timeMs);

        System.out.println("\n--- Запуск Kruskal ---");
        KruskalMST.Result krusRes = KruskalMST.run(graph);
        System.out.println("MST edges (Kruskal): " + krusRes.mstEdges);
        System.out.println("Total weight (Kruskal): " + krusRes.totalWeight);
        System.out.println("Comparisons (Kruskal): " + krusRes.comparisons);
        System.out.println("Find ops (Kruskal): " + krusRes.findOps);
        System.out.println("Union ops (Kruskal): " + krusRes.unionOps);
        System.out.println("Time (ms) (Kruskal): " + krusRes.timeMs);

        // Проверка равенства стоимости
        if (primRes.totalWeight == krusRes.totalWeight) {
            System.out.println("\n✔ Стоимости MST совпадают: " + primRes.totalWeight);
        } else {
            System.out.println("\n⚠ Стоимости MST различаются! Prim=" + primRes.totalWeight + ", Kruskal=" + krusRes.totalWeight);
        }
    }
}
