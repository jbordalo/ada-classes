import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		String[] tokens = input.readLine().split(" ");
		int nPlayers = Integer.parseInt(tokens[0]);

		tokens = input.readLine().split(" ");
		int nRoles = Integer.parseInt(tokens[0]);

		TeamFormations teamFormations = new TeamFormations(nPlayers, nRoles);

		for (int i = 0; i < nRoles; i++) {
			tokens = input.readLine().split(" ");
			boolean constraintType = tokens[0].equals("MAX");
			int constraintValue = Integer.parseInt(tokens[1]);

			teamFormations.addConstraint(i, constraintType, constraintValue);
		}

		long answer = teamFormations.numFormations();

		input.close();
		System.out.println(answer);
	}
}
