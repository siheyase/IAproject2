package exercise2;
import java.util.*;

public class SearchPath {
    private final Pyramid pyramid;
    private final int k;
    private final int N;
    public SearchPath(Pyramid pyramid, int k) {
        this.pyramid = pyramid;
        this.k = k;
        this.N = pyramid.V();
    }
    public class Room implements Comparable<Room>{
        private int id; //房间id (1-N)
        private int g; // 节点到起点的距离
        private int h; // 节点到终点的估计距离，启发式函数
        private Room parent;

        public Room(int id) {
            this.id = id;
            this.g = 0;
            this.h = 1;
            this.parent = null;
        }
        public Room(int id, Room parent, int dis) {
            this.id =id;
            this.g = parent.g + dis;
            this.parent = parent;
            if (id == N) {
                this.h = 0;
            } else {
                this.h = 1;
            }
        }

        @Override
        public int compareTo(Room room) {
            if ((g+h) == (room.g+room.h)) {
                return g- room.g;
            } else {
                return (g+h)-(room.g+room.h);
            }
        }
        public int getId() {
            return id;
        }
        public int getG() {
            return g;
        }
        public String toString() {
            return "id:" + id + " " + "g:" + g + " " + "h:" + h;
        }
    }

    public int[] AstarSolve() {
        PriorityQueue<Room> open = new PriorityQueue<>(Room::compareTo);
        ArrayList<Room> closed = new ArrayList<>();
        Room start = new Room(1);
        open.add(start);
        while (!open.isEmpty()) {
            Room node = open.poll();
            closed.add(node);
//            System.out.println(node.id); //test
            ArrayList<Edge> nearRooms = pyramid.nearEdges(node.id);
            for (Edge edge : nearRooms) {
//                System.out.println(edge.toString()); //test
                if (!open.contains(new Room(edge.w(), node, edge.wt())) && !closed.contains(new Room(edge.w(), node, edge.wt()))) {
                    open.add(new Room(edge.w(), node, edge.wt()));
                }
            }
        }
        PriorityQueue<Room> successNode = new PriorityQueue<>(Room::compareTo);
        for (Room each : closed) {
//            System.out.println(each.toString());
            if (each.id == N) {
//                System.out.println("cost:" + each.getG());
                successNode.add(each);
            }
        }


        int[] pathLen = new int[k];
        for (int i = 0; i < k; ++i) {
            Room targetNode = successNode.poll();
            if (targetNode == null) {
                pathLen[i] = -1;
            } else {
                pathLen[i] = targetNode.getG();
            }
        }

        return pathLen;
    }

}
