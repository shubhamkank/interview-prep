package com.interview.geometry;

import java.util.ArrayList;
import java.util.List;

//http://jeffe.cs.illinois.edu/teaching/compgeom/notes/01-convexhull.pdf
public class JarvisMarchConvexHull {

    private List<Point2D> hull = new ArrayList<>();

    /* Also called 'Gift-wrapping algorithm
       Time complexity: O(nh) - h is the number of convex hull vertices, Space complexity: O(n)
       Worst case time complexity - O(n^2) */
    public JarvisMarchConvexHull(Point2D[] points) {
        if (points == null) throw new IllegalArgumentException("argument is null");
        if (points.length == 0) throw new IllegalArgumentException("array is of length 0");

        int n = points.length;

        int l = 0;
        for (int i = 1; i < n; i++) {
            if(Double.compare(points[i].getX(), points[l].getX()) < 0) {
                l = i;
            }
        }

        int p = l, q;
        do {
            hull.add(points[p]);
            q = (p + 1) % n;
            for (int i = 0; i < n; i++) {
                if(Point2D.ccw(points[p], points[i], points[q]) > 0) {
                    q = i;
                }
            }
            p = q;
        } while (p != l);
    }

    public static void main(String[] args) {
        Point2D[] points = new Point2D[8];
        points[0] = new Point2D(0, 3);
        points[1] = new Point2D(1, 1);
        points[2] = new Point2D(2, 2);
        points[3] = new Point2D(4, 4);
        points[4] = new Point2D(0, 0);
        points[5] = new Point2D(1, 2);
        points[6] = new Point2D(3, 1);
        points[7] = new Point2D(3, 3);
        JarvisMarchConvexHull jarvisMarchConvexHull = new JarvisMarchConvexHull(points);
        System.out.println(jarvisMarchConvexHull.hull);
    }
}
