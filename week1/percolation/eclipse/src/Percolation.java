
public class Percolation {
    private edu.princeton.cs.algs4.WeightedQuickUnionUF wquUF;
    //WeightedQUUF_DBG wquUF;
    private int size;
    private boolean[] openedSites;
    //private boolean[] openedSites;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N)              
    {
        if (N <= 0)
            throw new java.lang.IllegalArgumentException("illegal args");
        
        size = N;
        this.wquUF = new edu.princeton.cs.algs4.WeightedQuickUnionUF(size * size + 2);
        //this.wquUF = new WeightedQUUF_DBG(size * size + 2);
        openedSites = new boolean[size * size];
        for (int i = 0; i < size * size; ++i)
            openedSites[i] = false;
        
        if (size == 1)
        {
            wquUF.union(0, 2);
            wquUF.union(0, 1);
        }
        else
        {
            for (int topPos = 0; topPos < size; ++topPos)
            {
                //System.out.println(topPos);
                wquUF.union(topPos, size*size);
            } 
            
            for (int bottomPos = size*size-size; bottomPos < size*size; ++bottomPos)
            {
                //System.out.println(bottomPos);
                wquUF.union(bottomPos, size*size +1);
            }
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int row, int column)         
    {
        checkBounds(row, column);
        if (!isOpen(row, column))
        {
            int absolutePos = (size * (row - 1) + (column - 1));
            openedSites[absolutePos] = true;
            lookForNeighboursConnections(row - 1, column - 1);
        }
    }

    private void lookForNeighboursConnections(int row, int column)
    {
        int absolutePos = size * row + column;

        // top
        if (row > 0)
        {
            int topPos = absolutePos - size;
            if (topPos >= 0 && openedSites[topPos])
            {
                //System.out.println("top: " + absolutePos + " " + topPos);
                wquUF.union(absolutePos, topPos);
            }
        }

        // left
        if (column > 0)
        {
            int leftPos = absolutePos - 1;
            if (openedSites[leftPos])
            {
                //System.out.println("left: " + absolutePos + " " + leftPos);
                wquUF.union(absolutePos, leftPos);
            }
        }

        // right
        if (column < (size-1))
        {
            int rightPos = absolutePos + 1;
            if (rightPos < (size * size - 1) 
                    && openedSites[rightPos])
            {
                //System.out.println("right: " + absolutePos + " " + rightPos);
                wquUF.union(absolutePos, rightPos);
            }
        }

        //bottom
        if (row < size)
        {
            int bottomPos = absolutePos + size;
            if (bottomPos < (size * size) 
                    && openedSites[bottomPos])
                //System.out.println("botton: " + absolutePos + " " + bottomPos);
                wquUF.union(absolutePos, bottomPos);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j)    
    {
        checkBounds(i, j);
        return openedSites[size * (i - 1) + (j - 1)];
    }
   
    private void checkBounds(int i, int j)
    {
        if (i <= 0 || i > size || j <= 0 || j > size) 
            throw new IndexOutOfBoundsException("out of bounds");
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j)    
    {
        checkBounds(i, j);

        int absPos = size * (i - 1) + (j - 1);
        if (openedSites[absPos])
        {
            if (i == 1)
                return true;
            //System.out.println("isFull " + absPos);
            return wquUF.connected(absPos, size * size);
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates()            
    {
        return wquUF.connected(size * size, size * size + 1);
    }
    
 // test client, described below
    public static void main(String[] args)
    {
        /*
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N-by-N percolation system
        
        Percolation perc = new Percolation(N);
        
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            System.out.println("Opening " + i + " " + j);
            perc.open(i, j);
        }
        
        System.out.println(perc.isFull(2, 6));
        */
        /*
    
        if (args.length != 2)
            throw new java.lang.IllegalArgumentException("illegal args");
     
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        
        if (N <= 0 || T <= 0)
            throw new java.lang.IllegalArgumentException("illegal args");
            
        PercolationStats stat = new PercolationStats(N, T);
        System.out.println("mean = " + stat.mean());
        System.out.println("stddev = " + stat.stddev());
        System.out.println("95% confidence interval = " 
        + stat.confidenceLo() + ", " + stat.confidenceHi());
        */
        //int n = 256;
        //System.out.println(17 * n*n + 128*n + 1024);
    }
}