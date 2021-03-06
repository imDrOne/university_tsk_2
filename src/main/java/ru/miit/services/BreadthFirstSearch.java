package ru.miit.services;

import org.jetbrains.annotations.Nullable;
import ru.miit.interfaces.IGraphable;

import javax.enterprise.inject.Default;
import java.util.LinkedList;

@Default
public class BreadthFirstSearch implements IGraphable {
    private int V;   // No. of vertices
    private LinkedList[] adj; //Adjacency Lists

    // Constructor
    public BreadthFirstSearch() {
       super();
    }

    @Override
    public void init(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    // Function to add an edge into the graph
    @Override
    public void addEdge(int v, int w, @Nullable Integer cost) {
        adj[v].add(w);
    }

    // prints BFS traversal from a given source s
    @Override
    public void find(int s) {
        System.out.println("\nBreadth first search starts...");

        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            for (int n : (Iterable<Integer>) adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
