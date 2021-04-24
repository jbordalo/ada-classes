import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Promotions {
	
	private int numNodes;
	private int lower;
	private int upper;
	private List<Integer>[] directSuccessors;
	private List<Integer>[] directPredecessors;
	private int[] successors;
	private int[] predecessors;

	@SuppressWarnings("unchecked")
	public Promotions(int lower, int upper, int employees) {
		this.numNodes = employees;
		this.lower = lower;
		this.upper = upper;
		this.directSuccessors = new List[employees];
		this.directPredecessors = new List[employees];

		for (int i = 0; i < numNodes; i++) {
			directSuccessors[i] = new LinkedList<Integer>();
			directPredecessors[i] = new LinkedList<Integer>();
		}

		this.successors = new int[employees];
		this.predecessors = new int[employees];
	}

	public void addPrecendence(int x, int y) {
		// Y is a successor of X
		directSuccessors[x].add(y);
		// X is an predecessor of Y
		directPredecessors[y].add(x);		
	}

	private void dfsExplore(int root, List<Integer>[] nodeAdjacencies, int[] result) {
		Stack<Integer> foundUnprocessed = new Stack<Integer>();

		boolean[] processed = new boolean[this.numNodes];

		foundUnprocessed.push(root);

		result[root]--;

		do {
			int node = foundUnprocessed.pop();

			if (!processed[node]) {
				processed[node] = true;
				result[root]++;
				for (Integer integer : nodeAdjacencies[node]) {
					if (!processed[integer]) {				
						foundUnprocessed.push(integer);
					}
				}
			}
		} while(!foundUnprocessed.isEmpty());

	}

	private void dfsTraversal(List<Integer>[] list, int[] result) {
		for (int i = 0; i < this.numNodes; i++) {
			dfsExplore(i, list, result);
		}
	}

	public int[] promotions() {
		int[] solution = new int [3];

		dfsTraversal(this.directSuccessors, this.successors);
		dfsTraversal(this.directPredecessors, this.predecessors);

		for (int i = 0; i < numNodes; i++) {
			if (this.lower >= this.numNodes - this.successors[i]) solution[0]++;
			if (this.upper >= this.numNodes - this.successors[i]) solution[1]++;
			if (this.upper <= this.predecessors[i]) solution[2]++;
		}

		return solution;
	}
}
