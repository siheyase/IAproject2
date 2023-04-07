import java.util.*;
public class Astar {
    // 定义一个二叉树结构
    private static class TreeNode implements Comparable<TreeNode> {
        private final Board board;
        private final TreeNode parent;
//        private final boolean inQueue;
        private final int step;
        private final int distance;
        private final int priority;

        // 定义二叉树的根节点
        public TreeNode(Board board) {
            this.board = board;
            parent = null;
//            inQueue = inQueue;
            step = 0;
            distance = board.getManhattan();
            priority = distance; // 优先级(f)
        }

        // 定义后续节点
        public TreeNode(Board board, TreeNode parent) {
            this.board = board;
            this.parent = parent;
//            inQueue = parent.inQueue;
            distance = board.getManhattan(); //g
            step = parent.step + 1;  //h
            priority = distance + step; //f=g+h
        }

        public  Board getBoard() {
            return board;
        }
        public TreeNode getParent() {
            return parent;
        }
        // 定义树节点的比较函数
        @Override
        public int compareTo(TreeNode node) {
            if (priority == node.priority) {
                return Integer.compare(distance, node.distance);
            } else {
                return Integer.compare(priority, node.priority);
            }
        }

        @Override
        public boolean equals(Object node) {
            if (node == null) {
                return false;
            }
            if (this == node) {
                return true;
            }
            if (node.getClass() != this.getClass()) {
                return false;
            }
            TreeNode that = (TreeNode) node;
            return getBoard().equals(that.getBoard());
        }
    }

    private int steps;
//    private boolean isSolvable;
    private ArrayList<Board> solution;
    private final Board initial;

    public Astar(Board initial) {
        this.initial = initial;
        search();
    }
    public void search() {
        PriorityQueue<TreeNode> q = new PriorityQueue<>(TreeNode::compareTo);
        if (q.comparator() == null) {
            System.out.println("error comparator");
        }
        q.add(new TreeNode(initial));
        TreeNode node = q.poll();
        assert node != null;
        Board board = node.getBoard();

        while(!board.isGoalStatus()) {
            for (Board b : board.neighbors()) {
                if (!q.contains(new TreeNode(b, node))) {
                    q.add(new TreeNode(b, node));
                }
                 // 将node的下层走法放入优先队列中
            }
            node = q.poll(); // 找到最低f值的走法
            assert node != null;
            board = node.getBoard();
        }

        ArrayList<Board> move_list = new ArrayList<>();
        while (node != null) {
            move_list.add(node.getBoard());
            node = node.getParent();
        }
        steps = move_list.size() - 1;
        Collections.reverse(move_list);
        solution = move_list;
    }

    public int getSteps() {
        return steps;
    }
    public ArrayList<Board> getSolution() {
        return solution;
    }

}
