
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class Main {

    public static void main(String[] args) {
	    int ii;
        for(ii = 0; ii < 10; ii++) {
            StdOut.printf("%d = %d\n", ii, StdRandom.uniform(10));
        }
    }
}
