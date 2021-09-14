package algo.DFS;

import java.util.*;

/**
 * Given a list of flight detail with start and end city, departure and arrival time.
 *
 * [ABC 9:00 DEF 1:00,
 * DEF 1:30 GHI 3:00,
 * DEF 12:00 GHI 2:00,
 * JKL 1:00 MNO 16:00]
 *
 * So the first element is depart ABC at 9 and arrive at DEF and 1:00
 * Return true/false if there is a path between start city and end city.
 *
 * Example:
 * ABC - GHI returns true
 * ABC - MNO returns false.
 *
 *
 * ABC 9:00 DEF 1:00,
 * ABC 12:00 GHI 15:30,
 * ABC 16:00 DEF 20:00,
 * ABC 16:00 JKL 18:00,
 * ABC 16:00 MNO 22:00,
 *
 */
public class FIndPathBtnTwoCity {
    class Flight {
        public String from;
        public String to;
        public int departureTime;
        public int arrivalTime;

        public Flight(String from, String to, int depTime, int arrTime) {
            this.from = from;
            this.to = to;
            this.departureTime = depTime;
            this.arrivalTime = arrTime;
        }
    }
    /**
     * <Source, <Destination, List of flights between source and destination>>
     * <
     *  ABC,
     *      <
     *          DEF, [
     *                  [9:00, 1:00],
     *               ]
     *      >
     *  DEF, <
     *          GHI, [
     *                 [1:30, 3:00],
     *                 [12:00, 2:00]
     *              ]
     *       >
     *  >
     */

    Map<String, Map<String, List<int[]>>> flightMap = new HashMap<>();

    public boolean checkPath1(String[] flights, String start, String stop) {
        flightMap.clear();
        for(String flight : flights) {
            // split the flight info by space,
            //      index 0=from,
            //      1=departure time,
            //      2=destination,
            //      3=arrival time at destination
            String[] flightDetails = flight.split(" ");
            if (!flightMap.containsKey(flightDetails[0])) {
                flightMap.put(flightDetails[0], new HashMap<>());
            }
            Map<String, List<int[]>> destinationMap = flightMap.get(flightDetails[0]);
            if (!destinationMap.containsKey(flightDetails[2])) {
                destinationMap.put(flightDetails[2], new ArrayList<>());
            }
            List<int[]> flTimes =  destinationMap.get(flightDetails[2]);
            // index 0 - departure time from the start city
            // index 1 - arrival time at destination city
            // Times are converted in minutes for easy comparison.
            flTimes.add(new int[]{ getTime(flightDetails[1]), getTime(flightDetails[3])});
        }

        if (!flightMap.containsKey(start)) {
            return false;
        }
        Queue<String> queue = new ArrayDeque<>();

        // timeMap maintains the time of the passenger at specific city
        // The purpose of this map to discard some paths if there is
        // no flight to next city when you arrive
        // For example, you arrive at DEF at 10:30 from some city, and DEF is not
        // your destination. There are two flights from DEF to two cities GHI(9:30), IJK(11:00)
        // Now after arriving at DEF at 10:30 you can't take the flight to GHI as the flight
        // departs from DEF at 9:30, but you can take the flight to IJK at 11:00
        HashMap<String, Integer> timeMap = new HashMap<>();

        // DFS search to find destination city
        queue.offer(start);

        while(!queue.isEmpty()) {
            String from = queue.poll();
            if (!flightMap.containsKey(from)) {
                continue;
            }
            Map<String, List<int[]>> toCities = flightMap.get(from);
            for(String toCity: toCities.keySet()) {
                System.out.println("From : " + from + " to: " + toCity);
                for(int[] srcDestTime : toCities.get(toCity)) {
                    System.out.println("  Dep " + srcDestTime[0] + " Ari: " + srcDestTime[1]);
                    if (timeMap.isEmpty() || !timeMap.containsKey(from)) {
                        timeMap.put(toCity, srcDestTime[1]);
                        if (toCity.equals(stop)) {
                            return true;
                        }
                        queue.offer(toCity);
                    } else if(timeMap.get(from) <  srcDestTime[0]) {
                        timeMap.put(toCity, srcDestTime[1]);
                        if (toCity.equals(stop)) {
                            return true;
                        }
                        queue.offer(toCity);
                    }
                }
            }
        }

        return false;
    }



    public boolean checkPath(String[] flights, String start, String stop) {
        flightMap.clear();
        for(String flight : flights) {
            // split the flight info by space,
            //      index 0=from,
            //      1=departure time,
            //      2=destination,
            //      3=arrival time at destination
            String[] flightDetails = flight.split(" ");
            if (!flightMap.containsKey(flightDetails[0])) {
                flightMap.put(flightDetails[0], new HashMap<>());
            }
            Map<String, List<int[]>> destinationMap = flightMap.get(flightDetails[0]);
            if (!destinationMap.containsKey(flightDetails[2])) {
                destinationMap.put(flightDetails[2], new ArrayList<>());
            }
            List<int[]> flTimes =  destinationMap.get(flightDetails[2]);
            // index 0 - departure time from the start city
            // index 1 - arrival time at destination city
            // Times are converted in minutes for easy comparison.
            flTimes.add(new int[]{ getTime(flightDetails[1]), getTime(flightDetails[3])});
        }

        if (!flightMap.containsKey(start)) {
            return false;
        }
        Queue<Flight> queue = new ArrayDeque<>();  // need to keep track of multiple dest from same start

        // timeMap maintains the time of the passenger at specific city
        // The purpose of this map to discard some paths if there is
        // no flight to next city when you arrive
        // For example, you arrive at DEF at 10:30 from some city, and DEF is not
        // your destination. There are two flights from DEF to two cities GHI(9:30), IJK(11:00)
        // Now after arriving at DEF at 10:30 you can't take the flight to GHI as the flight
        // departs from DEF at 9:30, but you can take the flight to IJK at 11:00
        HashMap<String, Integer> timeMap = new HashMap<>();

        // DFS search to find destination city
//        queue.offer(start);
//
//        while(!queue.isEmpty()) {
//            String from = queue.poll();
//            if (!flightMap.containsKey(from)) {
//                continue;
//            }
//            Map<String, List<int[]>> toCities = flightMap.get(from);
//            for(String toCity: toCities.keySet()) {
//                System.out.println("From : " + from + " to: " + toCity);
//                for(int[] srcDestTime : toCities.get(toCity)) {
//                    System.out.println("  Dep " + srcDestTime[0] + " Ari: " + srcDestTime[1]);
//                    if (timeMap.isEmpty() || !timeMap.containsKey(from)) {
//                        timeMap.put(toCity, srcDestTime[1]);
//                        if (toCity.equals(stop)) {
//                            return true;
//                        }
//                        queue.offer(toCity);
//                    } else if(timeMap.get(from) <  srcDestTime[0]) {
//                        timeMap.put(toCity, srcDestTime[1]);
//                        if (toCity.equals(stop)) {
//                            return true;
//                        }
//                        queue.offer(toCity);
//                    }
//                }
//            }
//        }

        return false;
    }

    /**
     * Method to convert time in string to minute
     * @param hrmin  hh:mm
     * @return
     */
    public int getTime(String hrmin) {
        String[] hhmm = hrmin.split(":");

        int time = 0;
        time += Integer.valueOf(hhmm[0]) * 60;
        time += Integer.valueOf(hhmm[1]);
        return time;
    }
    public static void main(String args[]) {
        FIndPathBtnTwoCity obj = new FIndPathBtnTwoCity();

//        boolean res = obj.checkPath(new String[] {
//                        "ABC 9:00 DEF 1:00",
//                        "DEF 1:30 GHI 3:00",
//                        "DEF 10:00 GHI 2:00",
//                        "JKL 1:00 MNO 16:00",
//                        "GHI 2:30 MNO 10:00"
//                }, "ABC", "MNO"
//        );
//        System.out.println("ABC -> DEF " + obj.checkPath(new String[] {
//                        "ABC 9:00 DEF 1:00",
//                        "DEF 1:30 GHI 3:00",
//                        "DEF 10:00 GHI 2:00",
//                        "JKL 1:00 MNO 16:00",
//                        "GHI 2:30 MNO 10:00"
//                }, "ABC", "DEF"
//        ));
//
//        System.out.println("ABC -> GHI " + obj.checkPath(new String[] {
//                        "ABC 9:00 DEF 1:00",
//                        "DEF 1:30 GHI 3:00",
//                        "DEF 10:00 GHI 2:00",
//                        "JKL 1:00 MNO 16:00",
//                        "GHI 2:30 MNO 10:00"
//                }, "ABC", "GHI"
//        ));

        System.out.println("ABC -> JKL " + obj.checkPath(new String[] {
                        "ABC 9:00 DEF 1:00",
                        "DEF 1:30 GHI 3:00",
                        "DEF 10:00 GHI 2:00",
                        "JKL 1:00 MNO 16:00",
                        "GHI 2:30 MNO 10:00"
                }, "ABC", "JKL"
        ));

//        System.out.println("ABC -> MNO " + obj.checkPath(new String[] {
//                        "ABC 9:00 DEF 1:00",
//                        "DEF 1:30 GHI 3:00",
//                        "DEF 10:00 GHI 2:00",
//                        "JKL 1:00 MNO 16:00",
//                        "GHI 2:30 MNO 10:00"
//                }, "ABC", "MNO"
//        ));
    }
}
