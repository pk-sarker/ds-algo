package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given the array restaurants where  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei].
 * You have to filter the restaurants using three filters.
 *
 * The veganFriendly filter will be either true (meaning you should only include restaurants with veganFriendlyi set to true)
 * or false (meaning you can include any restaurant). In addition, you have the filters maxPrice and maxDistance which are
 * the maximum value for price and distance of restaurants you should consider respectively.
 *
 * Return the array of restaurant IDs after filtering, ordered by rating from highest to lowest. For restaurants with the
 * Return the array of restaurant IDs after filtering, ordered by rating from highest to lowest. For restaurants with the
 * same rating, order them by id from highest to lowest. For simplicity veganFriendlyi and veganFriendly take value 1 when
 * it is true, and 0 when it is false.
 *
 * Input: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
 * Output: [3,1,5]
 * Explanation:
 * The restaurants are:
 * Restaurant 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
 * Restaurant 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
 * Restaurant 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
 * Restaurant 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
 * Restaurant 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1]
 * After filter restaurants with veganFriendly = 1, maxPrice = 50 and maxDistance = 10 we have restaurant 3, restaurant 1 and restaurant 5 (ordered by rating from highest to lowest).
 */
public class FilterRestaurants {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        // heap will contain restaurant id and rating
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> a[1]==b[1] ? b[0]-a[0]:b[1]-a[1]);

        for(int i=0; i<restaurants.length;i++) {
            if (eligible(restaurants[i], new int[]{veganFriendly, maxPrice, maxDistance})) {
                heap.offer(new int[]{restaurants[i][0], restaurants[i][1]});
            }
        }
        List<Integer> res = new ArrayList<>();
        while(!heap.isEmpty()) {
            res.add(heap.poll()[0]);
        }
        return res;
    }

    private boolean eligible(int[] restaurant, int[] filters) {
        if (filters[0] == 1 && restaurant[2] != 1 || restaurant[3] > filters[1] ||  restaurant[4] > filters[2]) {
            return false;
        }

        return true;
    }
    public static void main(String args[]) {
        FilterRestaurants obj = new FilterRestaurants();

        System.out.println("\nInput: restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], \n\t\tveganFriendly = 1, maxPrice = 50, maxDistance = 10\nOutput:" + obj.filterRestaurants(new int[][]{{1,4,1,40,10},{2,8,0,50,5},{3,8,1,30,4},{4,10,0,10,3},{5,1,1,15,1}}, 1, 50, 10));
    }
}
