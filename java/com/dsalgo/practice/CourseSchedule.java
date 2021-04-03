package com.dsalgo.practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
 * take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // create dependency tree

        HashMap<Integer, List<Integer>> courseAdj = new HashMap<>();
        for(int i=0; i<prerequisites.length; i++) {
            if (courseAdj.containsKey(prerequisites[i][1])) {
                courseAdj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            } else {
                courseAdj.put(prerequisites[i][1], new LinkedList<>());
                courseAdj.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }
        }

        boolean[] checked = new boolean[numCourses];
        boolean[] inPath = new boolean[numCourses];

        for(int i=0; i<numCourses; i++) {
            if (containsCycle(i, courseAdj, checked, inPath)) {
                return false;
            }
        }
        return true;
    }

    public boolean containsCycle(int currentNode, HashMap<Integer, List<Integer>> courseAdj, boolean[] checked, boolean[] inPath) {
        if (checked[currentNode]) {
            return false; // already checked this node for cycle and not found
        }

        if (inPath[currentNode]) {
            return true; // this node has been already visited, which means it contains a cycle.
        }

        // course is not in the dependency list, in this case the node
        // is a leaf node which doesn't have any dependency
        if (!courseAdj.containsKey(currentNode)) {
            return false;
        }

        // include current node in the path and traverse child nodes
        inPath[currentNode] = true;

        boolean ret = false;
        List<Integer> children = courseAdj.get(currentNode);

        for(Integer child : children) {
            // visit all  the child nodes
            ret = containsCycle(child, courseAdj, checked, inPath);
            if (ret) {
                break;
            }
        }
        // Remove the node from path  after visiting all the child nodes
        inPath[currentNode] = false;
        // After visiting the nodes in the downstream.
        //  we complete the check of this node.
        checked[currentNode] = true;

        return ret;
    }

    public static void main(String args[]) {
        CourseSchedule  obj = new CourseSchedule();
        System.out.println("Input: numCourses = 2, prerequisites = [[1,0]]\nOutput: " + obj.canFinish(2, new int[][]{{1,0}}));

        System.out.println("Input: numCourses = 2, prerequisites = [[1,0],[0,1]]\nOutput: " + obj.canFinish(2, new int[][]{{1,0}, {0,1}}));

        System.out.println("Input: numCourses = 4, prerequisites = [[1,0],[3,2],[2,1]]\nOutput: " + obj.canFinish(4, new int[][]{{1,0}, {3,2}, {2,1}}));
        System.out.println("Input: numCourses = 5, prerequisites = [[1,0],[3,2],[2,4],[4,3]]\nOutput: " + obj.canFinish(5, new int[][]{{1,0}, {3,2}, {2,4},{4, 3}}));
    }
}
