package algo.design;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Design a food rating system that can do the following:
 *
 *  - Modify the rating of a food item listed in the system.
 *  - Return the highest-rated food item for a type of cuisine in the system.
 *
 * Implement the FoodRatings class:
 *
 *  - FoodRatings(String[] foods, String[] cuisines, int[] ratings) Initializes the system. The food items are described by foods, cuisines and ratings, all of which have a length of n.
 *      - foods[i] is the name of the ith food,
 *      - cuisines[i] is the type of cuisine of the ith food, and
 *      - ratings[i] is the initial rating of the ith food.
 *  - void changeRating(String food, int newRating) Changes the rating of the food item with the name food.
 *  - String highestRated(String cuisine) Returns the name of the food item that has the highest rating for the given type of cuisine. If there is a tie, return the item with the lexicographically smaller name.
 *
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is, either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 */
public class DesignFoodRatingSystem {
    class FoodItem {
        public String food;
        public String cuisine;
        public int rating;
        public FoodItem(String food, String cuisine, int rate) {
            this.food = food;
            this.cuisine = cuisine;
            this.rating = rate;
        }
    }

    Map<String, FoodItem> foodMap = new HashMap<>();
    Map<String, PriorityQueue<FoodItem>> cuisineMap = new HashMap<>();
    public DesignFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
        for (int i=0; i<foods.length; i++) {
            if (!cuisineMap.containsKey(cuisines[i])) {
                // if ratting is equal then,  a.food.compareTo(b.food)  - ascending order of the food name
                // if rating is not equal, then b.rating-a.rating, food in descending order
                PriorityQueue<FoodItem> priorityQueue = new PriorityQueue<>((a,b) -> a.rating == b.rating ? a.food.compareTo(b.food) : b.rating-a.rating);
                cuisineMap.put(cuisines[i], priorityQueue);
            }
            FoodItem food = new FoodItem(foods[i], cuisines[i], ratings[i]);
            cuisineMap.get(cuisines[i]).add(food);
            foodMap.put(foods[i], food);
        }
    }

    public void changeRating(String food, int newRating) {
        FoodItem existingFood = foodMap.get(food);
        PriorityQueue<FoodItem> priorityQueue = cuisineMap.get(existingFood.cuisine);
        priorityQueue.remove(existingFood);
        existingFood.rating = newRating;
        priorityQueue.add(existingFood);
    }

    public String highestRated(String cuisine) {
        return cuisineMap.get(cuisine).peek().food;
    }

    public static void main(String args[]) {

        String[] foods = new String[]{"kimchi", "miso", "sushi", "moussaka", "ramen", "bulgogi"};
        String[] cuisines = new String[]{"korean", "japanese", "japanese", "greek", "japanese", "korean"};
        int[] ratings = new int[]{9, 12, 8, 15, 14, 7};

        DesignFoodRatingSystem obj = new DesignFoodRatingSystem(foods, cuisines, ratings);

        System.out.println("Highest Rated food in korean : " + obj.highestRated("korean"));
        System.out.println("Highest Rated food in japanese : " + obj.highestRated("japanese"));
        obj.changeRating("sushi", 16);
        System.out.println("Highest Rated food in japanese : " + obj.highestRated("japanese"));
        obj.changeRating("ramen", 16);
        System.out.println("Highest Rated food in japanese : " + obj.highestRated("japanese"));
    }
}
