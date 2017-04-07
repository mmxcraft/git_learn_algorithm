import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

public class BruteCollinearPoints {
   private static final double KEY_NUM = 0.00000001;
   private int lineCnt = 0;
   private LineSegment[] lineSg;
   public BruteCollinearPoints(Point[] points)
   {
       
       lineSg = new LineSegment[128];
       if (points == null)
           throw new  java.lang.NullPointerException();
    
       int ii, jj, kk, ll;
       
       int sz = points.length;

       Arrays.sort(points);

       for (ii = 0; ii < sz-1; ii++)
           if (points[ii].compareTo(points[ii+1]) == 0)
               throw new java.lang.IllegalArgumentException();

       double sTo1, sTo2, sTo3;

       for (ii = 0; ii < sz-3; ii++)
           for (jj = ii+1; jj < sz-2; jj++) {
               sTo1 = points[ii].slopeTo(points[jj]);
               for (kk = jj+1; kk < sz-1; kk++) {
                   sTo2 = points[ii].slopeTo(points[kk]);
                   if (Math.abs(sTo1 -sTo2) > KEY_NUM)
                       continue;
                   for (ll = kk+1; ll < sz; ll++) {
                       sTo3 = points[ii].slopeTo(points[ll]);
                       if (Math.abs(sTo2 - sTo3) > KEY_NUM)
                           continue;
                       else {
                           if (lineSg.length <= lineCnt) {
                               LineSegment[] newLsg = new LineSegment[lineSg.length + 128];
                               for (int m = 0; m < lineSg.length; m++) {
                                   newLsg[m] = lineSg[m];
                               }
                               lineSg = newLsg;
                           }
                       }
                       LineSegment newLine = new LineSegment(points[ii], points[ll]);
                       lineSg[lineCnt] = newLine;
                       lineCnt++;
                       /* StdOut.printf("%s -->", points[ii]);
                       StdOut.printf("%s -->", points[jj]);
                       StdOut.printf("%s -->", points[kk]);
                       StdOut.printf("%s \n",  points[ll]); */
                       break;
                   } // end for ll loop
               } // end for kk loop
           } // end for jj loop
   }
   public           int numberOfSegments()        // the number of line segments
   {
       return lineCnt;
   }
   public LineSegment[] segments()                // the line segments
   {
       LineSegment[] result = new LineSegment [lineCnt];
       for (int ii = 0; ii < lineCnt; ii++) {
           result[ii] = lineSg[ii];
       }
       return  result.clone();
   }
   public static void main(String[] args)
   {
       In in = new In(args[0]);
       int n = in.readInt();
       Point[] points = new Point[n];
       for (int i = 0; i < n; i++) {
        int x = in.readInt();
        int y = in.readInt();
        points[i] = new Point(x, y);
       }

       //StdDraw.enableDoubleBuffering();
       StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);

       for (Point p : points) {
           p.draw();
       }
       StdDraw.show();

       BruteCollinearPoints bruteLine = new BruteCollinearPoints(points);
       for (LineSegment segment : bruteLine.segments()) {
           StdOut.println(segment);
           segment.draw();
       }
       /*
       LineSegment[] lineSegments  = bruteLine.segments();
       for (int ii = 0; ii < bruteLine.numberOfSegments(); ii++) {
           StdOut.println(lineSegments[ii]);
           lineSegments[ii].draw();
       } */
       StdDraw.show();
   }
}