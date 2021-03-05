package com.dsalgo.practice;

import java.util.*;

/**
 *  Given a list of airline tickets represented by pairs of departure and arrival airports
 *  [from, to], reconstruct the itinerary in order. All of the tickets belong to a man
 *  who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 *  Note:
 * * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * * All airports are represented by three capital letters (IATA code).
 * * You may assume all tickets form at least one valid itinerary.
 * * One must use all the tickets once and only once.
 *
 *
 */
public class ReconstructItinerary {

    public static List<String> findItinerary(String[][] tickets) {
        List<String> result = new LinkedList<>();
        //  hash  map of the tickets
        Map<String, PriorityQueue<String>> hashMap = new HashMap<>();
        for(String[] ticket: tickets)  {
            //hashMap.getOrDefault(ticket[0], new PriorityQueue<String>()).add(ticket[1]);
            hashMap.computeIfAbsent(ticket[0], k -> new PriorityQueue<String>()).add(ticket[1]);
        }
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while(!stack.isEmpty()) {
            System.out.println("-> Stack top: " + stack.peek());
            while(hashMap.containsKey(stack.peek()) && !hashMap.get(stack.peek()).isEmpty()) {
                System.out.println("--> Stack push: " + hashMap.get(stack.peek()).peek());
                stack.push(hashMap.get(stack.peek()).poll());
            }
            System.out.println("-> Stack pop: " + stack.peek());
            result.add(0, stack.pop())
;        }
        return result;
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new LinkedList<>();
        //  hash  map of the tickets
        Map<String, PriorityQueue<String>> hashMap = new HashMap<>();

        for(List<String> ticket: tickets)  {
            hashMap.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<String>()).add(ticket.get(1));
        }
        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while(!stack.isEmpty()) {
            System.out.println("-> Stack top: " + stack.peek());
            while(hashMap.containsKey(stack.peek()) && !hashMap.get(stack.peek()).isEmpty()) {
                System.out.println("--> Stack push: " + hashMap.get(stack.peek()).peek());
                stack.push(hashMap.get(stack.peek()).poll());
            }
            System.out.println("-> Stack pop: " + stack.peek());
            result.add(0, stack.pop());
        }
        return result;
    }

    public static void main(String args[]) {
        //System.out.println(ReconstructItinerary.findItinerary(new String[][] {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}}));
        List<List<String>> tickets = new ArrayList<List<String>>();
        tickets.add(new ArrayList<>(){{ add("MUC"); add("LHR");}});
        tickets.add(new ArrayList<>(){{ add("JFK"); add("MUC");}});
        tickets.add(new ArrayList<>(){{ add("SFO"); add("SJC");}});
        tickets.add(new ArrayList<>(){{ add("LHR"); add("SFO");}});
        System.out.println(ReconstructItinerary.findItinerary(tickets));
    }
}
