import java.util.*;

public class HardWeeks {

	private int numNodes;
	private int limit;

	private List<Integer>[] directSuccessors;
	private int[] inDegree;

	@SuppressWarnings("unchecked")
	public HardWeeks(int tasks, int hardLimit) {
		this.numNodes = tasks;
		this.limit = hardLimit;

		this.directSuccessors = new List[this.numNodes];

		this.inDegree = new int[this.numNodes];

		for (int i = 0; i < this.numNodes; i++) {
			this.directSuccessors[i] = new LinkedList<>();
		}
	}

	public void addPrecendence(int x, int y) {
		// Y is a successor of X
		this.directSuccessors[x].add(y);
		this.inDegree[y]++;
	}

	private int[] topologicalSort() {
		LinkedList<Integer> currentWeek = new LinkedList<>();
		LinkedList<Integer> nextWeek = new LinkedList<>();

		int hardWeeks = 0;

		// Initialize
		for (int i = 0; i < this.numNodes; i++) {
			if (this.inDegree[i] == 0) {
				currentWeek.add(i);
			}
		}

		int maxTasks = currentWeek.size();

		if (currentWeek.size() > this.limit) hardWeeks++;

		while(!currentWeek.isEmpty()) {
			int node = currentWeek.removeLast();

			// Prepare next week
			for (Integer integer : this.directSuccessors[node]) {
				this.inDegree[integer]--;
				if (this.inDegree[integer] == 0) {
					nextWeek.add(integer);
				}
			}

			if (currentWeek.isEmpty()) {
				if (nextWeek.size() > this.limit) hardWeeks++;
				maxTasks = Math.max(maxTasks, nextWeek.size());

				// Switch bags
				LinkedList<Integer> temp;
				temp = currentWeek;
				currentWeek = nextWeek;
				nextWeek = temp;
			}
		}

		return new int[]{maxTasks, hardWeeks};
	}

	public int[] hardWeeks() {
		return topologicalSort();
	}
}
