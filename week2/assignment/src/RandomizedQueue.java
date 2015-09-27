import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int[] randomOrder;
        private int current;
        
        RandomizedQueueIterator()
        {
            current = 0;
            randomOrder = new int[mNumberOfItems];
            
            for (int i = 0; i < mItemsArray.length; ++i)
            {
                if (mItemsArray[i] == null)
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
            Item item = mItemsArray[randomOrder[current]];
            ++current; 
            return item;
        }
    }
    
    private class LinkedList
    {
        private class Node
        {
            private Node next;
            private int value;
        }
        
        private Node mFirst;
        private Node mLast;
        
        boolean isEmpty()
        {
            return mFirst == null;
        }
        
        void push(int value)
        {
            if (mFirst == null)
            {
                mFirst = new Node();
                mFirst.value = value;
                mLast = mFirst;
            }
            else
            {
                Node newNode = new Node();
                newNode.value = value;
                mLast.next = newNode;
                mLast = newNode;
            }
        }
        
        int pop()
        {
            int valueToReturn = mFirst.value;
            
            if (mFirst == mLast)
            {
                mFirst = null;
                mLast = null;
            }
            else
            {
                mFirst = mFirst.next;
            }
            
            return valueToReturn;
        }
    }
    
    private Item[] mItemsArray;
    private int mNumberOfItems;
    private LinkedList mEmptyPositions;
    
    public RandomizedQueue()
    {
        mNumberOfItems = 0;
        mEmptyPositions = new LinkedList();
        mItemsArray = (Item[]) new Object[0];
    }
    
    public boolean isEmpty()
    {
        return mNumberOfItems == 0;
    }
    
    public int size()
    {
       return mNumberOfItems;
    }
    
    private void resize(int newCapacity)
    {
        Item[] newArray = (Item[]) new Object[newCapacity];
       
        if (newCapacity > mItemsArray.length)
        {
            for (int i = 0; i < mItemsArray.length; i++)
                newArray[i] = mItemsArray[i];
        }
        else
        {
            int n = 0;
            for (int i = 0; i < mItemsArray.length; i++)
            {
                if (mItemsArray[i] == null)
                    continue;
                
                newArray[n] = mItemsArray[i];
                n++;
            }
        }
        
        mItemsArray = newArray;
    }
    
    public void enqueue(Item item)
    {
        if (item == null) 
            throw new NullPointerException();
        
        // if we have empty 'hole' than push item there
        if (!mEmptyPositions.isEmpty())
        {
            mItemsArray[mEmptyPositions.pop()] = item;
            mNumberOfItems++;
            return;
        }
        
        // otherwise push to the end
        int arraySize = mItemsArray.length;
        if (arraySize == 0)
            resize(1);
        else if (arraySize == mNumberOfItems)
            resize(arraySize*2);
        
        mItemsArray[mNumberOfItems] = item;
        mNumberOfItems++;
    }
    
    public Item dequeue()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item itemToReturn = null;
        int randomPos = 0;
        while (itemToReturn == null)
        {
            randomPos = StdRandom.uniform(mItemsArray.length);
            itemToReturn = mItemsArray[randomPos];
        }
        
        mEmptyPositions.push(randomPos);
        mItemsArray[randomPos] = null;
        mNumberOfItems--;
        
        if (mNumberOfItems > 0 && mNumberOfItems == mItemsArray.length/4)
            resize(mItemsArray.length/2);
        
        return itemToReturn;
    }
        
    public Item sample()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        
        Item itemToReturn = null;
        int randomPos = 0;
        while (itemToReturn == null)
        {
            randomPos = StdRandom.uniform(mItemsArray.length);
            itemToReturn = mItemsArray[randomPos];
        }
        
        return itemToReturn;
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
        for (String s : rq)
        {
            System.out.println("> " + s);
        }
        System.out.println(rq.dequeue());
        
    }
}