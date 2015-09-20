
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
   
    private double mmean, mdev, mconfidenceLo, mconfidenceHi;
    
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T)   
    {
        double[] means = new double[T];
        for (int count = 0; count < T; ++count)
        {
            Percolation percolation = new Percolation(N);
            double tryCounter = 0;
            int randI, randJ;
            
            while (!percolation.percolates())
            {
                randI = StdRandom.uniform(N);
                randI++;
                randJ = StdRandom.uniform(N);
                randJ++;
                
                tryCounter++;
                
                //System.out.println("rand " + randI + " " + randJ);
                
                percolation.open(randI, randJ);
                
            }
            //System.out.println("tryCounter " + tryCounter);
            means[count] = ((tryCounter) / ((double) (N*N)));
            //System.out.println("means[count] " + means[count]);
        }
        
        double meanSum = 0;
        for (int mean = 0; mean < means.length; ++mean)
        {
            meanSum += means[mean];
        }
        
        mmean = meanSum / T;
        double thSum = 0;
        for (int mean = 0; mean < means.length; ++mean)
        {
            double s = means[mean] - mmean;
            s *= s;
            thSum += s;
        }
        
        mdev = Math.sqrt((double) thSum / (T - 1));
       
        mconfidenceLo = mmean - ((1.96 * mdev) / Math.sqrt(T));
        mconfidenceHi = mmean + ((1.96 * mdev) / Math.sqrt(T));
    }
   
 // sample mean of percolation threshold
    public double mean()
    {
        return mmean;
    }
    
 // sample standard deviation of percolation threshold
    public double stddev()
    {
        return mdev;
    }

 // returns lower bound of the 95% confidence interval
    public double confidenceLo()
    {
        return mconfidenceLo;
    }
   
 // returns upper bound of the 95% confidence interval
    public double confidenceHi()
    {
        return mconfidenceHi;
    }
    
 // test client, described below
    public static void main(String[] args)
    {
        if (args.length != 2)
            throw new java.lang.IllegalArgumentException("illegal args");
     
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
        if (N <= 0 || T <= 0)
            throw new java.lang.IllegalArgumentException("illegal args");
         
        //long startTime = System.nanoTime();
        
                
        PercolationStats stat = new PercolationStats(N, T);
        
        //long endTime = System.nanoTime();

        //long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        //System.out.println((double)duration / 1000000000.0);
        System.out.println("mean = " + stat.mean());
        System.out.println("stddev = " + stat.stddev());
        System.out.println("95% confidence interval = " 
        + stat.confidenceLo() + ", " + stat.confidenceHi());
        
    }
}