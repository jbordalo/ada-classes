import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int segments = Integer.parseInt(input.readLine());

        PolygonPhobia polygonPhobia = new PolygonPhobia(segments);

        String[] tokens;
        for (int i = 0; i < segments; i++) {
            tokens = input.readLine().split(" ");
            int x1 = Integer.parseInt(tokens[0]);
            int y1 = Integer.parseInt(tokens[1]);
            int x2 = Integer.parseInt(tokens[2]);
            int y2 = Integer.parseInt(tokens[3]);
            polygonPhobia.addSegment(x1, y1, x2, y2);
        }

        input.close();

        System.out.println(polygonPhobia.solution());
    }
}
