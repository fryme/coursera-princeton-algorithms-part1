import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node
    {
        Node next;
        Node prev;
        Item value;
    }
    
    int m_size;
    Node m_first;
    Node m_last;
    
    public Deque()                           // construct an empty deque
    {
        m_size = 0;
    }
    
    public boolean isEmpty()                 // is the deque empty?
    {
        return m_size == 0;
    }
    
    public int size()                        // return the number of items on the deque
    {
        return m_size;
    }
    
    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) 
            throw new NullPointerException();
        
        if (m_first == null)
        {
            m_first = new Node();
            m_first.value = item;
            m_last = m_first;
        }
        else
        {
            Node oldFirst = m_first;
            m_first = new Node();
            m_first.value = item;
            m_first.next = oldFirst;
            oldFirst.prev = m_first;
        }
        
        m_size ++;
    }
    
    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) 
            throw new NullPointerException();
        
        if (m_last == null)
        {
            m_last = new Node();
            m_last.value = item;
            m_first = m_last;
        }
        else
        {
            Node oldLast = m_last;
            m_last = new Node();
            m_last.value = item;
            m_last.prev = oldLast;
            oldLast.next = m_last;
        }
        
        m_size ++;
    }
    
    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item itemToReturn;
        
        if (m_first == m_last)
        {
            itemToReturn = m_first.value;
            m_first = m_last = null;
        }
        else
        {
            itemToReturn = m_first.value;
            Node oldFirst = m_first;
            m_first.next.prev = null;
            m_first = oldFirst.next;
        }
        
        m_size --;
        
        return itemToReturn;
    }
    
    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item itemToReturn;
        
        if (m_last == m_first)
        {
            itemToReturn = m_last.value;
            m_last = m_first = null;
        }
        else
        {
            itemToReturn = m_last.value;
            m_last.prev.next = null;
            m_last = m_last.prev;
        }
        
        m_size --;
        
        return itemToReturn;
    }
    
    private class DequeIterator implements Iterator<Item>
    {
        private Node m_current;

        DequeIterator (Node first) 
        {
            m_current = first;
        }
        
        public boolean hasNext()  
        { 
            return m_current != null;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next() 
        {
            if (!hasNext()) 
                throw new NoSuchElementException();
            Item item = m_current.value;
            m_current = m_current.next; 
            return item;
        }
    }
    
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator(m_first);
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