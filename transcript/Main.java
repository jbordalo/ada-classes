import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		String nRowsInput = input.readLine();
		int nRows = Integer.parseInt(nRowsInput);

		Transcript transcript = new Transcript(nRows);

		for (int i = 0; i < nRows; i++) {
			String line = input.readLine();

			transcript.addKeyboardLine(line, i);
		}

		String original = input.readLine();
		String transcriptLine = input.readLine();
		

		int answer = transcript.giveScore(original, transcriptLine);
		input.close();
		System.out.println(answer);
	}
}
