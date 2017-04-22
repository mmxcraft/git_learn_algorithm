
/**
 * Created by mofma on 2017/3/21.
 */
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class KdTree {
    private Node root;
    private int size;
    private ArrayList<Point2D> pointInRange;
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private boolean xFlag;
    }

    public KdTree()   // construct an empty set of point
    {
        root = null;
        pointInRange = new ArrayList<Point2D>();
        size = 0;
    }
    private int size(Node node)
    {
        return size;
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return size == 0;
    }
    public int size()                         // number of points in the set
    {
        return size;
    }
    public void insert(Point2D p)
    {
        if (p == null)
            throw new java.lang.NullPointerException();

        RectHV rect = new RectHV(0, 0, 1, 1);

        Node n = new Node();
        n.p = p;
        n.lb = null;
        n.rt = null;

        if (root != null) {
            rect = root.rect;
        }
        root = putNode(root, n, rect, true);
    }

    private Node putNode(Node node, Node newNode, RectHV rect, boolean xFlag) {
        if (node == null) {
            newNode.rect = rect;
            newNode.xFlag = xFlag;
            size++;
            return newNode;
        }

        double xcmp = node.p.x() - newNode.p.x();
        double ycmp = node.p.y() - newNode.p.y();

        if (xcmp == 0 && ycmp == 0)
            node.p = newNode.p;
        else {
            if (xFlag) { //vertical
                if (xcmp > 0) { //left
                    RectHV nRect = new RectHV(rect.xmin(),rect.ymin(),node.p.x(),rect.ymax());
                    node.lb = putNode(node.lb, newNode,nRect, false);
                } else { //right/equal
                    RectHV nRect = new RectHV(node.p.x(),rect.ymin(),rect.xmax(),rect.ymax());
                    node.rt = putNode(node.rt, newNode, nRect, false);
                }
            } else {
                if (ycmp > 0) { // below
                    RectHV nRect = new RectHV(rect.xmin(), rect.ymin(),rect.xmax(), node.p.y());
                    node.lb = putNode(node.lb, newNode, nRect, true);
                } else { //top equal
                    RectHV nRect = new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());
                    node.rt = putNode(node.rt, newNode, nRect, true);
                }
            }
        }
        return node;
    }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null)
            throw new java.lang.NullPointerException();

        Node n = root;
        while (n != null) {
            double xcmp = n.p.x() - p.x();
            double ycmp = n.p.y() - p.y();

            if (xcmp == 0 && ycmp == 0)
                return true;

            if (n.xFlag) {
                if (xcmp > 0)
                    n = n.lb;
                else
                    n = n.rt;
            } else {
                if (ycmp > 0)
                    n = n.lb;
                else
                    n = n.rt;
            }

        }
        return false;
    }


    private void nodeDraw(Node node)
    {
        if (node == null)
            return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();
        if (node.xFlag) {
            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.RED);
            Point2D p1 = new Point2D(node.p.x(), node.rect.ymin());
            p1.drawTo(new Point2D(node.p.x(), node.rect.ymax()));

        } else {
            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.BLUE);
            Point2D p1 = new Point2D(node.rect.xmin(), node.p.y());
            p1.drawTo(new Point2D(node.rect.xmax(), node.p.y()));
        }

        nodeDraw(node.lb);
        nodeDraw(node.rt);
    }

    public void draw()   // draw all points to standard draw
    {
        nodeDraw(root);
    }

    private void getRange(Node node, RectHV rect)
    {
        if (node == null)
            return;
        if (node.rect.intersects(rect)) {
            getRange(node.lb, rect);
            if (rect.contains(node.p))
                pointInRange.add(node.p);
            getRange(node.rt, rect);
        } else {
            getRange(node.rt, rect);
            if (rect.contains(node.p))
                pointInRange.add(node.p);
            // getRange(node.lb, rect);
        }
    }
    public Iterable<Point2D> range(RectHV rect)
    {
        pointInRange.clear();
        getRange(root, rect);
        return pointInRange;
    }

    private Point2D getNearest(Node node, Point2D p, Point2D minDisP)
    {
        if (node == null)
            return minDisP;

        RectHV curRect;
        if (node.xFlag)
            curRect = new RectHV(node.rect.xmin(), node.rect.ymin(),node.p.x(), node.rect.ymax());
        else
            curRect = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());

        double minDis = p.distanceTo(minDisP);
        double curDis = p.distanceTo(node.p);

        if (curDis < minDis)
            minDisP = node.p;
        if (curRect.contains(p)) {
            minDisP = getNearest(node.lb, p, minDisP);
            minDis = p.distanceTo(minDisP);
            if (node.xFlag && minDis < curRect.xmax()-p.x())
                return minDisP;
            else if (!node.xFlag && minDis < curRect.ymax()-p.y())
                return minDisP;
            minDisP = getNearest(node.rt, p, minDisP);
        } else {
            minDisP = getNearest(node.rt, p, minDisP);
            minDis = p.distanceTo(minDisP);
            if (node.xFlag && minDis < (p.x()-curRect.xmax()))
                return minDisP;
            else if (!node.xFlag && minDis < (p.y()-curRect.ymax()))
                return minDisP;
            minDisP = getNearest(node.lb, p, minDisP);
        }

        return minDisP;
    }

    public Point2D nearest(Point2D p)
    {
        if (p == null)
            throw new java.lang.NullPointerException();

        if (isEmpty())
            return null;

        return getNearest(root, p, root.p);

    }
    public static void main(String[] args)
    {
        KdTree treeSet = new KdTree();
        treeSet.insert(new Point2D(0.7, 0.2));
        treeSet.insert(new Point2D(0.4, 0.2));
        treeSet.insert(new Point2D(0.8, 0.5));

        treeSet.insert(new Point2D(0.5, 0.4));
        treeSet.insert(new Point2D(0.2, 0.8));
        treeSet.insert(new Point2D(0.4, 0.5));
        treeSet.insert(new Point2D(0.6, 0.6));

        treeSet.insert(new Point2D(0.2, 0.4));
        treeSet.insert(new Point2D(0.2, 0.5));


        treeSet.insert(new Point2D(0.2, 0.6));
        treeSet.insert(new Point2D(0.2, 0.3));
        treeSet.insert(new Point2D(0.2, 0.5));

         treeSet.insert(new Point2D(0.4, 0.3));
         treeSet.insert(new Point2D(0.4,0.7));
        treeSet.insert(new Point2D(0.4,0.2));
        treeSet.insert(new Point2D(0.8,0.6));
        treeSet.insert(new Point2D(0.9,0.3));

        StdDraw.setXscale(0, 1.0);
        StdDraw.setYscale(0, 1.0);
        StdDraw.setPenRadius(0.01);

        treeSet.draw();

    }
}

