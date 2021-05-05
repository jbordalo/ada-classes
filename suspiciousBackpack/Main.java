import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public final static String CONSISTENT = "Consistent conjectures";
    public final static String INCONSISTENT = "Inconsistent conjectures";

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = input.readLine().split(" ");
        int suspects = Integer.parseInt(tokens[0]);
        int preceding = Integer.parseInt(tokens[1]);
        int concurrent = Integer.parseInt(tokens[2]);

        SuspiciousBackpack suspiciousBackpack = new SuspiciousBackpack(suspects);

        for (int i = 0; i < preceding; i++) {
            tokens = input.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            suspiciousBackpack.addPrecedingConjecture(x, y);
        }

        for (int i = 0; i < concurrent; i++) {
            tokens = input.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            suspiciousBackpack.addConcurrentConjecture(x, y);
        }

        input.close();

        System.out.println(suspiciousBackpack.solution() ? CONSISTENT : INCONSISTENT);
    }
}
