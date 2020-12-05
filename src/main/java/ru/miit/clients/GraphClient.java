package ru.miit.clients;

import org.jetbrains.annotations.Nullable;
import ru.miit.interfaces.IGraphable;

import javax.inject.Inject;

public class GraphClient {
    private IGraphable refGraphable;

    @Inject
    public GraphClient(IGraphable iGraphable) {
        this.refGraphable = iGraphable;
    }


    public void init(int V) {
        refGraphable.init(V);
    }

    public void addEdge(int v, int w, @Nullable int cost) {
        refGraphable.addEdge(v, w, cost);
    }

    public void find(int src) {
        refGraphable.find(src);
    }
}
