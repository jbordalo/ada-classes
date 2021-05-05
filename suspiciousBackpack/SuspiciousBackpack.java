import java.util.LinkedList;
import java.util.List;

public class SuspiciousBackpack {

    private final int numNodes;
    private final List<Integer>[] nodeAdjacencies;
    private final int[] inDegree;

    @SuppressWarnings("unchecked")
    public SuspiciousBackpack(int suspects) {
        this.numNodes = 2 * suspects;
        this.nodeAdjacencies = new List[this.numNodes];
        this.inDegree = new int[this.numNodes];

        for (int i = 0; i < this.numNodes; i++) {
            this.nodeAdjacencies[i] = new LinkedList<>();
        }
    }

    public void addPrecedingConjecture(int x, int y) {
        // Xi -> Xf; Yi -> Yf; Xf -> Yi
        // Xf = Xi + numNodes
        int xf = x + this.numNodes / 2;
        int yf = y + this.numNodes / 2;

        // Xi -> Xf
        this.nodeAdjacencies[x].add(xf);
        this.inDegree[xf]++;
        // Yi -> Yf
        this.nodeAdjacencies[y].add(yf);
        this.inDegree[yf]++;
        // Xf -> Yi
        this.nodeAdjacencies[xf].add(y);
        this.inDegree[y]++;
    }

    public void addConcurrentConjecture(int x, int y) {
        // Xi -> Yf; Yi -> Xf
        int xf = x + this.numNodes / 2;
        int yf = y + this.numNodes / 2;
        // Xi -> Yf
        this.nodeAdjacencies[x].add(yf);
        this.inDegree[yf]++;
        // Yi -> Xf
        this.nodeAdjacencies[y].add(xf);
        this.inDegree[xf]++;
    }

    private boolean isAcyclic() {
        int numProcNodes = 0;
        LinkedList<Integer> ready = new LinkedList<>();

        for (int i = 0; i < this.numNodes; i++) {
            if (this.inDegree[i] == 0) {
                ready.push(i);
            }
        }

        while (!ready.isEmpty()) {
            int node = ready.pop();

            numProcNodes++;

            for (Integer integer : this.nodeAdjacencies[node]) {
                this.inDegree[integer]--;
                if (this.inDegree[integer] == 0) {
                    ready.push(integer);
                }
            }

        }

        return numProcNodes == this.numNodes;
    }

    public boolean solution() {
        return this.isAcyclic();
    }

}
