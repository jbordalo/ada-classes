import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = input.readLine().split(" ");
        int toys = Integer.parseInt(tokens[0]);
        int children = Integer.parseInt(tokens[1]);

        ToysForChristmas toysForChristmas = new ToysForChristmas(toys, children);

        for (int toy = 0; toy < toys; toy++) {
            int units = Integer.parseInt(input.readLine());
            toysForChristmas.addToy(toy + 1, units);
        }

        for (int child = 0; child < children; child++) {
            tokens = input.readLine().split(" ");
            int nToys = Integer.parseInt(tokens[0]);

            for (int toy = 0; toy < nToys; toy++) {
                int preference = Integer.parseInt(tokens[toy + 1]);
                toysForChristmas.addSuitableToy(child, preference);
            }
        }

        input.close();
        System.out.println(toysForChristmas.solution());
    }
}
