import java.util.Iterator;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][] slates = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        Scanner scanner = new Scanner(System.in);
        System.out.println("input status:");
        String str = scanner.next();
        scanner.close();

        for (int i = 0; i < str.length(); i++) {
            int index_j = i % 3;
            int index_i = i / 3;
            slates[index_i][index_j] = Integer.valueOf(str.charAt(i)) - 48;
        }
        Board initial = new Board(slates);
        String s = initial.toString();
        System.out.println(s);
        Astar astar_solver = new Astar(initial);
        System.out.println("Minimum steps:");
        System.out.println(astar_solver.getSteps());
        System.out.println("Solution:");
        for (Board board : astar_solver.getSolution()) {
            System.out.println(board.toString());
        }
    }
}
