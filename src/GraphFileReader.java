/*
 * Name: Vikash Fernando
 * Student ID: 20232055
 *
 * GraphFileReader.java
 * Reads a graph from a text file.
 * The first line may contain a single number (node count) - this is skipped.
 * Each remaining line contains two numbers: "from to" representing a directed edge.
 */

import java.io.*;
import java.util.*;

public class GraphFileReader {

    public static DirectedGraph readGraph(String fileName) {
        DirectedGraph graph = new DirectedGraph();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");

                // If a line has only ONE number, it is the node count -> skip it
                if (parts.length == 1) {
                    System.out.println("  (Skipping node count line: " + line + ")");
                    continue;
                }

                // Otherwise it must be a pair: from to
                int from = Integer.parseInt(parts[0]);
                int to   = Integer.parseInt(parts[1]);
                graph.addEdge(from, to);
                System.out.println("  Read edge: " + from + " -> " + to);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return graph;
    }
}