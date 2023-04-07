//import java.util.*;
//public class Solver {
//    private static class TreeNode implements Comparable<TreeNode> {
//        private final Board board;
//        private final TreeNode parent;
//        private final boolean moved;
//        private final int step;
//        private final int distance;
//        private final int priority;
//
//        // initial node
//        public TreeNode(Board board, boolean moved) {
//            this.board = board;
//            parent = null;
//            this.moved = moved;
//            step = 0;
//            distance = board.getManhattan();
//            priority = distance + step;
//        }
//
//        // following node
//        public TreeNode(Board board, TreeNode parent) {
//            this.board = board;
//            this.parent = parent;
//            moved = parent.moved;
//            step = parent.step + 1;
//            distance = board.getManhattan();
//            priority = distance + step;
//        }
//
//        public Board getBoard() {
//            return board;
//        }
//        public TreeNode getParent() {
//            return parent;
//        }
//        public boolean isMoved() {
//            return moved;
//        }
//
//        public int compareTo(TreeNode node) {
//            if (priority == node.priority) {
//                return Integer.compare(distance, distance);
//            } else {
//                return Integer.compare(priority, node.priority);
//            }
//        }
//
//        @Override
//        public boolean equals(Object node) {
//            if (node == null) {
//                return false;
//            }
//            if (this == node) {
//                return true;
//            }
//            if (node.getClass() != this.getClass()) {
//                return false;
//            }
//            TreeNode that = (TreeNode) node;
//            return getBoard().equals(that.getBoard());
//        }
//
//        public int hashcode() {
//            return 1;
//        }
//    }
//    private int steps;
//    private boolean solvable;
//    private ArrayList<Board> solution;
//    private final Board initial;
//
//    public Solver(Board initial) {
//        if (initial == null) {
//            throw  new IllegalArgumentException();
//        }
//        this.initial = initial;
//        cache();
//    }
//
//    public boolean isSolvable() {
//        return solvable;
//    }
//    public int getSteps() {
//        return steps;
//    }
//    public ArrayList<Board> getSolution() {
//        return solution;
//    }
//
//    private void cache() {
//        MinPQ<TreeNode> pq = new MinPQ<>();
//        pq.insert(new TreeNode(initial, false));
//        pq.insert(new TreeNode(initial.moved(), true));
//        TreeNode node = pq.delMin();
//        Board board = node.getBoard();
//
//        while (!board.isGoalStatus()) {
//            for (Board b: board.neighbors()) {
//                if (node.getParent() == null || b.equals(node.getParent().getBoard())) {
//                    pq.insert(new TreeNode(b, node));
//                }
//            }
//            node = pq.delMin();
//            board = node.getBoard();
//        }
//        solvable = !node.isMoved();
//
//        if (!solvable) {
//            steps = -1;
//            solution = null;
//        } else {
//            ArrayList<Board> list = new ArrayList<>();
//            while (node != null) {
//                list.add(node.getBoard());
//                node = node.getParent();
//            }
//            steps = list.size() -1;
//            Collections.reverse(list);
//            solution = list;
//        }
//    }
//
//    public static void main(String[] args) {
//        int[][] tile = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("input status:");
//        String str = scanner.next();
////        System.out.println(str);
//        scanner.close();
//
//        for (int i = 0; i < str.length(); i++) {
//            int index_j = i % 3;
//            int index_i = i / 3;
//            tile[index_i][index_j] = Integer.valueOf(str.charAt(i)) - 48;
////            System.out.println(tile[index_i][index_j]);
//        }
//        Board initial = new Board(tile);
//        Solver solver = new Solver(initial);
//
//        if (!solver.isSolvable()) {
//            System.out.println("No solution for this board");
//        } else {
//            System.out.println("Minimum number of moving steps is " + solver.getSteps());
//            for (Board board : solver.getSolution()) {
//                System.out.println(board.toString());
//            }
//        }
//    }
//}
