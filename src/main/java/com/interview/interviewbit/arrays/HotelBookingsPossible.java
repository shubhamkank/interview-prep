package com.interview.interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;

public class HotelBookingsPossible {

    //Time complexity: O(nlogn), Space complexity: O(n)
    public boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        ArrayList<Point> points = new ArrayList<>();
        for(int i = 0; i < arrive.size(); i++) {
            points.add(new Point(arrive.get(i), true));
            points.add(new Point(depart.get(i), false));
        }

        Collections.sort(points, (a, b) -> {
            if(a.date == b.date) {
                return Boolean.compare(a.arrival, b.arrival);
            } else {
                return Integer.compare(a.date, b.date);
            }
        });

        int count = 0;
        for(Point p : points) {
            if(p.arrival) {
                count++;
            } else {
                count--;
            }
            if(count > K) {
                return false;
            }
        }
        return true;
    }

    static class Point {
        int date;
        boolean arrival;

        public Point(int date, boolean arrival) {
            this.date = date;
            this.arrival = arrival;
        }
    }

    //Time complexity: O(nlogn), Space complexity: O(1)
    public boolean hotel2(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
        Collections.sort(arrive);
        Collections.sort(depart);

        int roomsRequired = 0;
        int i = 0, j = 0;

        while(i < arrive.size() && j < arrive.size()) {
            if(arrive.get(i) < depart.get(j)) {
                i++;
                roomsRequired++;
            } else {
                j++;
                roomsRequired--;
            }
            if(roomsRequired > K) {
                return false;
            }
        }
        return true;
    }
}
