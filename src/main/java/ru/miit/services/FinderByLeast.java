package ru.miit.services;

import ru.miit.interfaces.IGraphable;
import ru.miit.utils.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FinderByLeast implements IGraphable {
    private Queue<Node> queue;
    private Node targetNode;
    private static ArrayList<Node> nodes;

    public FinderByLeast(ArrayList<Node> nodes, Node targetNode) {
        this.queue = new LinkedList<>();
        this.targetNode = targetNode;
        this.nodes = nodes;
    }

    private ArrayList<Node> findNeighbours(int adjacencyMatrix[][], Node x) {
        int nodeIndex = -1;

        ArrayList<Node> neighbours = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(x)) {
                nodeIndex = i;
                break;
            }
        }

        if (nodeIndex != -1) {
            for (int j = 0; j < adjacencyMatrix[nodeIndex].length; j++) {
                if (adjacencyMatrix[nodeIndex][j] == 1) {
                    neighbours.add(nodes.get(j));
                }
            }
        }
        return neighbours;
    }

    private String findByLeastNumberOVertices(int adjacencyMatrix[][], Node node) {
        String result = "";
        queue.add(node);
        node.visited = true;

        System.out.println();
        System.out.println(node.toString());

        while (!queue.isEmpty()) {
            System.out.println(result);
            Node element = queue.remove();
            result = result + " ";
            result = result.concat(String.valueOf(element.data));

            ArrayList<Node> neighbours = findNeighbours(adjacencyMatrix, element);

            for (int i = 0; i < neighbours.size(); i++) {
                Node n = neighbours.get(i);
                if (n != null && !n.visited) {
                    queue.add(n);
                    n.visited = true;

                }
            }

        }

        return result;
    }

    @Override
    public String find(int[][] adjacencyMatrix) {
        return findByLeastNumberOVertices(adjacencyMatrix, targetNode);
    }
}
