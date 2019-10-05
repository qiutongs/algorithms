package com.qiutongs.datastructure.graph;

import java.util.List;

public interface ShortestPath<V extends Comparable<V>> {
    public List<Graph.Edge<V>> find(Graph<V> graph, V fromV, V toV);
}
