import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// 定义一个棋盘类，
public class Board {
    private final int[][] slates;  // 存储9块石板
    private final int n;  // 石板的尺寸：n*n
    // 到目标状态的汉明距离
    private final int hamming;
    // 到目标状态的曼哈顿距离
    private final int manhattan;

    public Board(int[][] slates) {
        n = slates.length;
        this.slates = new int[n][n];
        for (int i = 0; i < n ; ++i) {
            System.arraycopy(slates[i], 0, this.slates[i], 0, n);
        }
        this.hamming = calculate_Hamming(slates);
        this.manhattan = calculate_manhattan(slates);
    }

    // 计算的是有多少个板块不在正确位子上
    public int calculate_Hamming(int[][] slates) {
        // 目标状态：1 3 5 7 0 2 6 8 4
        int[][] target = {{1,3,5}, {7,0,2}, {6,8,4}};
        int hammingDis = 0;
        for (int i = 0; i < n ; ++i) {
            for (int j = 0; j < n; ++j) {
                if (slates[i][j] != 0) {
                    int targetStatus = target[i][j];
                    hammingDis += slates[i][j] != targetStatus ? 1 : 0;
                }
            }
        }
        return hammingDis;
    }

    // 计算的是所有板块到目标位置的曼哈顿距离之和
    public int calculate_manhattan(int[][] slates) {
        int[][] target = {{1,3,5}, {7,0,2}, {6,8,4}};
        int manhattanDis = 0;
        for (int i = 0; i < n ; ++i) {
            for (int j = 0; j < n; ++j) {
                if (slates[i][j] != 0) {
                    for (int k = 0; k < n ; ++k) {
                        for (int q = 0; q < n; ++q) {
                            if (target[k][q] == slates[i][j]) {
                                manhattanDis += Math.abs(k - i) + Math.abs(q-j);
                            }
                        }
                    }
                }
            }
        }
        return manhattanDis;
    }

    // 输出board上状态
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                stringBuilder.append(slates[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int dimension() {
        return n;
    }
    public int getHamming() {
        return hamming;
    }
    public int getManhattan() {
        return manhattan;
    }

    public boolean isGoalStatus() {
        return getHamming() == 0;
    }

    @Override
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (this == y) {
            return true;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        Board board = (Board) y;
        return Arrays.deepEquals(slates, board.slates);
    }

    public ArrayList<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        int x = 0;
        int y = 0;
        // 找到值为0的下标，只能它周围的板块向0处移动
        for (int i = 0;i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (slates[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
        // 移动：从上、左、右、下向0处
        int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for (int[] direction : directions) {
            int xx = x + direction[0];
            int yy = y + direction[1];
            if (isValid(xx, yy)) {
                neighbors.add(new Board(moveBoard(x, y, xx, yy)));
            }
        }
        return neighbors;
    }

    // 判断元素的下标位置是否会越界，即0块周围的块是否为有效块
    private boolean isValid(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    // 复制一个新数组，在新数组里移动到指定位置
    private int[][] moveBoard (int x, int y, int xx, int yy) {
        int[][] templates = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(slates[i], 0, templates[i], 0, n);
        }
        int temp = templates[x][y];
        templates[x][y] = templates[xx][yy];
        templates[xx][yy] = temp;
        return templates;
    }

//    // 位子后得到的棋盘
//    public Board moved() {
//        Board board = null;
//        for (int i =0; i < n; i++) {
//            int x = i / n;
//            int y = i % n;
//            int xx = (i + 1) / n;
//            int yy = (i + 1) % n;
//            if (slates[x][y] != 0 && slates[xx][yy] != 0) {
//                board = new Board(moveBoard(x, y, xx, yy));
//                break;
//            }
//        }
//        return board;
//    }

    public static void main(String[] args) {
        int[][] tile = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        Scanner scanner = new Scanner(System.in);
        System.out.println("input status:");
        String str = scanner.next();
        scanner.close();

        for (int i = 0; i < str.length(); i++) {
            int index_j = i % 3;
            int index_i = i / 3;
            tile[index_i][index_j] = Integer.valueOf(str.charAt(i)) - 48;
        }

        Board board = new Board(tile);
        String s = board.toString();
        System.out.println(s);
        System.out.println(board.getHamming());
        System.out.println(board.getManhattan());
        System.out.println(board.isGoalStatus());
    }
}
