package exercise2;
import java.util.*;
import java.lang.Comparable;

public class Edge {
    private int a, b; //
    private int weight;

    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public int v() { return a;}
    public int w() { return b;}
    public int wt() { return weight;}

    public String toString() {
        return "" + a + "-" + b + ":" + weight;
    }

}
