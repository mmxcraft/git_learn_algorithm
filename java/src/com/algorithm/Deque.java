import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    
   private Node first, last;
   private int cnt;
 
   public Deque()                           // construct an empty deque
   {
       first = null;
       last = null;
       cnt = 0;
   }
   public boolean isEmpty()                 // is the deque empty?
   {
       return first == null;
   }
   public int size()     // return the number of items on the deque
   {
       return cnt;
   }
   public void addFirst(Item item)          // add the item to the front
   {
       if (item == null)
           throw new java.lang.NullPointerException();
       Node newItem = new Node();
       newItem.item = item;
       newItem.next = first;
       newItem.prev = null;
       if (first != null) {
        first.prev = newItem;
        first = newItem;
       }
       else {
        first = newItem;   
        first.next = null;
        first.prev = null;
        }
       
       if (last == null) last = first;
       cnt++;
   }
   public void addLast(Item item)           // add the item to the end
   {
      if (item == null)
          throw new java.lang.NullPointerException();
       Node newItem = new Node();
       newItem.item = item;
       newItem.prev = last;
       newItem.next = null;
       if (last != null) {
        last.next = newItem;
        last = newItem;        
       }
       else {
        last = newItem;
        last.prev = null;
        last.next = null;
       }
       if (first == null) first = newItem;
       cnt++;
   }
   public Item removeFirst()  // remove and return the item from the front
   {
       if (first == null)
           throw new java.util.NoSuchElementException();
       Item curItem = first.item;
       if (last == first) {
           last = null;
           first = null;
       }
       else {
        first = first.next;
        first.prev = null;
       }
        
       cnt--;
       
       return curItem;
   }
   public Item removeLast() // remove and return the item from the end
   {
       if (last == null)
           throw new java.util.NoSuchElementException();
       Node curItem = last;
       if (last == first) {
           last  = null;
           first = null;
       }
       else {
           last = last.prev;
           last.next = null;
       }
       cnt--;
       return curItem.item;
   }
   public Iterator<Item> iterator() 
   {
       return new DequeIterator();
   }
   
    private class Node
   {
       private Item item;
       private Node next;
       private Node prev;
   }
   


   private class DequeIterator implements Iterator<Item> 
   {
       private Node current = first;
       public boolean hasNext() { return current != null; }
       public void remove() { 
        throw new java.lang.UnsupportedOperationException();
       }
       public Item next() 
       {
           if (current == null)
               throw new java.util.NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
   }
   
   
   public static void main(String[] args)   // unit testing
   {
       int ii;
       Deque<Integer> test = new Deque<Integer>();
       test.addFirst(10);
       test.addFirst(20);
       test.addLast(30);
       test.addLast(40);
       
       StdOut.println("test");
       StdOut.printf("cnt=%d\n", test.size()); 
       for (int tt:test)
        {   StdOut.println(tt); }
       
       // Deque<int> test_dq = new Deque<int>();
       // test_dq.addFirst(10);
   }

}