public class TeamFormations {

	private int nPlayers;
	private int nRoles;

	private int[][] constr; // role: [min, max]
	private long[][] teams;

	public TeamFormations(int nPlayers, int nRoles) {
		this.nPlayers = nPlayers;
		this.nRoles = nRoles;
		this.constr = new int[nRoles][2];
		this.teams = new long[nRoles][nPlayers + 1];
	}

	private boolean belongs(int n, int min, int max) {
		return min <= n && n <= max;
	}

	// boolean type - true for max, false for min
	public void addConstraint(int role, boolean type, int value) {
		if (type) {
			this.constr[role][0] = 0;
			this.constr[role][1] = value;
		} else {
			this.constr[role][0] = value;
			this.constr[role][1] = this.nPlayers;
		}
	}

	public long numFormations() {
		for (int r = 0; r < this.nRoles; r++) {
			for (int p = 0; p < this.nPlayers + 1; p++) {
				// Base case
				if (r == 0) {
					int min = this.constr[r][0], max = this.constr[r][1];
					if (belongs(p, min, max)) {
						this.teams[r][p] = 1;
					} else {
						this.teams[r][p] = 0;
					}
				}
				// Iterative Step
				else {
					long sum = 0;
					int upperBound = Math.min(this.constr[r][1], p);

					for (int k = this.constr[r][0]; k <= upperBound; k++) {
						sum += this.teams[r - 1][p - k];
					}

					this.teams[r][p] = sum;
				}
			}
		}

		return teams[nRoles - 1][nPlayers];
	}
}
