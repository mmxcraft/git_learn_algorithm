import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Main {

    public static void main(String[] args) {

        String name = "https://finance.yahoo.com/quote/orcl";
        In in = new In(name);
        String text = in.readAll();
        StdOut.print(text);
         /*while (!StdIn.isEmpty())
        {
            StdOut.println(StdIn.readInt());
        }*/
    }
}
