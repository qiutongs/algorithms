package com.qiutongs.datastructure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<V> {

    static class Edge<V> {
        V from;
        V to;
        int weight;

        Edge(V from, V to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private Map<V, LinkedList<Edge<V>>> vertices;

    public Graph() {
        vertices = new HashMap<>();
    }

    public List<V> vertices() {
        return new ArrayList<>(vertices.keySet());
    }

    public List<Edge<V>> outgoing(V v) {
        return vertices.get(v);
    }

    public void addEdge(V fromV, V toV, int weight) {
        LinkedList<Edge<V>> neighbours = vertices.get(fromV);

        if (neighbours == null) {
            neighbours = new LinkedList<>();
            neighbours.add(new Edge<>(fromV, toV, weight));

            vertices.put(fromV, neighbours);
        } else {
            neighbours.add(new Edge<>(fromV, toV, weight));
        }

        if (!vertices.containsKey(toV)) {
            vertices.put(toV, new LinkedList<>());
        }
    }

    public void visualize() {
        vertices.forEach((v, list) -> {
            System.out.print(v + ": ");
            list.forEach(edge -> System.out.print(edge.to + " " + edge.weight + " -> "));
            System.out.println();
        });
    }

    public static void main(String[] args) {
        Graph<String> graph = new Graph<>();
        graph.addEdge("a", "b", 10);
        graph.addEdge("b", "c", 20);
        graph.addEdge("a", "c", 30);
        graph.visualize();
    }
}
