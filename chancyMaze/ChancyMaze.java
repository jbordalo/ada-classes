public class ChancyMaze {

    private final int INF = Integer.MAX_VALUE;

    private final int numNodes;
    private final Edge[] edges;
    private int edgesCounter;

    public ChancyMaze(int rooms, int corridors) {
        this.numNodes = rooms;
        this.edges = new Edge[corridors];
        this.edgesCounter = 0;
    }

    public void addCorridor(int head, int tail, int label) {
        this.edges[this.edgesCounter++] = new Edge(head, tail, label);
    }

    private boolean updateLengths(int[] len, int[] via) {
        boolean changes = false;

        for (Edge e : this.edges) {
            int tail = e.getTail();
            int head = e.getHead();

            if (len[tail] < INF) {
                int newLen = len[tail] + e.getLabel();
                if (newLen < len[head]) {
                    len[head] = newLen;
                    via[head] = tail;
                    changes = true;
                }
            }
        }
        return changes;
    }

    private boolean bellmanFord() {
        int[] length = new int[this.numNodes];
        int[] via = new int[this.numNodes];

        for (int i = 0; i < this.numNodes; i++)
            length[i] = INF;

        length[0] = 0;
        via[0] = 0;

        boolean changes = false;

        for (int i = 0; i < this.numNodes; i++) {
            changes = this.updateLengths(length, via);
            if (length[this.numNodes - 1] < 0) return true;
            if (!changes) break;
        }

        // Negative-weight cycles detection
        return changes && updateLengths(length, via);
    }

    public boolean solution() {
        return this.bellmanFord();
    }

}
