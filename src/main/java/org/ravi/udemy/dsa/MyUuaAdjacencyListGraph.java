package org.ravi.udemy.dsa;

import java.util.Formatter;
import java.util.HashMap;
import java.util.StringJoiner;

// almost javascript style but using Java

/**
 * Undirected, Unweighted, Acyclical graph  AdjacencyList implementation
 *
 * @param <T>
 * Video at: www.udemy.com/course/master-the-coding-interview-data-structures-algorithms/learn/lecture/12371828
 */
// changes with video: parameters called node,
public class MyUuaAdjacencyListGraph<T> implements TheGraph<T> {
    private int numEdges;
    private int numVertices;
    private HashMap<T, MyJavaScriptArray<T>> graph;

    public MyUuaAdjacencyListGraph() {
        this.graph = new HashMap<>();
    }

    @Override
    public void addVertex(T node) {
        if (graph.containsKey(node)) {
            throw new IllegalStateException(String.format("Vertex=[%s] already exists", node));
        }
        graph.put(node, new MyJavaScriptArray<>(2));
        numVertices++;
    }

    // TODO add duplicate-edge addition check (ex: A-to-B and B-to-A)
    @Override
    public void addEdge(T node1, T node2) {
        if (!graph.containsKey(node1)) {
            throw new IllegalStateException(String.format("Start of edge-[%s] does not exist", node1));
        }
        if (!graph.containsKey(node2)) {
            throw new IllegalStateException(String.format("End of edge-[%s] does not exist", node2));
        }

        graph.get(node1).push(node2);
        graph.get(node2).push(node1);
        numEdges++;
    }

    @Override
    public void showConnections() {
        System.out.printf("%s %n", toString());
    }

    @Override
    public int getNumEdges() {
        return numEdges;
    }

    @Override
    public int getNumVertices() {
        return numVertices;
    }

    @Override
    public boolean hasVertex(T node) {
        return graph.containsKey(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(numVertices * 16);
        Formatter formatter = new Formatter(sb);

        graph.keySet().stream()
                .forEach(k -> {
                    formatter.format("%s:", k);
                    MyJavaScriptArray<T> array = graph.get(k);
                    if (!array.isEmpty()) {
                        StringJoiner joiner = new StringJoiner(" ", "[", "]");
                        for (int i = 0; i < array.getCount(); i++) {
                            joiner.add(array.elementAt(i).toString());
                        }
                        formatter.format(" --> %s%n", joiner);
                    }
                });
        formatter.close();
        return sb.toString();
    }
}
