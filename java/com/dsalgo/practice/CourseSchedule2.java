package com.dsalgo.practice;

import java.util.*;

public class CourseSchedule2 {

    public int[] findOrderOpt(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        int[] degree = new int[numCourses];
        for(int[] prereq: prerequisites) {
            int src = prereq[1], dest = prereq[0];
            Set<Integer> set = new HashSet<>();
            if (adjMap.containsKey(src)) {
                set = adjMap.get(src);
            }
            set.add(dest);
            adjMap.put(src, set);
            // increase in-degree for destination node
            degree[dest] += 1;
        }

        int[] result = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        while(!queue.isEmpty() && count < numCourses) {
            int course = queue.poll();
            result[count++] = course;
            if (adjMap.containsKey(course)) {
                for(int adjNode: adjMap.get(course)) {
                    degree[adjNode] -= 1;
                    if (degree[adjNode] == 0) {
                        queue.offer(adjNode);
                    }
                }
            }
        }
        // if there is any cycle then the queue will be empty,
        // all the nodes will have some degree greater than 0
        if (count == numCourses) {
            return result;
        }

        return new int[]{};
    }

    public static void main(String args[]) {
        CourseSchedule2 obj = new CourseSchedule2();

        System.out.println(Arrays.toString(obj.findOrderOpt(2, new int[][]{{1,0}})));

        System.out.println(Arrays.toString(obj.findOrderOpt(4, new int[][]{{1,0}, {2,0}, {3, 1}, {3,2}})));
    }
}
