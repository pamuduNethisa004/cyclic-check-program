/*
 * Name: Vikash Fernando
 * Student ID: 20232055
 *
 * AcyclicChecker.java
 * Implements the sink elimination algorithm to check if a directed graph is acyclic.
 * Also finds and prints an actual cycle if one exists (Task 5).
 */

import java.util.*;

public class AcyclicChecker {

    /*
     * SINK ELIMINATION ALGORITHM (Task 4)
     *
     * Logic:
     *   1. If the graph is empty -> return true (acyclic).
     *   2. If there is no sink   -> return false (cycle exists).
     *   3. Otherwise, remove the sink and repeat.
     *
     * NOTE: We work on a COPY of the graph so the original is preserved
     *       for cycle printing later.
     */
    public static boolean checkAcyclic(DirectedGraph original) {
        DirectedGraph copy = makeCopy(original);

        while (!copy.isEmpty()) {
            Integer sink = copy.findSink();

            if (sink == null) {
                System.out.println("No sink found - graph has a cycle");
                return false;
            }

            System.out.println("Found sink: " + sink + " - removing it");
            copy.removeNode(sink);
        }

        return true;
    }

    /*
     * FIND AND PRINT A CYCLE (Task 5)
     *
     * Uses DFS with three colour states:
     *   WHITE (0) = not visited yet
     *   GRAY  (1) = currently being explored (in the DFS stack)
     *   BLACK (2) = fully explored, no cycle through here
     *
     * If we reach a GRAY node during DFS, we found a back-edge = cycle.
     */
    public static void findAndPrintCycle(DirectedGraph graph) {
        Map<Integer, Integer> color  = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();

        final int WHITE = 0, GRAY = 1, BLACK = 2;

        for (Integer node : graph.getNodes()) {
            color.put(node, WHITE);
            parent.put(node, null);
        }

        for (Integer node : graph.getNodes()) {
            if (color.get(node) == WHITE) {
                Integer cycleNode = dfs(graph, node, color, parent, WHITE, GRAY, BLACK);
                if (cycleNode != null) {
                    printCycle(cycleNode, parent);
                    return;
                }
            }
        }
    }

    private static Integer dfs(DirectedGraph graph, int start,
                               Map<Integer, Integer> color,
                               Map<Integer, Integer> parent,
                               int WHITE, int GRAY, int BLACK) {
        color.put(start, GRAY);

        for (int neighbour : graph.getNeighbours(start)) {
            if (color.get(neighbour) == GRAY) {
                parent.put(neighbour, start);
                return neighbour;
            }
            if (color.get(neighbour) == WHITE) {
                parent.put(neighbour, start);
                Integer result = dfs(graph, neighbour, color, parent, WHITE, GRAY, BLACK);
                if (result != null) return result;
            }
        }

        color.put(start, BLACK);
        return null;
    }

    private static void printCycle(int cycleNode, Map<Integer, Integer> parent) {
        List<Integer> cycle = new ArrayList<>();
        int current = cycleNode;

        while (true) {
            cycle.add(current);
            current = parent.get(current);
            if (current == cycleNode) break;
        }
        cycle.add(cycleNode);

        Collections.reverse(cycle);

        System.out.print("Cycle found: ");
        for (int i = 0; i < cycle.size(); i++) {
            System.out.print(cycle.get(i));
            if (i < cycle.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }

    private static DirectedGraph makeCopy(DirectedGraph original) {
        DirectedGraph copy = new DirectedGraph();
        for (Integer node : original.getNodes()) {
            for (Integer neighbour : original.getNeighbours(node)) {
                copy.addEdge(node, neighbour);
            }
        }
        return copy;
    }
}