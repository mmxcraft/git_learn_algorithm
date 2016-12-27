import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item [] data;
   private int cnt = 0;
 
   public RandomizedQueue()                 // construct an empty randomized queue
   {
      cnt  = 0;
   }
   public boolean isEmpty()                 // is the queue empty?
   {
       return (cnt == 0);
   }
   public int size()  // return the number of items on the queue
   {
       return cnt;
   }
   public void enqueue(Item item)           // add the item
   {
      if (item == null)
           throw new java.lang.NullPointerException();
      if (data == null) {
          data  = (Item []) new Object[32];
      }
      else {
      if (data.length == cnt) 
           resize(data.length*2);
      }
           
       data[cnt] = item;
       cnt++;
   }
   public Item dequeue() // remove and return a random item
   {
       if (cnt == 0)
           throw new java.util.NoSuchElementException();
   
       if (cnt > 1)
        StdRandom.shuffle(data, 0, cnt-1);
       
       Item delItem = data[cnt-1];
       cnt--;
       
       if (cnt == data.length/4 && cnt > 0) {
        resize(data.length/2); 
       }

       return delItem;
   }
   public Item sample()    // return (but do not remove) a random item
   {
       if (cnt == 0)
           throw new java.util.NoSuchElementException();
       int i = StdRandom.uniform(cnt);
       return data[i];
   }
   public Iterator<Item> iterator()  
   {
       return new RandomizedQueueIterator();
   }
   private class RandomizedQueueIterator implements Iterator<Item> 
   {
       private int pos = 0;
       public boolean hasNext() { return cnt > 0 && pos < cnt; }
       public void remove() { 
        throw new java.lang.UnsupportedOperationException(); 
       }
       public Item next() 
       {
        if (pos > cnt-1 || cnt <= 0)
           throw new java.util.NoSuchElementException();

        if (pos < cnt-1)
            StdRandom.shuffle(data, pos, cnt-1);
        
        return data[pos++];
       }
   }
   public static void main(String[] args)   // unit testing
   {
       RandomizedQueue<String> test = new RandomizedQueue<String>();
       test.enqueue("aa");
       test.enqueue("bb");
       test.enqueue("cc");
       test.enqueue("dd");
       test.enqueue("ee");
       
       for (int ii = 0; ii < 3; ii++) {
           for (String str : test)
            StdOut.printf("%s ", str);
           StdOut.print("\n");
       }
       
   }
   private void resize(int capacity)
   {
       Item[] newData = (Item[]) new Object[capacity];
       for (int i = 0; i < cnt; i++)
           newData[i] = data[i];
       data = newData;
   }
}