
/**
 * Created by mofma on 2017/3/21.
 */
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class KdTree {
    private Node root;
    private ArrayList<Point2D> pointInRange;
    private class Node {
        private Point2D p;      // the point
        private RectHV rect;
        private Node pNode;
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private boolean xFlag;
        private int size;

        public Node(Point2D p1, RectHV nRect, Node ppNode, boolean flag, int sZ)
        {
            p = p1;
            lb = null;
            rt = null;
            xFlag = flag;
            size = sZ;
            pNode = ppNode;

            if (nRect == null)
                rect = new RectHV(0.0, 0.0, p1.x(), 1.0);
            else
                rect = nRect;

            if (rect.contains(p1)) {
                if (xFlag)
                    rect = new RectHV(rect.xmin(), rect.ymin(), p1.x(), rect.ymax());
                else
                    rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), p1.y());
            } else {
                double pXmax = 1.0;
                double pYmax = 1.0;
                if (pNode.pNode != null) {
                    pXmax = pNode.pNode.rect.xmax();
                    pYmax = pNode.pNode.rect.ymax();
                }

                if (xFlag)
                    rect = new RectHV(rect.xmin(), rect.ymax(), p1.x(), pYmax);
                else
                    rect = new RectHV(rect.xmax(), rect.ymin(), pXmax, p1.y());
            }
        }

        public int compareTo(Object that) {
            double x = ((Node) that).p.x();
            double y = ((Node) that).p.y();
            if (xFlag)
                return (p.x() < x) ? -1 : (p.x() == x) ? 0 : 1;
            else
                return (p.y() < y) ? -1 : (p.y() == y) ? 0 : 1;
        }
    }

    public KdTree()   // construct an empty set of point
    {
        root = null;
        pointInRange = new ArrayList<Point2D>();
    }

    private int comparePoint(Point2D p1, Point2D p2, boolean xFlag)
    {
        if (xFlag)
            return (p1.x() < p2.x()) ? -1 : (p1.x() == p2.x()) ? 0 : 1;
        else
            return (p1.y() < p2.y()) ? -1 : (p1.y() == p2.y()) ? 0 : 1;
    }


    private int size(Node node)
    {
        if (node == null)
            return 0;
        else return node.size;

    }

    public boolean isEmpty()                      // is the set empty?
    {
        return size(root) == 0;
    }
    public int size()                         // number of points in the set
    {
        return size(root);
    }
    public void insert(Point2D p)
    {
        if (p == null)
            throw new java.lang.NullPointerException();
        RectHV rect = null;
        if (root != null) {
            rect = root.rect;
        }
        root = putNode(root, p, rect, null, true);
    }

    private Node putNode(Node node, Point2D newP, RectHV rect, Node pNode, boolean xFlag) {
        if (node == null) {
            node = new Node(newP, rect, pNode, xFlag, 1);
            return node;
        }

        int cmp = comparePoint(node.p, newP, xFlag);
        xFlag = !node.xFlag;
        if (cmp > 0) {
            if (node.lb != null) {
                rect = node.lb.rect;
            }
            Node newNode = putNode(node.lb, newP, rect, node, xFlag);
            if (node.lb != newNode) {
                node.lb = newNode;
            } else {
                if (node.size == node.lb.size)
                    node.size++;
                return node;
            }
        }
        else if (cmp < 0) {
            if (node.rt != null) {
                rect = node.rt.rect;
            }
            Node newNode = putNode(node.rt, newP, rect, node, xFlag);
            if (newNode != node.rt) {
                node.rt = newNode;
            } else {
                if (node.size == node.rt.size)
                    node.size++;
                return node;
            }
        } else {
                if (node.p.equals(newP))
                    return node;
                if (node.lb != null) {
                    rect = node.lb.rect;
                    Node newNode = putNode(node.lb, newP, rect, node, xFlag);
                }
                else if (node.rt != null) {
                    rect = node.rt.rect;
                    Node newNode = putNode(node.rt, newP, rect, node, xFlag);
                }else {
                    Node newNode = putNode(null,newP, rect, node, xFlag);
                }
        }
        node.size++;
        return node;
    }

    private Node get(Node node, Point2D newP) {
        if (node == null)
            return null;
        RectHV rect  = new RectHV(0.0, 0.0, 1.0, 1.0);
        Node newNode = new Node(newP, rect, null, node.xFlag, 1);
        int cmp = node.compareTo(newNode);
        if (cmp > 0) get(node.lb, newP);
        else if (cmp < 0) get(node.rt, newP);
        return node;
     }

    public boolean contains(Point2D p) // does the set contain point p?
    {
        if (p == null)
            throw new java.lang.NullPointerException();

        return (get(root, p) != null);
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
            Point2D p1 = new Point2D(node.rect.xmax(), node.rect.ymin());
            p1.drawTo(new Point2D(node.rect.xmax(), node.rect.ymax()));

        } else {
            StdDraw.setPenRadius();
            StdDraw.setPenColor(StdDraw.BLUE);
            Point2D p1 = new Point2D(node.rect.xmin(), node.rect.ymax());
            p1.drawTo(new Point2D(node.rect.xmax(), node.rect.ymax()));
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
        double minDis = p.distanceTo(minDisP);
        double curDis = p.distanceTo(node.p);
        if (curDis < minDis)
            minDisP = node.p;
        if (node.rect.contains(p)) {
            minDisP = getNearest(node.lb, p, minDisP);
            minDis = p.distanceTo(minDisP);
            if (node.xFlag && minDis < node.rect.xmax()-p.x())
                return minDisP;
            else if (!node.xFlag && minDis < node.rect.ymax()-p.y())
                return minDisP;
            minDisP = getNearest(node.rt, p, minDisP);
        } else {
            minDisP = getNearest(node.rt, p, minDisP);
            minDis = p.distanceTo(minDisP);
            if (node.xFlag && minDis < (p.x()-node.rect.xmax()))
                return minDisP;
            else if (!node.xFlag && minDis < (p.y()-node.rect.ymax()))
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
        //treeSet.insert(new Point2D(0.5, 0.4));
        treeSet.insert(new Point2D(0.2, 0.3));
        treeSet.insert(new Point2D(0.2, 0.4));
        treeSet.insert(new Point2D(0.2, 0.5));
        //treeSet.insert(new Point2D(0.2, 0.6));

         //treeSet.insert(new Point2D(0.4, 0.3));
       // treeSet.insert(new Point2D(0.4,0.7));
       // treeSet.insert(new Point2D(0.8,0.6));

        StdDraw.setXscale(0, 1.0);
        StdDraw.setYscale(0, 1.0);
        StdDraw.setPenRadius(0.01);

        treeSet.draw();

    }
}

