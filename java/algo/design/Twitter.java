package algo.design;

import java.util.*;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user,
 * and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *
 *  - Twitter() Initializes your twitter object.
 *  - void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
 *  - List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 *  - void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
 *  - void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 *
 *  Example:
 *  Input
 * ["Twitter", "postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"]
 * [[], [1, 5], [1], [1, 2], [2, 6], [1], [1, 2], [1]]
 * Output
 * [null, null, [5], null, null, [6, 5], null, [5]]
 *
 * Explanation
 * Twitter twitter = new Twitter();
 * twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
 * twitter.follow(1, 2);    // User 1 follows user 2.
 * twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.unfollow(1, 2);  // User 1 unfollows user 2.
 * twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
 */
public class Twitter {
    public static int timestamp = 0;
    HashMap<Integer, User> userMap;
    public class Tweet { // linked list node
        public int tweetId;
        public int time;
        public Tweet nextTweet; // tweet 1 -> tweet 2 -> tweet 3
        public Tweet(int tweetId) {
            this.tweetId = tweetId;
            this.time = timestamp++;
            this.nextTweet = null;
        }
    }
    public class User {
        public int userId;
        public Set<Integer> followed; // users followed by
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
    public Twitter() {
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
}
