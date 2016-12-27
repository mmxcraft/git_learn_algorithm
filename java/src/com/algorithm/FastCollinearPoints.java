import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
   private static  final double KEY_NUM = 0.00000001;
   private int lineCnt = 0;
   private LineSegment[] lineSg;
   public FastCollinearPoints(Point[] points)
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

      //  double[] slopeTo = new double[sz];

      for (ii = 0; ii < sz; ii++)
      {
         Point[] tmpArray = points.clone();
         Arrays.sort(tmpArray, points[ii].slopeOrder());

        /* for (kk = 0; kk < sz; kk++) {
            slopeTo[kk] = points[ii].slopeTo(tmpArray[kk]);
         } */

         int beginP = 0;
         double tmpSlopeTo = points[ii].slopeTo(tmpArray[beginP]);

         for (jj = 0; jj < sz; jj++) {
            if (points[ii] == tmpArray[beginP]) {
               beginP++;
               continue;
            }
            if (!SameSlope(points[ii].slopeTo(tmpArray[jj]), tmpSlopeTo)) {
               if (jj-beginP <= 2) {
                  beginP = jj;
                  tmpSlopeTo = points[ii].slopeTo(tmpArray[beginP]);
                  continue;
               }
            } else if ((jj == sz-1 && jj-beginP < 2) || jj != sz-1) { continue; }

            if (!isDupSlope(points, ii, tmpSlopeTo)) {
                  if (lineSg.length <= lineCnt) {
                     LineSegment[] newLsg = new LineSegment[lineSg.length + 128];
                     for (int m = 0; m < lineSg.length; m++) {
                        newLsg[m] = lineSg[m];
                     }
                     lineSg = newLsg;
                  }
                  Point lastPoint;
                  if (jj == sz-1 && SameSlope(points[ii].slopeTo(tmpArray[jj]), tmpSlopeTo))
                     lastPoint = tmpArray[jj];
                  else
                     lastPoint = tmpArray[jj-1];
                  LineSegment newLine = new LineSegment(points[ii], lastPoint);
                  lineSg[lineCnt++] = newLine;
                  /* StdOut.printf("%s -->", points[ii]);
                  for(kk=beginP;kk<jj-1;kk++)
                      StdOut.printf("%s -->", tmpArray[kk]);
                  StdOut.println(tmpArray[jj-1]);*/
               }
               beginP = jj;
               tmpSlopeTo = points[ii].slopeTo(tmpArray[beginP]);
            }
         }
   }
   private boolean isDupSlope(Point[] points, int curPos, double slope)
   {
      double existSlope;
      for (int ii = 0; ii < curPos; ii++) {
         existSlope = points[ii].slopeTo(points[curPos]);
         if (Math.abs(existSlope - slope) < KEY_NUM)
            return true;
         if (slope == Double.NEGATIVE_INFINITY || slope == Double.POSITIVE_INFINITY) {
           if (existSlope == Double.NEGATIVE_INFINITY || existSlope == Double.POSITIVE_INFINITY)
              return true;
           }
       }

       return false;
   }
   private boolean SameSlope(double slope1, double slope2)
   {
      if (slope1 == Double.NEGATIVE_INFINITY || slope1 == Double.POSITIVE_INFINITY) {
         if (slope2 == Double.NEGATIVE_INFINITY || slope2 == Double.POSITIVE_INFINITY)
            return true;
      }
      return (Math.abs(slope1 - slope2) < KEY_NUM);
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

      StdDraw.enableDoubleBuffering();
      StdDraw.setXscale(0, 32768);
      StdDraw.setYscale(0, 32768);

      for (Point p : points) {
         p.draw();
      }
      StdDraw.show();

      FastCollinearPoints fastLine = new FastCollinearPoints(points);
      for (LineSegment segment : fastLine.segments()) {
         StdOut.println(segment);
         segment.draw();
      }

      /* LineSegment[] collinearLine = fastLine.segments();

      for (int ii = 0; ii < fastLine.numberOfSegments(); ii++) {
          StdOut.println(collinearLine[ii]);
          collinearLine[ii].draw();
      } */
      StdDraw.show();
   }
}