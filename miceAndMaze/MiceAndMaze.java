import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;


public class MiceAndMaze {

    private final int INF = Integer.MAX_VALUE;

    private final int numNodes;
    private final int exitCell;
    private final int timer;
    private final List<Edge>[] outIncidentEdges;


    @SuppressWarnings("unchecked")
    public MiceAndMaze(int nCells, int exitCell, int timer) {
        this.numNodes = nCells;
        this.exitCell = exitCell - 1;
        this.timer = timer;
        this.outIncidentEdges = new LinkedList[this.numNodes];

        for (int i = 0; i < this.numNodes; i++) {
            this.outIncidentEdges[i] = new LinkedList<>();
        }
    }

    public void addConnection(int a, int b, int t) {
        // A --t--> B
        // We add the inverse edge for this specific solution
        this.outIncidentEdges[b - 1].add(new Edge(a - 1, b - 1, t));
    }

    private void exploreNode(int source, boolean[] selected, int[] length, PriorityQueue<SimpleEntry<Integer, Integer>> connected) {
        for (Edge e : this.outIncidentEdges[source]) {
            int node = e.getHead();

            if (!selected[node]) {
                int newLength = length[source] + e.getLabel();
                if (newLength <= this.timer && newLength < length[node]) {
                    boolean nodeIsInQueue = length[node] < INF;
                    SimpleEntry<Integer, Integer> oldPair = new SimpleEntry<>(length[node], node);
                    length[node] = newLength;

                    if (nodeIsInQueue) {
                        // This will emulate a decreaseKey
                        connected.remove(oldPair);
                    }

                    connected.add(new SimpleEntry<>(newLength, node));
                }
            }
        }
    }

    private int[] dijkstra(int origin) {
        boolean[] selected = new boolean[this.numNodes];
        int[] length = new int[this.numNodes];

        PriorityQueue<SimpleEntry<Integer, Integer>> connected = new PriorityQueue<>(this.numNodes, new EntryComparator());

        for (int v = 0; v < this.numNodes; v++) {
            selected[v] = false;
            length[v] = INF;
        }

        length[origin] = 0;

        connected.add(new SimpleEntry<>(0, origin));

        do {
            int node = connected.remove().getValue();
            selected[node] = true;
            exploreNode(node, selected, length, connected);
        }
        while (!connected.isEmpty());

        return length;
    }

    public int solution() {
        int[] length = this.dijkstra(exitCell);

        int rats = 0;
        for (int i = 0; i < this.numNodes; i++) {
            if (length[i] <= this.timer) rats++;
        }

        return rats;
    }

}
