public class BruteCollinearPoints {
    
    LineSegment[] mLineSegments;
    
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        mLineSegments = new LineSegment[points.length - 1];
    }
    
    public int numberOfSegments()        // the number of line segments
    {
        return mLineSegments.length;
    }
    
    public LineSegment[] segments()                // the line segments
    {
        
        return mLineSegments;
    }
}