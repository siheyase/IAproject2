package exercise1;

import java.util.*;
public class Astar {
    // 定义一个树结构
    private static class TreeNode implements Comparable<TreeNode> {
        private final Board board;
        private final TreeNode parent;
        private final int step;
        private final int distance;
        private final int priority;

        // 定义二叉树的根节点
        public TreeNode(Board board) {
            this.board = board;
            parent = null;
            step = 0;
            distance = board.getManhattan();
            priority = distance; // 优先级(f)
        }

        // 定义后续节点
        public TreeNode(Board board, TreeNode parent) {
            this.board = board;
            this.parent = parent;
            distance = board.getManhattan(); //g
            step = parent.step + 1;  //h
            priority = distance + step; //f=g+h
        }

        public Board getBoard() {
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

    }

    private int steps;
    private ArrayList<Board> solution;
    private final Board initial;

    public Astar(Board initial) {
        this.initial = initial;
        search();
    }
    public void search() {
        PriorityQueue<TreeNode> pq = new PriorityQueue<>(TreeNode::compareTo);
        ArrayList<TreeNode> isOpened = new ArrayList<>();
        if (pq.comparator() == null) {
            System.out.println("error comparator");
        }
        pq.add(new TreeNode(initial)); // 将初始状态加入优先级队列
        TreeNode node = pq.poll(); // 弹出初始节点
        isOpened.add(node); // 弹出的节点放入已打开的队列。
        assert node != null;
        Board board = node.getBoard();

        while(!board.isGoalStatus()) {
            for (Board b : board.neighbors()) {
                // 条件：优先级队列不含该节点，同时该节点从未被打开过
                if (!pq.contains(new TreeNode(b, node)) && !isOpened.contains(new TreeNode(b, node))) {
                    pq.add(new TreeNode(b, node));
                }
                 // 将node的下层走法放入优先队列中
            }
            node = pq.poll(); // 找到最低f值的走法
            isOpened.add(node); // 将其放入已打开的队列
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
