package ru.miit.services;

import org.jetbrains.annotations.Nullable;
import ru.miit.interfaces.IGraphable;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.Iterator;
import java.util.LinkedList;

@Decorator
public class DepthFirstSearch implements IGraphable {

    @Inject
    @Delegate
    IGraphable ref;

    // Array  of lists for
    // Adjacency List Representation
    private LinkedList<Integer> adj[];
    private int V; // No. of vertices

    // Constructor
    public DepthFirstSearch() {
        super();
    }

    @Override
    public void init(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }

        ref.init(v);
    }


    // Function to add an edge into the graph
    @Override
    public void addEdge(int v, int w, @Nullable Integer cost) {
        adj[v].add(w); // Add w to v's list.
        ref.addEdge(v, w, null);
    }

    // A function used by DFS
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // The function to do DFS traversal.
    // It uses recursive
    // DFSUtil()
    @Override
    public void find(int v) {
        System.out.println("[DECORATOR] Depth first search starts...");
        // Mark all the vertices as
        // not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];

        // Call the recursive helper
        // function to print DFS
        // traversal
        DFSUtil(v, visited);

        ref.find(v);
    }
}
