package org.ravi.rank;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HrGraphTest {
    @Test
    public void hasDfs() {
        HrGraph<String> graph = new HrGraph<>();

        HrGraph.HrNode<String> aNode = graph.addNode("a");
        HrGraph.HrNode<String> bNode = graph.addNode("b");
        HrGraph.HrNode<String> cNode = graph.addNode("c");
        HrGraph.HrNode<String> dNode = graph.addNode("d");

        graph.addEdge(aNode, bNode);
        graph.addEdge(aNode, cNode);
        graph.addEdge(bNode, cNode);
        graph.addEdge(aNode, dNode);

        assertThat(graph.hasPathDFS(aNode, bNode)).isTrue();
        assertThat(graph.hasPathDFS(bNode, dNode)).isFalse();
    }
}
