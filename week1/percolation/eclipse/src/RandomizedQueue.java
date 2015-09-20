import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int[] randomOrder;
        private int current;
        
        RandomizedQueueIterator()
        {
            current = 0;
            randomOrder = new int[nElements];
            
            for (int i = 0; i < array.length; ++i)
            {
                if (array[i] == null)
                    continue;
                
                randomOrder[i] = i;
            }
            StdRandom.shuffle(randomOrder);
        }
        
        public boolean hasNext()  
        { 
            return current < randomOrder.length;
        }
        
        public void remove()
        { 
            throw new UnsupportedOperationException();  
        }

        public Item next() 
        {
            if (!hasNext()) 
                throw new NoSuchElementException();
            Item item = array[randomOrder[current]];
            ++current; 
            return item;
        }
    }
    
    private Item[] array;
    private int nElements;
    private String freePositions;
    int pos;
    
    public RandomizedQueue()
    {
        nElements = 0;
        pos = 0;
        array = (Item[]) new Object[1];
        freePositions = new String();
    }
    
    public boolean isEmpty()
    {
        return nElements == 0;
    }
    
    public int size()
    {
        return nElements;
    }
    
    private void resize(int newCapacity)
    {
        Item[] copy = (Item[]) new Object[newCapacity];
        if (newCapacity > array.length)
        {
            for (int i = 0; i < array.length; i++)
                copy[i] = array[i];
        }
        else
        {
            int n = 0;
            for (int i = 0; i < array.length; i++)
            {
                if (array[i] == null)
                    continue;
                
                copy[n] = array[i];
                n++;
            }
        }
        array = copy;
    }
    
    public void enqueue(Item item)
    {
        int arraySize = array.length;
        nElements += 1;
        
        if (arraySize == nElements)
            resize(arraySize*2);
        
        if (freePositions.length() > 0)
        {
            String[] pos = freePositions.split(",");
            if (pos.length > 0)
                array[Integer.parseInt(pos[0])] = item;
        }
        else
        {
            array[pos] = item;
            pos++;
        }
    }
    
    public Item dequeue()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item item = null;
        int pos = 0;
        while (item == null)
        {
            pos = getRandomPos();
            item = array[pos];
        }
        
        --nElements;
        array[pos] = null;
        if (nElements > 0 && nElements == array.length/4)
            resize(array.length / 2);
        
        freePositions += pos;
        freePositions += ",";
        return item;
    }
    
    private int getRandomPos()
    {
        return StdRandom.uniform(array.length);
    }
        
    public Item sample()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item item = null;
        int pos = 0;
        while (item == null)
        {
            pos = getRandomPos();
            item = array[pos];
        }
        
        return item;
    }
    
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }
    
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("1");
        rq.enqueue("4");
        rq.enqueue("3");
        rq.enqueue("9");
        rq.enqueue("1");
        rq.enqueue("4");
        rq.enqueue("3");
        rq.enqueue("9");
        rq.enqueue("1");
        rq.enqueue("4");
        rq.enqueue("3");
        rq.enqueue("9");
        rq.enqueue("1");
        rq.enqueue("4");
        rq.enqueue("3");
        rq.enqueue("9");
        
        for (String s : rq)
        {
            System.out.println("> " + s);
        }
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("9");
        rq.enqueue("1");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("9");
        rq.enqueue("1");
        
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        rq.enqueue("3");
        rq.enqueue("9");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("3");
        rq.enqueue("9");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        rq.enqueue("4");
        rq.enqueue("4");
        rq.enqueue("4");
        rq.enqueue("4");
        rq.enqueue("4");
        rq.enqueue("4");
        
        
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        rq.enqueue("4");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        
    }
}