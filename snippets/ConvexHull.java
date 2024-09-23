import java.util.*;

public class ConvexHull {
    static class Point implements Comparable<Point> {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point p) {
            if (this.y == p.y) {
                return this.x - p.x;
            }
            return this.y - p.y;
        }
    }

  /*
0 if the points are collinear,
1 if they form a clockwise turn,
2 if they form a counterclockwise turn
*/
    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        return (val == 0) ? 0 : (val > 0) ? 1 : 2;
    }

  /* imagine you have a group of tacs, and you put a rubber band around
  the outermost tacs will be touching the rubber band
  convex hull returns the outermost points
  */
    static List<Point> convexHull(Point[] points) {
        Arrays.sort(points);
        List<Point> hull = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            while (hull.size() >= 2 && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) != 2) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }

        int t = hull.size() + 1;
        for (int i = points.length - 1; i >= 0; i--) {
            while (hull.size() >= t && orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) != 2) {
                hull.remove(hull.size() - 1);
            }
            hull.add(points[i]);
        }

        hull.remove(hull.size() - 1);
        return hull;
    }
}
