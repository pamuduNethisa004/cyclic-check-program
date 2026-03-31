/*
 * Name: Vikash Fernando
 * Student ID: 20232055
 *
 * Main.java
 * Entry point. Asks the user which file to check, then runs the acyclicity check.
 * Prints execution time in nanoseconds and milliseconds.
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Directed Graph Acyclicity Checker ===");
        System.out.print("Enter the input file name: ");
        String fileName = scanner.nextLine().trim();
        scanner.close();

        System.out.println();
        System.out.println("=== Reading graph from: " + fileName + " ===");
        DirectedGraph graph = GraphFileReader.readGraph(fileName);

        System.out.println();
        System.out.println("=== Running Sink Elimination Algorithm ===");

        // Start timing
        long startTime = System.nanoTime();

        boolean isAcyclic = AcyclicChecker.checkAcyclic(graph);

        // Stop timing
        long endTime = System.nanoTime();
        long elapsedNs = endTime - startTime;
        double elapsedMs = elapsedNs / 1_000_000.0;

        System.out.println("--------------------------------------------------");
        System.out.println();

        if (isAcyclic) {
            System.out.println("ANSWER: YES - this graph is acyclic");
        } else {
            System.out.println("ANSWER: NO - this graph has a cycle");
            AcyclicChecker.findAndPrintCycle(graph);
        }

        System.out.printf("Execution time (ns): %d%n", elapsedNs);
        System.out.printf("Execution time (ms): %.4f%n", elapsedMs);
        System.out.println("--------------------------------------------------");
    }
}