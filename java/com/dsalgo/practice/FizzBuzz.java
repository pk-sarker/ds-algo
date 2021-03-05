package com.dsalgo.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 *
 * But for multiples of three it should output “Fizz” instead of the number and for the
 * multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class FizzBuzz {

    /**
     * Naive Approach
     */
    public static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            boolean divisibleBy3 = i%3 == 0 ? true: false;
            boolean divisibleBy5 = i%5 == 0 ? true: false;
            if (divisibleBy3 && divisibleBy5) {
                result.add("FizzBuzz");
            } else if (divisibleBy3) {
                result.add("Fizz");
            } else if (divisibleBy5) {
                result.add("Buzz");
            } else {
                result.add(""+i);
            }
        }
        return result;
    }

    // Using string Concatenation
    // This is help full if there are lot of mapping;
    // like the number is multiple of 3, 5, 7 , 9
    public static List<String> fizzBuzzStringConcat(int n) {
        List<String> result = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            boolean divisibleBy3 = i%3 == 0 ? true: false;
            boolean divisibleBy5 = i%5 == 0 ? true: false;
            StringBuilder num = new StringBuilder();
            if (divisibleBy3) {
                num.append("Fizz");
            }
            if (divisibleBy5) {
                num.append("Buzz");
            }
            if (num.length() == 0) {
                num.append(""+i);
            }
            result.add(num.toString());
        }
        return result;
    }

    // Using HashMap
    // if there are lot of mapping then keeping the mapping in
    // hash map will be easier.
    public static List<String> fizzBuzzHashMap(int n) {
        List<String> result = new ArrayList<>();
        Map<Integer, String> hashMap = new HashMap<>(){{
            put(3, "Fizz");
            put(5, "Buzz");
        }};

        for(int i=1; i<=n; i++) {
            StringBuilder num = new StringBuilder();
            for(int key: hashMap.keySet()) {
                if (i % key == 0) {
                    num.append(hashMap.get(key));
                }
            }
            if (num.length() == 0) {
                num.append(""+i);
            }
            result.add(num.toString());
        }
        return result;
    }

    public  static void main(String args[]) {
        System.out.println("\nInput: 16 \nOutput: " + FizzBuzz.fizzBuzz(16));

        System.out.println("\nInput: 16 \nOutput: " + FizzBuzz.fizzBuzzStringConcat(16));

        System.out.println("\nInput: 16 \nOutput: " + FizzBuzz.fizzBuzzHashMap(16));
    }
}
