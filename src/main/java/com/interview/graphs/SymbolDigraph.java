package com.interview.graphs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SymbolDigraph {

    private Map<String, Integer> stringIndexMap; //string -> index
    private String[] keys; //index -> string
    private Digraph graph;

    public SymbolDigraph(String filename, String delimiter) throws FileNotFoundException {
        stringIndexMap = new HashMap<>();

        Scanner scanner = new Scanner(new FileInputStream(filename));
        while (scanner.hasNextLine()) {
            String[] a = scanner.nextLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if(!stringIndexMap.containsKey(a[i])) {
                    stringIndexMap.put(a[i], stringIndexMap.size());
                }
            }
        }

        keys = new String[stringIndexMap.size()];
        for (String name : stringIndexMap.keySet()) {
            keys[stringIndexMap.get(name)] = name;
        }

        graph = new Digraph(stringIndexMap.size());

        scanner = new Scanner(new FileInputStream(filename));
        while (scanner.hasNextLine()) {
            String[] a = scanner.nextLine().split(delimiter);
            int v = stringIndexMap.get(a[0]);
            for(int i = 1; i < a.length; i++) {
                int w = stringIndexMap.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return stringIndexMap.containsKey(s);
    }

    public int indexOf(String s) {
        return stringIndexMap.get(s);
    }

    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public Digraph digraph() {
        return graph;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filename = "/Users/shubham.kankaria/interview-prep/src/main/resources/routes.txt";
        String delimiter = " ";
        SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
        System.out.println(sg.digraph());
        System.out.println(sg.nameOf(0));
    }
}
