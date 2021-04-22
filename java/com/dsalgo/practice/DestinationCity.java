package com.dsalgo.practice;

import java.util.*;

/**
 * Input: paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
 * Output: "Sao Paulo"
 * Explanation: Starting at "London" city you will reach "Sao Paulo" city which is the destination city.
 * Your trip consist of: "London" -> "New York" -> "Lima" -> "Sao Paulo".
 *
 * Input: paths = [["B","C"],["D","B"],["C","A"]]
 * Output: "A"
 * Explanation: All possible trips are:
 * "D" -> "B" -> "C" -> "A".
 * "B" -> "C" -> "A".
 * "C" -> "A".
 * "A".
 * Clearly the destination city is "A".
**/
public class DestinationCity {

    public static String destCity(List<List<String>> paths) {
             Map<String, Integer> outgointCount = new HashMap<>();
             Set<String> nodeWithNoOutgoingPath = new HashSet<>();
             for(List<String> path : paths) {
                 outgointCount.put(path.get(0), outgointCount.getOrDefault(path.get(0), 0)+1);
                 nodeWithNoOutgoingPath.remove(path.get(0));
                 if (!outgointCount.containsKey(path.get(1))) {
                     nodeWithNoOutgoingPath.add(path.get(1));
                 }
             }
             StringBuilder sb = new StringBuilder();
             for(String city : nodeWithNoOutgoingPath) {
                 sb.append(city);
                 break;
             }
             return sb.toString();
    }

    public static void main(String args[]) {
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList("London","New York"));
        paths.add(Arrays.asList("New York","Lima"));
        paths.add(Arrays.asList("Lima","Sao Paulo"));
        System.out.println("\nOutput: " + DestinationCity.destCity(paths));


        List<List<String>> paths1 = new ArrayList<>();
        paths1.add(Arrays.asList("B","C"));
        paths1.add(Arrays.asList("D","B"));
        paths1.add(Arrays.asList("C","A"));
        System.out.println("\nOutput: " + DestinationCity.destCity(paths1));

        List<List<String>> paths2 = new ArrayList<>();
        paths2.add(Arrays.asList("A","Z"));
        System.out.println("\nOutput: " + DestinationCity.destCity(paths2));

        List<List<String>> paths3 = new ArrayList<>();
        paths3.add(Arrays.asList("jMgaf WaWA","iinynVdmBz"));
        paths3.add(Arrays.asList(" QCrEFBcAw","wRPRHznLWS"));
        paths3.add(Arrays.asList("iinynVdmBz","OoLjlLFzjz"));
        paths3.add(Arrays.asList("OoLjlLFzjz"," QCrEFBcAw"));
        paths3.add(Arrays.asList("IhxjNbDeXk","jMgaf WaWA"));
        paths3.add(Arrays.asList("jmuAYy vgz","IhxjNbDeXk"));

        System.out.println("\nOutput: " + DestinationCity.destCity(paths3));
    }
}
