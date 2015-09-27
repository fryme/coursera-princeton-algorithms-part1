import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Subset 
{
    public static void main(String[] args)
    {
        if (args.length < 1)
            throw new java.lang.IllegalArgumentException("illegal args");

        
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        
        String str = null;
        while (!StdIn.isEmpty())
        {
            str = StdIn.readString();
            rq.enqueue(str);
        }
        
        int K = Integer.parseInt(args[0]);
        
        if (K <= 0 || rq.size() == 0 || K > rq.size())
            throw new java.lang.IllegalArgumentException("illegal args");
        
        
        for (int i = 0; i < K; ++i)
        {
            System.out.println(rq.dequeue());
        }
    }
}
