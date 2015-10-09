import  edu.princeton.cs.algs4.LinkedStack;

public class BruteCollinearPoints {
    
    LineSegment[] mLineSegments;
    Point[] mPoints;
    //Point[] mLessPoints;
    //Point[] mMaxPoints;
    
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        mPoints = points; 
        //mLessPoints = new Point[points.length];
        //mMaxPoints = new Point[points.length];
    }
    
    public int numberOfSegments()        // the number of line segments
    {
        return mLineSegments.length;
    }
    
    Point findLessPoint(Point point1, Point point2, Point point3, Point point4)
    {
        Point lesserPoint;
        if (point1.compareTo(point2) < 0)
            lesserPoint = point1;
        else
            lesserPoint = point2;
        
        if (point3.compareTo(lesserPoint) < 0)
            lesserPoint = point3;
        
        if (point4.compareTo(lesserPoint) < 0)
            lesserPoint = point4;
        
        return lesserPoint;
    }
    
    Point findMaxPoint(Point point1, Point point2, Point point3, Point point4)
    {
        Point maxPoint;
        if (point1.compareTo(point2) > 0)
            maxPoint = point1;
        else
            maxPoint = point2;
        
        if (point3.compareTo(maxPoint) > 0)
            maxPoint = point3;
        
        if (point4.compareTo(maxPoint) > 0)
            maxPoint = point4;
        
        return maxPoint;
    }
    
    private void calculateSegments()
    {
        int lineSegmentsIndex = 0;
        for (int pointIndex = 0; pointIndex < mPoints.length; ++pointIndex)
        {
            Point point1 = mPoints[pointIndex];
            for (int pointIndex2 = 0; pointIndex2 < mPoints.length; ++pointIndex2)
            {
                Point point2 = mPoints[pointIndex2];
                if (0 == point1.compareTo(point2))
                    continue;
                
                double slope12 = point1.slopeTo(point2);
                
                for (int pointIndex3 = pointIndex2; pointIndex3 < mPoints.length; ++pointIndex3)
                {
                    Point point3 = mPoints[pointIndex3];
                    
                    if (0 == point2.compareTo(point3))
                        continue;
                    
                    double slope23 = point2.slopeTo(point3);
                    
                    for (int pointIndex4 = pointIndex3; pointIndex4 < mPoints.length; ++pointIndex4)
                    {
                        Point point4 = mPoints[pointIndex4];
                        
                        if (0 == point3.compareTo(point4))
                            continue;
                        
                        double slope34 = point3.slopeTo(point4);
                        
                        if (slope12 == slope23 && slope23 == slope34)
                        {
                            Point lessPoint = findLessPoint(point1, point2, point3, point4);
                            Point maxPoint = findMaxPoint(point1, point2, point3, point4);
                            
                            // for (int lessIndex = 0; )
                            mLineSegments[lineSegmentsIndex++] = new LineSegment(lessPoint, maxPoint);                            
                        }
                    }
                }
            }
        }
    }
    
    public LineSegment[] segments()                // the line segments
    {
        calculateSegments();
        return mLineSegments;
    }
}