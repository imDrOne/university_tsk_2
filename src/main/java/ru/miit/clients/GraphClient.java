package ru.miit.clients;

import ru.miit.interfaces.IGraphable;

import javax.inject.Inject;

public class GraphClient {
    private IGraphable refGraphable;

    @Inject
    public GraphClient(IGraphable iGraphable) {
        this.refGraphable = iGraphable;
    }

    public String find() {
        return "Hello";
    }

}
