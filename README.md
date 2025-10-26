Assignment 3 — Optimization of a City Transportation Network (Minimum Spanning Tree)

Student: Елдос Жанабек
Group: SE-2401
Course: Design and Analysis of Algorithms
Year: 2025

Objective

The goal of this assignment is to apply Prim’s and Kruskal’s algorithms to optimize a city transportation network.
The task is to find a Minimum Spanning Tree (MST) — the minimal set of roads connecting all districts at the lowest total construction cost.

Project Description

The transportation network is represented as a weighted undirected graph:

Vertices represent city districts

Edges represent possible roads between districts

Edge weights represent construction costs

The program:

Reads graph data from a JSON file (input.json)

Runs both Prim’s and Kruskal’s algorithms

Outputs:

List of edges forming the MST

Total cost of the MST

Number of vertices and edges

Operation counts (comparisons, heap or union-find operations)

Execution time in milliseconds

Compares both algorithms in terms of efficiency and performance.

Algorithms Overview
Prim’s Algorithm

Builds the MST incrementally by always selecting the smallest edge that connects the current tree with a new vertex.

Implemented with a Priority Queue (Min-Heap).

Time Complexity: O(E log V)

Best for dense graphs.

Kruskal’s Algorithm

Sorts all edges by weight and adds them one by one, ensuring no cycles are formed using Union-Find.

Time Complexity: O(E log E)

Best for sparse graphs.Project Structure
Assignment-3/
│
├── src/
│   └── main/
│       └── java/com/example/
│           ├── Edge.java
│           ├── Graph.java
│           ├── InputModel.java
│           ├── UnionFind.java
│           ├── PrimMST.java
│           ├── KruskalMST.java
│           └── Main.java
│
├── src/main/resources/
│   └── input.json
│
└── README.md

How to Run
Using IntelliJ IDEA or any IDE

Clone or open the project.

Place the file input.json in src/main/resources.

Run the Main class.

Using Command Line
# Compile
javac -d out src/main/java/com/example/*.java

# Run
java -cp out com.example.Main


To use a custom input file:

java -cp out com.example.Main path/to/your/input.json

Example Output
Исходный граф: |V| = 5, |E| = 7
Edges: [(A - B : 4), (A - C : 2), (B - C : 5), (B - D : 10), (C - D : 3), (C - E : 8), (D - E : 7)]

--- Запуск Prim ---
MST edges (Prim): [(A - C : 2), (C - D : 3), (A - B : 4), (D - E : 7)]
Total weight (Prim): 16
Time (ms) (Prim): 0.02

--- Запуск Kruskal ---
MST edges (Kruskal): [(A - C : 2), (C - D : 3), (A - B : 4), (D - E : 7)]
Total weight (Kruskal): 16
Time (ms) (Kruskal): 0.01

✔ MST total costs are identical: 16

Input Format (input.json)
{
"vertices": ["A", "B", "C", "D", "E"],
"edges": [
{"u": "A", "v": "B", "weight": 4},
{"u": "A", "v": "C", "weight": 2},
{"u": "B", "v": "C", "weight": 5},
{"u": "B", "v": "D", "weight": 10},
{"u": "C", "v": "D", "weight": 3},
{"u": "C", "v": "E", "weight": 8},
{"u": "D", "v": "E", "weight": 7}
]
}

Performance Summary
Algorithm	Vertices	Edges	Total Cost	Operations	Time (ms)
Prim	5	7	16	15	0.0229
Kruskal	5	7	16	32	0.0174

MST total cost is identical for both algorithms.
Kruskal was slightly faster, but Prim scales better for dense networks.


Summary

Both algorithms produce identical MST results.

Kruskal is faster on sparse graphs.

Prim is better for dense graphs.

The implementation satisfies all
