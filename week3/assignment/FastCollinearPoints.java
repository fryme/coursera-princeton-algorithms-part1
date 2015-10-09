import java.util.Arrays;
import edu.princeton.cs.algs4.*;

public class FastCollinearPoints {
    
    Point[] mPoints;
    LineSegment[] mLineSegments;
    
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        mPoints = points;
    }
    
    public int numberOfSegments()        // the number of line segments
    {
        return mLineSegments.length;
    }
    
    Point findMaxPoint(Point[] points)
    {
        Point maxPoint;
        if (points[0].compareTo(points[1]) > 0)
            maxPoint = points[0];
        else
            maxPoint = points[1];
        
        if (points[2].compareTo(maxPoint) > 0)
            maxPoint = points[2];
        
        if (points[3].compareTo(maxPoint) > 0)
            maxPoint = points[3];
        
        return maxPoint;
    }
    
    Point findLessPoint(Point[] points)
    {
        Point lesserPoint;
        if (points[0].compareTo(points[1]) < 0)
            lesserPoint = points[0];
        else
            lesserPoint = points[1];
        
        if (points[2].compareTo(lesserPoint) < 0)
            lesserPoint = points[2];
        
        if (points[3].compareTo(lesserPoint) < 0)
            lesserPoint = points[3];
        
        return lesserPoint;
    }
    
    public LineSegment[] segments()                // the line segments
    {
        int segmentIndex = 0;
                
        for (int pointIndex = 0; pointIndex < mPoints.length; pointIndex++)
        {
            Point currentPoint = mPoints[pointIndex];
            Point[] sortedPoints = new Point[mPoints.length - pointIndex - 1];
            
            
            for (int pointsToSortIndex = pointIndex + 1, absoluteIndex = 0; pointsToSortIndex < sortedPoints.length; pointsToSortIndex++)
                sortedPoints[absoluteIndex++] = mPoints[pointsToSortIndex];
            
            Arrays.sort(sortedPoints, mPoints[0].slopeOrder(mPoints[pointIndex]));
            
            double currentSlope = currentPoint.slopeTo(sortedPoints[0]);
            Point[] sameSlopePoints = new Point[4];
            int sameSlopeCurrentIndex = 0;
            
            for(int sortedIndex = 1; sortedIndex < sortedPoints.length; ++sortedIndex)
            {
                double nextSlope = currentPoint.slopeTo(sortedPoints[sortedIndex]);
                if (currentSlope != nextSlope)
                {
                    currentSlope = nextSlope;
                }
                else
                {
                    sameSlopePoints[sameSlopeCurrentIndex++] = sortedPoints[sortedIndex];
                }
                
                if (sameSlopePoints.length == 4)
                {
                    mLineSegments[segmentIndex++] = new LineSegment(findMaxPoint(sameSlopePoints), findLessPoint(sameSlopePoints));
                    sameSlopePoints = new Point[4];
                    sameSlopeCurrentIndex = 0;
                }
            }
        }
        
        return mLineSegments;
    }
    
    public static void main(String[] args) {
        // read the N points from a file
        In in = new In(args[0]);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
    }
    
}

