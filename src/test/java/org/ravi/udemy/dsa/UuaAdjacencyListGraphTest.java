package org.ravi.udemy.dsa;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

// undirected, unweighted, acyclical adjacency graph
public class UuaAdjacencyListGraphTest {
    @Test
    public void emptiness() {
        TheGraph<Integer> graph = new MyUuaAdjacencyListGraph<>();

        assertThat(graph.getNumEdges()).isEqualTo(0);
        assertThat(graph.getNumVertices()).isEqualTo(0);
        assertThat(graph.hasVertex(44)).isFalse();
    }

    @Test
    public void videoUseCase() {
        TheGraph<Integer> graph = new MyUuaAdjacencyListGraph<>();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> graph.addEdge(4, 5))
                .withNoCause();
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);

        assertThat(graph.getNumVertices()).isEqualTo(7);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(4, 5);
        graph.addEdge(1, 2);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(6, 5);
        assertThat(graph.getNumEdges()).isEqualTo(8);

        // verify visually!
        graph.showConnections();
    }
}
