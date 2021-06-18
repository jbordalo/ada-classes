import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ToysForChristmas {

    private final int SOURCE = 0;
    private final int SINK;
    private final int VALUE = 1;
    private final int FIRST_CHILD;
    private final int INF = Integer.MAX_VALUE;

    private final int numNodes;
    private final List<Edge>[] outIncidentEdges;
    private final List<Edge> edges;

    @SuppressWarnings("unchecked")
    public ToysForChristmas(int toys, int children) {
        this.numNodes = 2 + toys + children;
        SINK = numNodes - 1;
        FIRST_CHILD = toys + 1;
        this.outIncidentEdges = new LinkedList[this.numNodes];
        this.edges = new LinkedList<>();

        for (int i = 0; i < this.numNodes; i++)
            this.outIncidentEdges[i] = new LinkedList<>();

        this.linkToSink();
    }

    private void linkToSink() {
        for (int child = FIRST_CHILD; child < SINK; child++) {
            Edge e = new Edge(child, SINK, 0);
            this.outIncidentEdges[SINK].add(e);
            this.edges.add(e);
            Edge e1 = new Edge(SINK, child, 1);
            this.outIncidentEdges[child].add(e1);
            this.edges.add(e1);
        }
    }

    public void addToy(int toy, int units) {
        Edge e = new Edge(toy, SOURCE, units);
        this.outIncidentEdges[SOURCE].add(e);
        this.edges.add(e);
        Edge e1 = new Edge(SOURCE, toy, 0);
        this.outIncidentEdges[toy].add(e1);
        this.edges.add(e1);
    }

    public void addSuitableToy(int child, int toy) {
        Edge e = new Edge(child + FIRST_CHILD, toy, VALUE);
        this.outIncidentEdges[toy].add(e);
        this.edges.add(e);
        Edge e1 = new Edge(toy, child + FIRST_CHILD, 0);
        this.outIncidentEdges[child + FIRST_CHILD].add(e1);
        this.edges.add(e1);
    }

    private int findPath(int[][] flow, int[] via) {
        Queue<Integer> waiting = new LinkedList<>();
        boolean[] found = new boolean[this.numNodes];

        int[] pathIncr = new int[this.numNodes];
        waiting.add(SOURCE);
        found[SOURCE] = true;
        via[SOURCE] = SOURCE;
        pathIncr[SOURCE] = INF;

        do {
            int origin = waiting.remove();
            for (Edge e : this.outIncidentEdges[origin]) {
                int destin = e.getHead();
                int residue = e.getLabel() - flow[origin][destin];
                if (!found[destin] && residue > 0) {
                    via[destin] = origin;
                    pathIncr[destin] = Math.min(pathIncr[origin], residue);
                    if (destin == SINK)
                        return pathIncr[destin];
                    waiting.add(destin);
                    found[destin] = true;
                }
            }
        }
        while (!waiting.isEmpty());
        return 0;
    }

    private int edmondsKarp() {
        int[][] flow = new int[this.numNodes][this.numNodes];
        int[] via = new int[this.numNodes];

        for (Edge edge : this.edges) {
            flow[edge.getTail()][edge.getHead()] = 0;
        }

        int flowValue = 0;
        int increment;

        while ((increment = this.findPath(flow, via)) != 0) {
            flowValue += increment;
            // Update flow.
            int node = SINK;
            while (node != SOURCE) {
                int origin = via[node];
                flow[origin][node] += increment;
                flow[node][origin] -= increment;
                node = origin;
            }
        }
        return flowValue;
    }

    public int solution() {
        return this.edmondsKarp();
    }

}
