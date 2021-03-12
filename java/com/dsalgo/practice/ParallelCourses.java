package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given an integer n which indicates that we have n courses, labeled from 1 to n.
 * You are also given an array relations where relations[i] = [a, b], representing a
 * prerequisite relationship between course a and course b: course a has to be studied
 * before course b.
 *
 * In one semester, you can study any number of courses as long as you have studied all
 * the prerequisites for the course you are studying.
 *
 * Return the minimum number of semesters needed to study all courses. If there is no way
 * to study all the courses, return -1.
 *
 *
 * Input: n = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation: In the first semester, courses 1 and 2 are studied.
 * In the second semester, course 3 is studied.
 *
 * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: No course can be studied because they depend on each other.
 *
 *
 */
public class ParallelCourses {


    public static int minimumSemesters(int N, int[][] relations) {
        // considering each course is a node and
        // e is a  directed edge between course a to b if
        // a is prerequisite  of b.

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<relations.length; i++) {
            graph.get(relations[i][0]).add(relations[i][1]);
        }

        int[] nodeInEdge = new int[N+1]; // n courses, labeled from 1 to n.
        Arrays.fill(nodeInEdge, 0);
        int result = 0, nodeVisited = 0;
        for(int i=0; i<relations.length; i++) {
            nodeInEdge[relations[i][1]]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i=1; i<N+1; i++) {
            if (nodeInEdge[i]==0) {
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()) {
            result++;
            LinkedList<Integer> newQueue = new LinkedList<>();
            for(int node: queue) {
                nodeVisited++;
                for(int endNode: graph.get(node)) {
                    nodeInEdge[endNode]--;

                    if (nodeInEdge[endNode]==0) {
                        newQueue.offer(endNode);
                    }
                }
            }
            queue.clear();
            queue = newQueue;
        }
        return nodeVisited ==  N ?  result: -1;
    }

    public static void main(String args[]) {
        System.out.println("\nInput: N=3 relations=[[1,3],[2,3]] \nOutput: " + ParallelCourses.minimumSemesters(3, new int[][]{{1,3},{2,3}}));
    }

}
