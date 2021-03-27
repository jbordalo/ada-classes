import java.util.HashMap;
import java.util.Map;

public class Transcript {

	private Map<Character, Integer[]> keyboard;
	private int[][] scores;
	private int T;

	public Transcript(int rows) {
		this.keyboard = new HashMap<Character, Integer[]>();
		this.T = rows;
	}

	public void addKeyboardLine(String line, int row) {

		this.T = Math.max(T, line.length());

		for (int i = 0; i < line.length(); i++) {
			Integer[] coords = { row, i };
			this.keyboard.put(line.charAt(i), coords);
		}

	}
	
	public int points(char t, char o) {
		
		Integer[] tPos = this.keyboard.get(t);
		Integer[] oPos = this.keyboard.get(o);
		
		return this.T - Math.max(Math.abs(tPos[0]-oPos[0]), Math.abs(tPos[1]-oPos[1]));
	}

	public int giveScore(String original, String transcript) {
		int m = transcript.length();
		int n = original.length();

		this.scores = new int[m + 1][n + 1];

		for (int i = 0; i < m+1; i++) {
			for (int j = i; j < n+1; j++) {

				if (i == 0) {
					this.scores[i][j] = j * (-this.T);
				} else {
					char t = transcript.charAt(i-1);
					char o = original.charAt(j-1);
					
					if (j == i) {
						this.scores[i][j] = this.scores[i-1][j-1] + points(t,o);
					} else {
						this.scores[i][j] = Math.max(
										this.scores[i-1][j-1]+points(t,o),
										this.scores[i][j-1]-this.T);
					}
				}

			}
		}
		
		return this.scores[m][n];
	}

}
