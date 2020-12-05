package ru.miit.interfaces;

import org.jetbrains.annotations.Nullable;

public interface IGraphable {
    void find(int src);
    void addEdge(int v, int w, @Nullable Integer cost);
    void init(int v);
}
