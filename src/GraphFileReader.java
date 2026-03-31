/*
 * Name: Vikash Fernando
 * Student ID: 20232055
 *
 * GraphFileReader.java
 * Reads a graph from a text file.
 * Each line in the file contains two numbers: "from to" representing a directed edge.
 */

import java.io.*;
import java.util.*;

public class GraphFileReader {

    public static DirectedGraph readGraph(String fileName) {
        DirectedGraph graph = new DirectedGraph();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextInt()) {
                int from = scanner.nextInt();
                int to   = scanner.nextInt();
                graph.addEdge(from, to);
                System.out.println("  Read edge: " + from + " -> " + to);
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return graph;
    }
}