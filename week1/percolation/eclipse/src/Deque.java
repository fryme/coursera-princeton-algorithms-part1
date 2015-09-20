import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node
    {
        private Node next;
        private Node prev;
        private Item item;
    }
    
    private Node mfirst;
    private Node mlast;
    private int msize;
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = mfirst;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    public Deque()
    {
        msize = 0;
    }
    
    public boolean isEmpty()
    {
        return mfirst == null;
    }
    
    public int size()
    {
        return msize;
    }
    
    public void addFirst(Item item)
    {
        if (item == null) throw new NullPointerException();

        Node newFirst = new Node();
        newFirst.item = item;
        
        if (!isEmpty())
        {
            newFirst.next = mfirst;
            mfirst.prev = newFirst;
            mfirst = newFirst;
        }
        else
        {
            mfirst = newFirst;
        }
        
        if (mlast == null)
            mlast = mfirst;
             
        msize++;
    }
    
    public void addLast(Item item)
    {
        if (item == null) throw new NullPointerException();
        
        Node newLast = new Node();
        newLast.item = item;
        
        if (!isEmpty())
        {
            mlast.next = newLast;
            newLast.prev = mlast;
            mlast = newLast;
        }
        else
        {
            mlast = newLast;
        }

        if (mfirst == null)
            mfirst = mlast;
        
        msize++;
    }
    
    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item item = mfirst.item;
        Node newFirst = mfirst.next;
        
        if (msize == 1)
        {
            if (mlast.next != null)
                mlast.next = null;
            if (mlast.prev != null)
                mlast.prev = null;
            if (mfirst.next != null)
                mfirst.next = null;
            if (mfirst.prev != null)
                mfirst.prev = null;
            mlast = null;
            mfirst = null;
        }
        else
        {
            mfirst.next = null;
            mfirst.prev = null;
            mfirst = null;
            mfirst = newFirst;
            if (mfirst.next != null)
                mfirst.next.prev = mfirst; 
        }
        
        msize--;
        return item;  
    }

    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        if (mlast == null)
            return removeFirst();
        
        Node oldLast = mlast;
        Item item = oldLast.item;
        if (msize == 1)
        {
            if (mlast.next != null)
                mlast.next = null;
            if (mlast.prev != null)
                mlast.prev = null;
            if (mfirst.next != null)
                mfirst.next = null;
            if (mfirst.prev != null)
                mfirst.prev = null;
            mlast = null;
            mfirst = null;
        }
        else
        {
            mlast = oldLast.prev;
            mlast.next = null;
            oldLast.prev = null;
        }

        msize--;
        return item;
    }
    
    @Override
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    
    public static void main(String[] args)
    {
        Deque<String> d = new Deque<String>();
        d.addFirst("1");
        d.addFirst("2");
        d.addFirst("3");
        d.addLast("7");
        d.removeFirst();
        d.removeFirst();
        
        for (String s : d)
        {
            System.out.println(s);
        }
    }
}