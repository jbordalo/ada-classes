import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input =
				new BufferedReader(new InputStreamReader(System.in));

		String[] tokens = input.readLine().split(" ");
		int n_children = Integer.parseInt(tokens[0]);
		int best = Integer.MIN_VALUE;

		// For each child
		for (int n = 0; n < n_children; n++) {
			String[] c_tokens = input.readLine().split(" ");
			int n_eggs = Integer.parseInt(c_tokens[0]);
			for (int i = 1; i <= n_eggs; i++) {
				int curr = Integer.parseInt(c_tokens[i]);
				best = Math.max(best, curr);
			}
		}

		input.close();
		System.out.println(best);
	}
}