package com.dsalgo.practice;

import java.util.*;

// Counting Triangles
public class CountingTriangles {

    static class Sides{
        int a;
        int b;
        int c;
        Sides(int a,int b,int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static int countDistinctTriangles(ArrayList<Sides> arr) {
        // Write your code here
        // [[2, 2, 3], [3, 2, 2], [2, 5, 6]]
        // [2, 2, 3] => 2-> [0] 2->[0], 3->[0]
        // [3, 2, 2] => all in Map
        //           => 3 is in 0-th triangle,
        //           => check remaining [2, 2] in 0-th triangle
        //           => if one of the point doesn't match try next work
        HashMap<Integer, HashSet<Integer>> sidesMap = new HashMap<>();
        int count = 0;
        for(int i=0;i<arr.size();i++) {
            Sides s = arr.get(i);
            if (sidesMap.containsKey(s.a) && sidesMap.containsKey(s.b) && sidesMap.containsKey(s.c)) {
                // check for duplicate
                HashSet<Integer> indices1 = sidesMap.get(s.a);
                indices1.addAll(sidesMap.get(s.a));
                indices1.addAll(sidesMap.get(s.a));

                // set of indices
                boolean isDuplicate = false;
                for(Integer n : indices1){
                    Sides st = arr.get(n);
                    List<Integer> points = new ArrayList<>();
                    points.add(st.a);
                    points.add(st.b);
                    points.add(st.c);
                    points.remove(new Integer(s.a));
                    points.remove(new Integer(s.b));
                    points.remove(new Integer(s.c));

                    if (points.isEmpty()) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    count++;
                }
            } else {
                count++;
                sidesMap.computeIfAbsent(s.a, k -> new HashSet<>()).add(i);
                sidesMap.computeIfAbsent(s.b, k -> new HashSet<>()).add(i);
                sidesMap.computeIfAbsent(s.c, k -> new HashSet<>()).add(i);
            }
        }
        return count;
    }

    public static void main(String args[]) {
        ArrayList<Sides> arr = new ArrayList<>();
        arr.add(new Sides(2,2,3));
        arr.add(new Sides(3,2,2));
        arr.add(new Sides(2,5,6));
        System.out.println(CountingTriangles.countDistinctTriangles(arr));

        ArrayList<Sides> arr_1 = new ArrayList<>();
        arr_1.add(new Sides(7, 6, 5));
        arr_1.add(new Sides(5, 7, 6));
        arr_1.add(new Sides(8, 2, 9));
        arr_1.add(new Sides(2, 3, 4));
        arr_1.add(new Sides(2, 4, 3));
        System.out.println(CountingTriangles.countDistinctTriangles(arr_1));

        ArrayList<Sides> arr_2 = new ArrayList<>();
        arr_2.add(new Sides(3, 4, 5));
        arr_2.add(new Sides(8, 8, 9));
        arr_2.add(new Sides(7, 7, 7));
        System.out.println(CountingTriangles.countDistinctTriangles(arr_2));

        ArrayList<Sides> arr_3 = new ArrayList<>();
        arr_3.add(new Sides(8, 4, 6));
        arr_3.add(new Sides(100, 101, 102));
        arr_3.add(new Sides(84, 93, 173));
        System.out.println(CountingTriangles.countDistinctTriangles(arr_3));

        ArrayList<Sides> arr_4 = new ArrayList<>();
        arr_4.add(new Sides(5,8,9));
        arr_4.add(new Sides(5,9,8));
        arr_4.add(new Sides(9,5,8));
        arr_4.add(new Sides(9,8,5));
        arr_4.add(new Sides(8,9,5));
        arr_4.add(new Sides(8,5,9));
        System.out.println(CountingTriangles.countDistinctTriangles(arr_4));

    }
}
