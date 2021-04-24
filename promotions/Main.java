import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
		String[] tokens = input.readLine().split(" ");
		int lower = Integer.parseInt(tokens[0]);
		int upper = Integer.parseInt(tokens[1]);
		int employees = Integer.parseInt(tokens[2]);
		int precedences = Integer.parseInt(tokens[3]);
		
		Promotions promotions = new Promotions(lower, upper, employees);

		for (int i = 0; i < precedences; i++) {
			tokens = input.readLine().split(" ");
			int x = Integer.parseInt(tokens[0]);
			int y = Integer.parseInt(tokens[1]);
			promotions.addPrecendence(x, y);
		}

		int[] solution = promotions.promotions();
		for (int i = 0; i < 3; i++) {
			System.out.println(solution[i]);
		}
		
		input.close();
	}

}
