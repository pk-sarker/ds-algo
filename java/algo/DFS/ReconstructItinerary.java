package algo.DFS;

import java.util.*;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and
 * the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK".
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest
 * lexical order when read as a single string.
 *
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and
 * only once.
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 *
 * Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 *
 */
public class ReconstructItinerary {
    Map<String, PriorityQueue<String>> flightMap = new HashMap<>();
    LinkedList<String> result = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> ticket : tickets) {
            if (!flightMap.containsKey(ticket.get(0))) {
                flightMap.put(ticket.get(0), new PriorityQueue<>());
            }
            flightMap.get(ticket.get(0)).offer(ticket.get(1));
        }
        //this.dfs("JFK");
        this.dfsItr("JFK");
        return this.result;
    }

    public void dfs(String src) {
        if (this.flightMap.containsKey(src)) {
            PriorityQueue<String> adjDest = this.flightMap.get(src);
            while(!adjDest.isEmpty()) {
                String dest = adjDest.poll();
                dfs(dest);
            }
        }
        this.result.addFirst(src);
    }

    public void dfsItr(String src) {
        Queue<String> q = new ArrayDeque<>();

        q.offer(src);
        while(!q.isEmpty()) {
            String st = q.poll();
            if (this.flightMap.containsKey(st)) {
                PriorityQueue<String> adjDest = this.flightMap.get(st);
                while(!adjDest.isEmpty()) {
                    String dest = adjDest.poll();
                    q.offer(dest);
                }
            }
            this.result.addLast(st);
        }
    }

    public static void main(String args[]) {
        ReconstructItinerary obj = new ReconstructItinerary();
        List<List<String>> res = new ArrayList<>();
        // ["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]
        res.add(Arrays.asList( new String[] {"MUC","LHR"}));
        res.add(Arrays.asList( new String[] {"JFK","MUC"}));
        res.add(Arrays.asList( new String[] {"SFO","SJC"}));
        res.add(Arrays.asList( new String[] {"LHR","SFO"}));

        System.out.println(obj.findItinerary(res).toString());
    }
}
