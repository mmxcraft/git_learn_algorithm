import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
   public static void main(String[] args)
   {
       RandomizedQueue<String> myQueue = new RandomizedQueue<String>();
       int cnt = Integer.parseInt(args[0]);
       while (!StdIn.isEmpty()) {
           String s = StdIn.readString();
           myQueue.enqueue(s);
       }
       
       for (int i = 0; i < cnt; i++) {
           String s = myQueue.dequeue();
           StdOut.println(s);
       }
       
   }
}