import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int nCells = Integer.parseInt(input.readLine());
        int exitCell = Integer.parseInt(input.readLine());
        int timer = Integer.parseInt(input.readLine());

        MiceAndMaze miceAndMaze = new MiceAndMaze(nCells, exitCell, timer);

        int connections = Integer.parseInt(input.readLine());

        String[] tokens;

        for (int i = 0; i < connections; i++) {
            tokens = input.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            int t = Integer.parseInt(tokens[2]);

            miceAndMaze.addConnection(a, b, t);
        }

        input.close();
        System.out.println(miceAndMaze.solution());
    }
}
