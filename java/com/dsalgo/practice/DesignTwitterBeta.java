package com.dsalgo.practice;

import java.util.*;

public class DesignTwitterBeta {
    public static int timestamp = 0;
    HashMap<Integer, User> userMap;
    public class Tweet {
        public int tweetId;
        public int time;
        public Tweet nextTweet;
        public Tweet(int tweetId) {
            this.tweetId = tweetId;
            this.time = timestamp++;
            this.nextTweet = null;
        }
    }
    public class User {
        public int userId;
        public Set<Integer> followed;
        public Tweet tweetHead;
        public User(int userId) {
            this.userId = userId;
            followed = new HashSet<>();
            tweetHead = null;
            follow(userId); // follow itself
        }

        public void follow(int userId) {
            this.followed.add(userId);
        }
        public void unfollow(int userId) {
            this.followed.remove(userId);
        }

        public void postTweet(int tweetId) {
            Tweet t = new Tweet(tweetId);
            t.nextTweet = tweetHead;
            tweetHead = t;
        }
    }
    /** Initialize your data structure here. */
    public DesignTwitterBeta() {
        this.userMap = new HashMap<Integer, User>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        userMap.get(userId).postTweet(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> last10feeds =  new LinkedList<>();
        if (!userMap.containsKey(userId)) {
            return last10feeds;
        }
        User u = userMap.get(userId);
        //System.out.println(">> " + u.followed.size());

        PriorityQueue<Tweet> heap = new PriorityQueue<>(u.followed.size(), (a, b) -> b.time-a.time);

        for(int user : u.followed) {
            Tweet t = userMap.get(user).tweetHead;
            if (t != null) {
                heap.add(t);
            }
        }
        int n = 0;
        while(!heap.isEmpty() && n < 10) {
            Tweet t = heap.poll();
            last10feeds.add(t.tweetId);
            n++;
            if (t.nextTweet != null) {
                heap.offer(t.nextTweet);
            }
        }

        return last10feeds;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            userMap.get(followerId).unfollow(followeeId);
        }
    }

    public static void main(String args[]) {
        DesignTwitterBeta obj = new DesignTwitterBeta();
        obj.postTweet(1,5);
        obj.postTweet(1,3);
        System.out.println(obj.getNewsFeed(1).toString());
    }
}
