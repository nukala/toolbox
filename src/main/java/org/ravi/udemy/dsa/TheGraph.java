package org.ravi.udemy.dsa;

// www.udemy.com/master-the-coding-interview-data-structures-algorithms/learn/lecture/12371830
public interface TheGraph<T> {
    default void addVertex(T node) {
        throw new IllegalStateException(getClass().getSimpleName() + ": unimplemented");
    }

    default boolean hasVertex(T node) {
        return false;
    }

    default void addEdge(T node1, T node2) {
        throw new IllegalStateException(getClass().getSimpleName() + ": unimplemented");
    }

    default void showConnections() {
        throw new IllegalStateException(getClass().getSimpleName() + ": unimplemented");
    }

    default int getNumEdges() {
        throw new IllegalStateException(getClass().getSimpleName() + ": unimplemented");
    }

    default int getNumVertices() {
        throw new IllegalStateException(getClass().getSimpleName() + ": unimplemented");
    }

    /**
     * Displays a vertex of the graph.
     */
    // see: www.tutorialspoint.com/data_structures_algorithms/graph_data_structure.htm
    default void displayVertex() {
        throw new IllegalStateException(getClass().getSimpleName() + ": unimplemented");
    }
}
