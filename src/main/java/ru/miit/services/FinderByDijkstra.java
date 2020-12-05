package ru.miit.services;

import ru.miit.interfaces.IGraphable;
import ru.miit.utils.Node;

import java.util.*;

public class FinderByDijkstra implements IGraphable {
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
    }

    @Override
    public void addEdge(int v, int w, Integer cost) {
        this.adj.get(v).add(new Node(w, cost));
    }

    // Function for Dijkstra's Algorithm
    @Override
    public void find(int src) {
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
