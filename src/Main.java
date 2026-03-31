/*
 * Name: Vikash Fernando
 * Student ID: 20232055
 *
 * Main.java
 * Entry point. Asks the user which file to check, then runs the acyclicity check.
 * If a cycle exists, it is found and printed.
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