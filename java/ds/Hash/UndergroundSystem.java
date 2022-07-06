package ds.Hash;

import java.util.*;

/**
 * An underground railway system is keeping track of customer travel times between different stations. They are using this data to calculate the average time it takes to travel from one station to another.
 *
 * Implement the UndergroundSystem class:
 *
 * - void checkIn(int id, string stationName, int t)
 *      - A customer with a card ID equal to id, checks in at the station stationName at time t.
 *      - A customer can only be checked into one place at a time.
 * - void checkOut(int id, string stationName, int t)
 *      - A customer with a card ID equal to id, checks out from the station stationName at time t.
 * - double getAverageTime(string startStation, string endStation)
 *      - Returns the average time it takes to travel from startStation to endStation.
 *      - The average time is computed from all the previous traveling times from startStation to endStation that happened directly, meaning a check in at startStation followed by a check out from endStation.
 *      - The time it takes to travel from startStation to endStation may be different from the time it takes to travel from endStation to startStation.
 *      - There will be at least one customer that has traveled from startStation to endStation before getAverageTime is called.
 *
 * You may assume all calls to the checkIn and checkOut methods are consistent. If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All events happen in chronological order.
 */
public class UndergroundSystem {
    Map<String, Map<Integer, LinkedList<Integer>>> checkInMap = new HashMap<>();

    Map<String, Map<String, int[]>> checkInCheckOutMap = new HashMap<>();
    Map<Integer, String> checkedInUser = new HashMap<>();

    public UndergroundSystem() {

    }

    public void checkIn(int id, String stationName, int t) {
        if (!this.checkInMap.containsKey(stationName)) {
            Map<Integer, LinkedList<Integer>> map = new HashMap<>();
            this.checkInMap.put(stationName, map);
        }
        if (!this.checkInMap.get(stationName).containsKey(id)) {
            this.checkInMap.get(stationName).put(id, new LinkedList<>());
        }
        this.checkInMap.get(stationName).get(id).add(t);
        this.checkedInUser.put(id, stationName);
    }

    public void checkOut(int id, String stationName, int t) {
        if (!this.checkedInUser.containsKey(id)) {
            return;
        }
        String checkInStation = this.checkedInUser.get(id);

        int lastCheckInTime = this.checkInMap.get(checkInStation).get(id).getLast();

        int duration = t - lastCheckInTime;

        if (!this.checkInCheckOutMap.containsKey(checkInStation)) {
            this.checkInCheckOutMap.put(checkInStation, new HashMap<>());
        }
        if (!this.checkInCheckOutMap.get(checkInStation).containsKey(stationName)) {
            this.checkInCheckOutMap.get(checkInStation).put(stationName, new int[]{0,0});
        }
        int[] sum = this.checkInCheckOutMap.get(checkInStation).get(stationName);

        sum[0] += duration;
        sum[1]++;
        this.checkInCheckOutMap.get(checkInStation).put(stationName, sum);
    }

    public double getAverageTime(String startStation, String endStation) {
        double avgTime = 0.0;
        if (!this.checkInCheckOutMap.containsKey(startStation)) {
            return avgTime;
        }
        if (!this.checkInCheckOutMap.get(startStation).containsKey(endStation)) {
            return avgTime;
        }
        int[] sum = this.checkInCheckOutMap.get(startStation).get(endStation);

        avgTime = (double) sum[0]/sum[1];
        return avgTime;
    }

    public static void main(String args[]) {
        UndergroundSystem undergroundSystem = new UndergroundSystem();

        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);
        undergroundSystem.checkOut(27, "Waterloo", 20);
        undergroundSystem.checkOut(32, "Cambridge", 22);

        double avgTime = undergroundSystem.getAverageTime("Paradise", "Cambridge");
        System.out.println("Average time from Paradise to Cambridge is " + avgTime);

        double avgTime2 = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        System.out.println("Average time from Leyton to Waterloo is " + avgTime2);
        undergroundSystem.checkIn(10, "Leyton", 24);
        double avgTime3 = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        System.out.println("Average time from Leyton to Waterloo is " + avgTime3);
        undergroundSystem.checkOut(10, "Waterloo", 38);
        double avgTime4 = undergroundSystem.getAverageTime("Leyton", "Waterloo");
        System.out.println("Average time from Leyton to Waterloo is " + avgTime4);


        undergroundSystem.checkIn(10, "Leyton", 3);
        undergroundSystem.checkOut(10, "Paradise", 8);
        double avgTime5 = undergroundSystem.getAverageTime("Leyton", "Paradise");
        System.out.println("Average time from Leyton to Paradise is " + avgTime5);

        undergroundSystem.checkIn(5, "Leyton", 10);
        undergroundSystem.checkOut(5, "Paradise", 16);

        double avgTime6 = undergroundSystem.getAverageTime("Leyton", "Paradise");
        System.out.println("Average time from Leyton to Paradise is " + avgTime6);

        undergroundSystem.checkIn(37043, "K2618O72", 29);
        undergroundSystem.checkOut(37043, "U1DTINDT", 39);
        double avgTime7 = undergroundSystem.getAverageTime("K2618O72", "U1DTINDT");
        System.out.println("Average time from K2618O72 to U1DTINDT is " + avgTime7);

        undergroundSystem.checkIn(779975, "K2618O72", 112);
        undergroundSystem.checkOut(779975, "CN3K6CYM", 157);
        double avgTime8 = undergroundSystem.getAverageTime("K2618O72", "U1DTINDT");
        System.out.println("Average time from K2618O72 to U1DTINDT is " + avgTime8);

        undergroundSystem.checkIn(706901, "K2618O72", 221);
        double avgTime9 = undergroundSystem.getAverageTime("K2618O72", "CN3K6CYM");
        System.out.println("Average time from K2618O72 to CN3K6CYM is " + avgTime9);
    }
}
