package net.mahdirazavi.test.graphcoloring;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class AdjMatrixGraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private int V;
    private int E;
    private boolean[][] adjMatrix;

    // empty graph with V vertices
    public AdjMatrixGraph(int V) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        this.adjMatrix = new boolean[V][V];
    }


    /**
     * /Create random graph with V vertices and E edges
     *
     * @param V
     * @param E
     */
    public AdjMatrixGraph(int V, int E) {
        this(V);
        if (E < 0) throw new RuntimeException("Number of edges must be nonnegative");
        if (E > V * (V - 1) + V) throw new RuntimeException("Too many edges");

        // can be inefficient
        while (this.E != E) {
            int v = new Random().nextInt(V);
            int w = new Random().nextInt(V);
            addEdge(v, w);
        }
    }

    public String showAdjMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append((adjMatrix[i][j] ? 1 : 0) + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getMaximumDegree() {
        int maxDegree = 0;
        for (int i = 0; i < V; i++) {
            int degree = 0;
            for (int j = 0; j < V; j++) {
                degree += (adjMatrix[i][j] ? 1 : 0);
            }
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }

        return maxDegree;
    }

    // number of vertices and edges
    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }


    // add undirected edge v-w
    public void addEdge(int v, int w) {
        if (!adjMatrix[v][w]) E++;
        adjMatrix[v][w] = true;
        adjMatrix[w][v] = true;
    }

    // does the graph contain the edge v-w?
    public boolean contains(int v, int w) {
        return adjMatrix[v][w];
    }

    // return list of neighbors of v
    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }


    // support iteration over graph vertices
    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        private int v;
        private int w = 0;

        AdjIterator(int v) {
            this.v = v;
        }

        public Iterator<Integer> iterator() {
            return this;
        }

        public boolean hasNext() {
            while (w < V) {
                if (adjMatrix[v][w]) return true;
                w++;
            }
            return false;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return w++;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // string representation of Graph - takes quadratic time
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    // test client
    public static void main(String[] args) {
        int V = 6;//Integer.parseInt(5);
        int E = 7;//Integer.parseInt(7);
        AdjMatrixGraph G = new AdjMatrixGraph(V, E);
        System.out.println(G);
        System.out.println(G.showAdjMatrix());
        System.out.println("max degree: " + G.getMaximumDegree());
    }

}

