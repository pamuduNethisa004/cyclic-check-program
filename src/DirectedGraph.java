/*
 * Name: Vikash Fernando
 * Student ID: 20232055
 *
 * DirectedGraph.java
 * Represents a directed graph using an adjacency list.
 * Built on top of HashMap and ArrayList (standard Java data structures).
 */

import java.util.*;

public class DirectedGraph {

    // Each node maps to a list of nodes it points TO
    private Map<Integer, List<Integer>> adjList;

    public DirectedGraph() {
        adjList = new HashMap<>();
    }

    // Add a directed edge from -> to
    public void addEdge(int from, int to) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());
        adjList.get(from).add(to);
    }

    // Return all nodes in the graph
    public Set<Integer> getNodes() {
        return adjList.keySet();
    }

    // Return the neighbours (outgoing edges) of a node
    public List<Integer> getNeighbours(int node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    // A sink is a node with NO outgoing edges
    public Integer findSink() {
        for (Integer node : adjList.keySet()) {
            if (adjList.get(node).isEmpty()) {
                return node;
            }
        }
        return null; // no sink found
    }

    // Remove a node and all edges pointing to it
    public void removeNode(int node) {
        adjList.remove(node);
        for (List<Integer> neighbours : adjList.values()) {
            neighbours.remove(Integer.valueOf(node));
        }
    }

    public boolean isEmpty() {
        return adjList.isEmpty();
    }
}