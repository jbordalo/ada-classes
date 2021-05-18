import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public final static String YES = "yes";
    public final static String NO = "no";

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = input.readLine().split(" ");
        int rooms = Integer.parseInt(tokens[0]);
        int corridors = Integer.parseInt(tokens[1]);

        ChancyMaze chancyMaze = new ChancyMaze(rooms, corridors);

        for (int i = 0; i < corridors; i++) {
            tokens = input.readLine().split(" ");
            int tail = Integer.parseInt(tokens[0]);
            int head = Integer.parseInt(tokens[1]);
            String mode = tokens[2];
            int label = Integer.parseInt(tokens[3]);

            label = mode.equals("C") ? -label : label;

            chancyMaze.addCorridor(head, tail, label);
        }

        input.close();

        System.out.println(chancyMaze.solution() ? YES : NO);
    }
}
