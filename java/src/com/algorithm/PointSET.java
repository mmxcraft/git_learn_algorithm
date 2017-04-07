/**
 * Created by mofma on 2017/3/21.
 */

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class PointSET {
    private SET<Point2D> pointSet;
    private ArrayList<Point2D> pointInRange;

    public         PointSET()    // construct an empty set of point
    {
        pointSet = new SET<Point2D>();
        pointInRange = new ArrayList<Point2D>();
    }
    public boolean isEmpty()    // is the set empty?
    {
        return pointSet.isEmpty();
    }
    public  int size()   // number of points in the set
    {
        return pointSet.size();
    }
    public void insert(Point2D p)
    {
        if (p == null)
            throw new java.lang.NullPointerException();
        pointSet.add(p);
    }
    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null)
            throw new java.lang.NullPointerException();

        return pointSet.contains(p);
    }
    public              void draw()  // draw all points to standard draw
    {
        for (Point2D each: pointSet)
             each.draw();
    }
    public Iterable<Point2D> range(RectHV rect)
    {
        if (rect == null)
            throw new java.lang.NullPointerException();

        pointInRange.clear();

        for (Point2D each: pointSet) {
            if (rect.contains(each))
                pointInRange.add(each);
        }

        return pointInRange;
    }
    public Point2D nearest(Point2D p)
    {
        if (p == null)
            throw new java.lang.NullPointerException();

        if (isEmpty())
            return null;

        double minDis = -1.0;
        Point2D minPoint = new Point2D(0, 0);

        for (Point2D each: pointSet) {
            double curDis = each.distanceTo(p);
            if (minDis < 0.0 || curDis < minDis) {
                minDis = curDis;
                minPoint = each;
            }
         }

        return minPoint;
    }
    public static void main(String[] args) // unit testing of the methods (optional)
    {
        PointSET pSet = new PointSET();
        pSet.insert(new Point2D(30, 40));
        pSet.insert(new Point2D(30, 100));
        pSet.insert(new Point2D(30, 200));
        pSet.insert(new Point2D(300, 400));
        pSet.insert(new Point2D(500, 600));

        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 5000);
        StdDraw.setPenRadius(0.01);

        pSet.draw();

    }
}
