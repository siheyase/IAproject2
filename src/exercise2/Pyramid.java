package exercise2;
import java.util.*;
// 构造一个类定义金字塔内的房间 数据结构：图
public class Pyramid {
    private final int N; // 房间个数N
    private int M; // 通道个数
    private Edge[][] rooms;

    public Pyramid(int N) {
        assert N > 0;
        this.N = N;
        this.M = 0;
        rooms = new Edge[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                rooms[i][j] = null;
            }
        }
    }

    public int V() { return N;}
    public int E() { return M;}

    public void addEdge(Edge e) {
        assert e.v() >= 1 && e.v() < N;
        assert e.w() >= 1 && e.w() < N;

        if (hasEdge(e.v(), e.w())) {
            return;
        }
        if (e.v() < e.w()) {
            rooms[e.v()-1][e.w()-1] = e; // 有向边，只保留高往低的路径（低-高不走）
            M = M + 1;
        }
    }

    public boolean hasEdge(int v, int w) {
        assert v >= 1 && v <= N;
        assert w >= 1 && w <= N;
        return rooms[v-1][w-1] != null;
    }

    public void show() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (rooms[i][j] != null) {
                    System.out.println(rooms[i][j].toString());
                }
            }
        }
    }

    public ArrayList<Edge> nearEdges(int v) {
        assert v >= 1 && v <= N;
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            if (rooms[v-1][i] != null) {
                edges.add(rooms[v-1][i]);
            }
        }
        return edges;
    }

}
