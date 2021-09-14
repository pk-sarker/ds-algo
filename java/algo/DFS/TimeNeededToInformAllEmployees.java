package algo.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the
 * company is the one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is the direct
 * manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed that the subordination
 * relationships have a tree structure.
 *
 * The head of the company wants to inform all the company employees of an urgent piece of news.
 * He will inform his direct subordinates, and they will inform their subordinates, and so on
 * until all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
 * (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 */
public class TimeNeededToInformAllEmployees {
    public int numOfMinutes(final int n, final int headID, final int[] manager, final int[] informTime) {
        // manager, subordinates
        final Map<Integer, List<Integer>> graph = new HashMap<>();

        int total = 0;
        for (int i = 0; i < manager.length; i++) {
            int j = manager[i]; // j is the manager of i,
            if (!graph.containsKey(j))
                graph.put(j, new ArrayList<>());
            graph.get(j).add(i); // add subordinate, i, to the manager
        }
        return dfs(graph, informTime, headID);
    }

    private int dfs(final Map<Integer, List<Integer>> graph, final int[] informTime, final int cur) {
        int max = 0;
        // if cur is not a manager, leaf nodes
        if (!graph.containsKey(cur)) {
            return max;
        }

        // for current manager, calculate max inform time for each subordinates
        for (int i = 0; i < graph.get(cur).size(); i++) {// for each subordinate
            // max = max(prev max, )
            max = Math.max(max, dfs(graph, informTime, graph.get(cur).get(i)));
        }
        return max + informTime[cur]; // previous max + current cost
    }
}
