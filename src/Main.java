/*
 * Name: Vikash Fernando
 * Student ID: 20232055
 *
 * Main.java
 * Entry point. Reads a graph from file, runs the acyclicity check,
 * and prints the result. If a cycle exists, it is found and printed.
 */

public class Main {

    public static void main(String[] args) {

        // -------------------------------------------------------
        // Change the filename here to test different inputs
        String fileName = "a_320_0.txt";
        // -------------------------------------------------------

        System.out.println("=== Reading graph from: " + fileName + " ===");
        DirectedGraph graph = GraphFileReader.readGraph(fileName);
        System.out.println();

        System.out.println("=== Running Sink Elimination Algorithm ===");
        boolean isAcyclic = AcyclicChecker.checkAcyclic(graph);
        System.out.println();

        if (isAcyclic) {
            System.out.println("Result: Graph is ACYCLIC (YES)");
        } else {
            System.out.println("Result: Graph has a CYCLE (NO)");
            System.out.println();
            System.out.println("=== Finding the cycle ===");
            AcyclicChecker.findAndPrintCycle(graph);
        }
    }
}