package com.ds.practice;

import java.util.*;

/**
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 *
 */
public class TimeBasedKeyValueStore {

    Map<String, Map<Integer, String>> map;
    Map<String, LinkedList<Integer>> keyPrevTime;
    int prevTime = -1;
    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore() {
        this.map  = new HashMap<>();
        this.keyPrevTime = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        LinkedList<Integer> prevTimestamp = this.keyPrevTime.get(key);
        if (prevTimestamp == null) {
            this.keyPrevTime.computeIfAbsent(key, k-> new LinkedList<>()).addLast(timestamp);
        } else if (timestamp > prevTimestamp.getLast() + 1 ){
            String prevVal = map.get(key).get(prevTimestamp.getLast());
            for(int i=prevTimestamp.getLast()+1; i< timestamp; i++) {
                map.computeIfAbsent(key, k -> new HashMap<>()).put(i, prevVal);
            }
            this.keyPrevTime.computeIfAbsent(key, k-> new LinkedList<>()).addLast(timestamp);
        } else {
            this.keyPrevTime.computeIfAbsent(key, k-> new LinkedList<>()).addLast(timestamp);
        }
        map.computeIfAbsent(key, k -> new HashMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        Map<Integer, String> timeValue = map.get(key);
        if (!timeValue.containsKey(timestamp)) {
            if (timestamp > this.keyPrevTime.get(key).getLast())  {
                return timeValue.get(this.keyPrevTime.get(key).getLast());
            }

            if (timestamp < this.keyPrevTime.get(key).getFirst())  {
                return "";
            }
        }
        return map.get(key).get(timestamp);
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length <=1) {
            res.add(0);
            return res;
        }

        for(int i=0; i<nums.length; i++) {
            res.add(i, 0);
            int j = i;
            while(j>0) {
                if (nums[j-1] > nums[i]) {
                    res.set(j-1, res.get(j-1)+1);
                }
                j--;
            }
        }

        System.out.println(res);
        return res;
    }

    public static void main(String args[]) {
        TimeBasedKeyValueStore ob = new TimeBasedKeyValueStore();
//        System.out.println("Set(foo, bar, 1)");
//        ob.set("foo","bar",1);
//        System.out.println("get(foo, 1): " + ob.get("foo",1));
//        System.out.println("get(foo, 3): " + ob.get("foo",3));
//        System.out.println("Set(foo, bar2, 2)");
//        ob.set("foo","bar2",2);
//        System.out.println("get(foo, 4): " + ob.get("foo",4));
//        System.out.println("get(foo, 2): " + ob.get("foo",2));
//        System.out.println("Set(foo, bar3, 3)");
//        ob.set("foo","bar3",3);
//        System.out.println("get(foo, 3): " + ob.get("foo",3));
//        System.out.println("Set(foo, bar6, 6)");
//        ob.set("foo","bar6",6);
//        System.out.println("get(foo, 6): " + ob.get("foo",6));
//
//        System.out.println("get(foo, 5): " + ob.get("foo",5));
//        System.out.println("get(foo, 4): " + ob.get("foo",4));
//        System.out.println("get(foo, 3): " + ob.get("foo",3));
//        System.out.println("get(foo, 2): " + ob.get("foo",2));

          /*System.out.println("Set(foo, bar, 1)");
          ob.set("foo","bar",1);
          System.out.println("get(foo, 1): bar == " + ob.get("foo",1));
          System.out.println("get(foo, 3): bar == " + ob.get("foo",3));
          System.out.println("Set(foo, bar2, 2)");
          ob.set("fooo","barr",3);
          ob.set("foo","bar2",4);
        System.out.println("get(foo, 4): bar == " + ob.get("foo",4));
        System.out.println("get(foo, 5): bar == " + ob.get("foo",5));
        System.out.println("get(foo, 5): bar == " + ob.get("fooo",4));*/

        //System.out.println(ob.countSmaller(new int[]{5,2,6,1}));

        System.out.println("Set(love, high, 10)");
        ob.set("love","high",10);
        System.out.println("Set(love, low, 20)");
        ob.set("love","low",20);
        System.out.println("get(love, 5): \"\" == " + ob.get("love",5));
        System.out.println("get(love, 10): \"high\" == " + ob.get("love",10));
        System.out.println("get(love, 15): \"high\" == " + ob.get("love",15));
        System.out.println("get(love, 20): \"low\" == " + ob.get("love",20));
        System.out.println("get(love, 25): \"low\" == " + ob.get("love",25));

    }
}
