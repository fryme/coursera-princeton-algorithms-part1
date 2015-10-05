import  edu.princeton.cs.algs4.LinkedStack;

public class BruteCollinearPoints {
    
    LineSegment[] mLineSegments;
    
    private class Slope
    {
        double mSlopeValue;
        Point mPoint1;
        Point mPoint2;
    };
    
    Slope[] mSlopes; // array contains slopes between all points
    Point[] mPoints;
    
    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        mSlopes = new Slope[points.length * 2];
        mPoints = points; 
    }
    
    public int numberOfSegments()        // the number of line segments
    {
        return mLineSegments.length;
    }
    
    private void calculateSegments()
    {
        int firstIndex = 0;//, secondIndex = ;
        int slopeIndex = 0;
        while (firstIndex != mPoints.length)
        {
            for (int pointIndex = firstIndex; pointIndex < mPoints.length - 1; ++pointIndex)
            {
                Point firstPoint = mPoints[firstIndex];
                Point secondPoint = mPoints[pointIndex];
                mSlopes[slopeIndex].mSlopeValue = firstPoint.slopeTo(secondPoint);
                mSlopes[slopeIndex].mPoint1 = firstPoint;
                mSlopes[slopeIndex].mPoint2 = secondPoint;
                slopeIndex++;
            }
            firstIndex++;
        }
    }
    
    private void findSegmentsInOneLine()
    {
        for (int firstSlopeIndex = 0; firstSlopeIndex < mSlopes.length; ++firstSlopeIndex)
        {
            LinkedStack<Slope> slopesOnOneLine = new LinkedStack<Slope>();
            for (int secondSlopeIndex = firstSlopeIndex + 1; secondSlopeIndex < mSlopes.length; ++secondSlopeIndex)
            {
                Slope firstSlope = mSlopes[firstSlopeIndex];
                Slope secondSlope = mSlopes[secondSlopeIndex];
                if (firstSlope.mSlopeValue == secondSlope.mSlopeValue)
                {
                    if (firstSlope.mSlopeValue == 0)
                    {
                        if (firstSlope.mPoint1.compareTo(secondSlope.mPoint2) == 0)
                            continue; // points are equals
                        
                        // How to determine points on different lines?
                        if (slopesOnOneLine.size() == 0)
                        {
                            slopesOnOneLine.push(firstSlope);
                            slopesOnOneLine.push(secondSlope);
                        }
                        else
                        {
                            slopesOnOneLine.push(secondSlope);
                        }
                        // if (firstSlope.mPoint1.compareTo(secondSlope.mPoint2) == 1)
                            // on one line;
                    }
                    else if (firstSlope.mSlopeValue == secondSlope.mSlopeValue)
                    {
                        if (slopesOnOneLine.size() == 0)
                        {
                            slopesOnOneLine.push(firstSlope);
                            slopesOnOneLine.push(secondSlope);
                        }
                        else
                        {
                            slopesOnOneLine.push(secondSlope);
                        }
                    }
                }
                
                if (slopesOnOneLine.size() == 4)
                {
                    for (int slopeIndex = 0; slopeIndex < slopesOnOneLine.size(); ++slopeIndex)
                    {
                        
                    }
                }
            }
        }
    }
    
    public LineSegment[] segments()                // the line segments
    {
        calculateSegments();
        findSegmentsInOneLine();
        return mLineSegments;
    }
}