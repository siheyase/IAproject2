package exercise2;

import java.util.Scanner;

public class Solve {
    public static void main(String[] args) {
        int N, M, K;
        Scanner s = new Scanner(System.in);
        System.out.println("input Pyramid situation:");
        N = s.nextInt();
        M = s.nextInt();
        K = s.nextInt();
        s.nextLine();
        Pyramid pyramid = new Pyramid(N);
        int v, w, wt;
        for (int i = 0; i < M; ++i) {
            v = s.nextInt();
            w = s.nextInt();
            wt = s.nextInt();
            pyramid.addEdge(new Edge(v, w, wt));
            s.nextLine();
        }
//        pyramid.show();
        s.close();
        SearchPath solver = new SearchPath(pyramid, K);
        int[] path = solver.AstarSolve();
        for (int i = 0; i < K; ++i) {
            System.out.println(path[i]);
        }
    }
}
