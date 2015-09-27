import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node
    {
        private Node next;
        private Node prev;
        private Item value;
    }
    
    private int mSize;
    private Node mFirst;
    private Node mLast;
    
    public Deque()                           // construct an empty deque
    {
        mSize = 0;
    }
    
    public boolean isEmpty()                 // is the deque empty?
    {
        return mSize == 0;
    }
    
    public int size()                        // return the number of items on the deque
    {
        return mSize;
    }
    
    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) 
            throw new NullPointerException();
        
        if (mFirst == null)
        {
            mFirst = new Node();
            mFirst.value = item;
            mLast = mFirst;
        }
        else
        {
            Node oldFirst = mFirst;
            mFirst = new Node();
            mFirst.value = item;
            mFirst.next = oldFirst;
            oldFirst.prev = mFirst;
        }
        
        mSize++;
    }
    
    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) 
            throw new NullPointerException();
        
        if (mLast == null)
        {
            mLast = new Node();
            mLast.value = item;
            mFirst = mLast;
        }
        else
        {
            Node oldLast = mLast;
            mLast = new Node();
            mLast.value = item;
            mLast.prev = oldLast;
            oldLast.next = mLast;
        }
        
        mSize++;
    }
    
    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item itemToReturn;
        
        if (mFirst == mLast)
        {
            itemToReturn = mFirst.value;
            mFirst = null;
            mLast = null;
        }
        else
        {
            itemToReturn = mFirst.value;
            Node oldFirst = mFirst;
            mFirst.next.prev = null;
            mFirst = oldFirst.next;
        }
        
        mSize--;
        
        return itemToReturn;
    }
    
    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item itemToReturn;
        
        if (mLast == mFirst)
        {
            itemToReturn = mLast.value;
            mLast = null;
            mFirst = null;
        }
        else
        {
            itemToReturn = mLast.value;
            mLast.prev.next = null;
            mLast = mLast.prev;
        }
        
        mSize--;
        
        return itemToReturn;
    }
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node mCurrent;

        DequeIterator(Node first)
        {
            mCurrent = first;
        }
        
        public boolean hasNext()  
        { 
            return mCurrent != null;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next() 
        {
            if (!hasNext()) 
                throw new NoSuchElementException();
            Item item = mCurrent.value;
            mCurrent = mCurrent.next; 
            return item;
        }
    }
    
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator(mFirst);
    }
    
    public static void main(String[] args)   // unit testing
    {
        Deque<String> d = new Deque<String>();
        d.addFirst("1");
        d.addFirst("2");
        d.addFirst("3");
        
        
        for (String s : d)
        {
            System.out.println(s);
        }
        
        d.addLast("7");
        System.out.println("<<");

        for (String s : d)
        {
            System.out.println(s);
        }
        System.out.println("<<");
        d.removeFirst();
        d.removeFirst();
        
        for (String s : d)
        {
            System.out.println(s);
        }
        
        d.removeLast();
        System.out.println("<<");
        for (String s : d)
        {
            System.out.println(s);
        }
        d.removeLast();
        d.removeLast();
    }
}