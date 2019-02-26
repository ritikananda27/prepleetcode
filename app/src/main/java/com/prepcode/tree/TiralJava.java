package com.prepcode.tree;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TiralJava {

    public String reverseWords(String s) {
        if (s.isEmpty()) return null;
        StringBuilder sb = new StringBuilder();
        String[] strArr = s.split(" ");
        for (int i = strArr.length - 1; i > 0; i--) {
            String st = strArr[i];
            st.trim();
            if (st != null && st.length() > 0) {
                sb.append(st + " ");
            }

        }

        return sb.toString();

    }

    @TargetApi(Build.VERSION_CODES.N)
    public int minMeetingRooms() {
        //[[9,10],[4,9],[4,17]]
        Interval[] intervals = new Interval[3];
        intervals[0] = (new Interval(0, 30));
        intervals[1] = new Interval(5, 10);
        intervals[2] = new Interval(15, 20);
        if (intervals.length == 0) {
            return 0;
        }
        //Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a.start - b.start);

        PriorityQueue<Interval> heap = new PriorityQueue<>((a, b) -> a.end - b.end);

        heap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            Interval interval = heap.poll();
            if (intervals[i].start >= interval.end) {
                heap.offer(intervals[i]);
            } else {
                heap.offer(interval);
                heap.offer(intervals[i]);
            }
        }
        return heap.size();
    }


    public class Interval {

        public int start;
        public int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;

        }
    }

}