package com.interview.geometry;

import java.util.Comparator;
import java.util.Objects;

public class Point2D implements Comparable<Point2D> {

    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        if (Double.isInfinite(x) || Double.isInfinite(y))
            throw new IllegalArgumentException("Coordinates must be finite");

        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("Coordinates cannot be NaN");

        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Returns true if a→b→c is a counterclockwise turn.
     * @param a first point
     * @param b second point
     * @param c third point
     * @return { -1, 0, +1 } if a→b→c is a { clockwise, collinear; counterclocwise } turn.
     */
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if(area2 < 0) {
            return -1;
        } else if(area2 > 0) {
            return +1;
        } else {
            return  0;
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point (x1, y1)
     * if and only if either {@code y0 < y1} or if {@code y0 == y1} and {@code x0 < x1}.
     *
     * @param  that the other point
     * @return the value {@code 0} if this string is equal to the argument
     *         string (precisely when {@code equals()} returns {@code true});
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    @Override
    public int compareTo(Point2D that) {
        if(this.y < that.y) {
            return -1;
        }
        if(this.y > that.y) {
            return +1;
        }
        if(this.x < that.x) {
            return -1;
        }
        if(this.x > that.x) {
            return +1;
        }
        return 0;
    }

    public Comparator<Point2D> polarOrder() {
        return new PolarOrder();
    }

    // compare other points relative to polar angle (between 0 and 2pi) they make with this Point
    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if(dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            else if(dy2 >= 0 && dy1 < 0) return +1;    // q1 below; q2 above
            else if(dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if(dx1 >= 0 && dx2 < 0) return -1;
                else if(dx2 >= 0 && dx1 < 0) return +1;
                else return  0;
            }
            else return -ccw(Point2D.this, q1, q2);     // both above or below

            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2D point2D = (Point2D) o;
        return Double.compare(point2D.x, x) == 0 &&
                Double.compare(point2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}