import dataStructures.EqualSetsException;
import dataStructures.UnionFind;
import dataStructures.UnionFindInArray;

import java.util.HashMap;
import java.util.Map;

public class PolygonPhobia {

    private static final int POINTS_PER_LINE = 2;

    private final UnionFind unionFind;
    private int counter;
    private final Map<Integer, Integer> pointMap;
    private int totalSegments;

    public PolygonPhobia(int segments) {
        this.unionFind = new UnionFindInArray(segments * POINTS_PER_LINE);
        this.counter = 0;
        this.totalSegments = 0;
        this.pointMap = new HashMap<>();
    }

    private void addPoint(int x, int y) {
        // (x,y) -> 1000x+y
        int key = 1000 * x + y;
        if (!this.pointMap.containsKey(key)) {
            this.pointMap.put(key, this.counter++);
        }
    }

    private int getPoint(int x, int y) {
        return this.pointMap.get(1000 * x + y);
    }

    public void addSegment(int x1, int y1, int x2, int y2) {
        // Add points
        addPoint(x1, y1);
        addPoint(x2, y2);

        // Get representatives
        int fstRep = this.unionFind.find(this.getPoint(x1, y1));
        int sndRep = this.unionFind.find(this.getPoint(x2, y2));

        try {
            this.unionFind.union(fstRep, sndRep);
            this.totalSegments++;
        } catch (EqualSetsException ignored) {
        }
    }

    public int solution() {
        return this.totalSegments;
    }

}
