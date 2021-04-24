import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		String[] tokens = input.readLine().split(" ");
		int tasks = Integer.parseInt(tokens[0]);
		int precedences = Integer.parseInt(tokens[1]);
		int hardLimit = Integer.parseInt(tokens[2]);

		HardWeeks hardweeks = new HardWeeks(tasks, hardLimit);

		for (int i = 0; i < precedences; i++) {
			tokens = input.readLine().split(" ");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			hardweeks.addPrecendence(x, y);
		}

		input.close();

		int[] solution = hardweeks.hardWeeks();

		System.out.printf("%d %d%n", solution[0], solution[1]);
	}
}
