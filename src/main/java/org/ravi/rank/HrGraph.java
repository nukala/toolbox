package org.ravi.rank;

import lombok.Getter;
import org.ravi.udemy.dsa.WorthLooking;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// hacker-rank graph
@SuppressWarnings({"unused"})
public class HrGraph<T> {
    /**
     * To help generate ids for faster lookup of nodes. This is not necessary
     */
    private static AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, HrNode<T>> nodeLookup = new HashMap<>();

    private HrNode<T> getNode(int id) {
        return nodeLookup.get(id);
    }

    public void addEdge(HrNode<T> source, HrNode<T> destination) {
        source.adjacent.add(destination);
    }

    public void addEdge(int sourceId, int destinationId) {
        addEdge(getNode(sourceId), getNode(destinationId));
    }

    public HrNode<T> addNode(T payload) {
        HrNode<T> node = new HrNode<>(payload);

        nodeLookup.put(node.getId(), node);
        return node;
    }

    public boolean hasPathDFS(HrNode<T> src, HrNode<T> dest) {
        Objects.requireNonNull(src, "null source-vertex not allowed");
        Objects.requireNonNull(dest, "null destination-vertex not allowed");

        // using a visited flag would require us to be able to clear all the visited flags when needed.
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDFS(src, dest, visited);
    }

    public boolean hasPathDFS(int sourceId, int destinationId) {
        return hasPathDFS(getNode(sourceId), getNode(destinationId));
    }

    private boolean hasPathDFS(HrNode<T> src, HrNode<T> dest, HashSet<Integer> visited) {
        if (visited.contains(src.id)) {
            return false;
        }
        if (src.equals(dest)) {
            return true;
        }
        visited.add(src.getId());
        for (HrNode<T> child : src.adjacent) {
            if (hasPathDFS(child, dest, visited)) {
                return true;
            }
        }

        return false;
    }

    // www.techiedelight.com/depth-first-search/
    @WorthLooking("hasPathIterativeDFS: use a stack and push with decrementing-loop-of-edges")
    private boolean hashPathIterativeDFS(HrNode<T> src, HrNode<T> dest) {
        Objects.requireNonNull(src, "null source-vertex not allowed");
        Objects.requireNonNull(dest, "null destination-vertex not allowed");
        Stack<HrNode<T>> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        stack.push(src);
        while (!stack.isEmpty()) {
            HrNode<T> node = stack.pop();

            if (visited.contains(node.getId())) {
                continue;
            }

            visited.add(node.getId());
            if (dest.equals(node)) {
                return true;
            }

            for (int i = node.adjacent.size() - 1; i >= 0; i--) {
                HrNode<T> adj = node.adjacent.get(i);

                if (!visited.contains(adj.getId())) {
                    stack.push(adj);
                }
            }
        }
        return false;
    }

    public boolean hasPathBFS(int sourceId, int destinationId) {
        return hasPathBFS(getNode(sourceId), getNode(destinationId));
    }

    public boolean hasPathBFS(HrNode<T> src, HrNode<T> dest) {
        Objects.requireNonNull(src, "null source-vertex not allowed");
        Objects.requireNonNull(dest, "null destination-vertex not allowed");
        // to track what to visit next
        Queue<HrNode<T>> queue = new LinkedList<>();

        // using a visited flag requires us to be able to clear all the visited flags when needed.
        HashSet<Integer> visited = new HashSet<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            HrNode<T> node = queue.remove();

            if (node.equals(dest)) { // Found a match!
                return true;
            }

            if (visited.contains(node.id)) { // already evaluated!
                continue;
            }
            visited.add(node.id);

            queue.addAll(node.adjacent);
        }

        return false;
    }

    public static class HrNode<T> {
        private LinkedList<HrNode<T>> adjacent = new LinkedList<>();
        @Getter
        private T payload;
        @Getter
        private int id;

        private HrNode(T payload) {
            this.payload = payload;
            id = counter.incrementAndGet();
        }
    }
}
