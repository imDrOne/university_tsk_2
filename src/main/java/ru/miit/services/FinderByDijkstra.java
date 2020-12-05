package ru.miit.services;

import ru.miit.interfaces.IGraphable;
import ru.miit.utils.Node;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.*;

@Decorator
public class FinderByDijkstra implements IGraphable {

    @Inject
    @Delegate
    IGraphable ref;

    public int[] dist;
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    private List<List<Node>> adj;

    public FinderByDijkstra() {
        super();
    }

    @Override
    public void init(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, new Node());
        adj = new ArrayList<>();


        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            this.adj.add(item);
        }

        ref.init(V);
    }

    @Override
    public void addEdge(int v, int w, Integer cost) {
        this.adj.get(v).add(new Node(w, cost));

        ref.addEdge(v, w, cost);
    }

    // Function for Dijkstra's Algorithm
    @Override
    public void find(int src) {
        System.out.println("\n[DECORATOR] Dijjkstra search starts...");

        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;

        // Add source node to the priority queue
        pq.add(new Node(src, 0));

        // Distance to the source is 0
        dist[src] = 0;
        while (settled.size() != V) {

            // remove the minimum distance node
            // from the priority queue
            int u = pq.remove().node;

            // adding the node whose distance is
            // finalized
            settled.add(u);

            e_Neighbours(u);
        }

        System.out.println("The shorted path from node :");
        for (int i = 0; i < dist.length; i++)
            System.out.println(src + " to " + i + " is " + dist[i]);

        ref.find(src);
    }

    // Function to process all the neighbours
    // of the passed node
    private void e_Neighbours(int u) {
        int edgeDistance = -1;
        int newDistance = -1;

        // All the neighbors of v
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;

                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }
}
