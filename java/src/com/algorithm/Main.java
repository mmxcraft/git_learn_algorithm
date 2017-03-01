
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class Main {

    public static void main(String[] args) {

        int [] allInts = In.readInts(args[0]);

        for(int ii = 0; ii < allInts.length; ii++) {
            StdOut.println(allInts[ii]);
        }

         /*while (!StdIn.isEmpty())
        {
            StdOut.println(StdIn.readInt());
        }*/
    }
}
