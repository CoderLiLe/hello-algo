package dataStructure.design;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/design-twitter/
 */

public class Twitter {
    private static int timestamp = 0;

    private static class Tweet {
        private int id;
        private int time;
        private Tweet next;

        // 需要传入推文内容(id)和发文时间
        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }

    private static class User {
        private int id;
        public Set<Integer> followed;
        // 用户发表的推文链表头结点
        public Tweet head;

        public User(int userId) {
            followed = new HashSet<Integer>();
            this.id = userId;
            this.head = null;

        }

        public void follow(int userId) {
            followed.add(userId);
        }

        public void unfollow(int userId) {
            // 不可以取关自己
            if (userId != this.id) {
                followed.remove(userId);
            }
        }

        public void post(int tweetId) {
            Tweet twt = new Tweet(tweetId, timestamp);
            timestamp++;
            // 将新建的推文插入链表头
            // 越靠前的推文 time 值越大
            twt.next = head;
            head = twt;
        }
    }

    // 需要一个映射将 userId 和 User 对象对应起来
    private HashMap<Integer, User> userMap = new HashMap<>();

    public Twitter() {

    }

    /**
     * user 发表一条 tweet 动态
     * @param userId
     * @param tweetId
     */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }

        User user = userMap.get(userId);
        user.post(tweetId);
    }

    /**
     * 返回该 userId 关注的人（包含自己）最近的动态id，最多10条，而且这些动态必须按从新到旧时间线排列
     * @param userId
     * @return
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;
        // 关注列表的用户id
        Set<Integer> followedIds = userMap.get(userId).followed;
        // 自动通过 time 属性从大到小排序，容量为 followedIds 的大小
        PriorityQueue<Tweet> pq = new PriorityQueue<>(followedIds.size(), (a, b) -> (b.time - a.time));

        // 先将所有链表头结点插入优先级队列
        for (Integer followedId : followedIds) {
            Tweet twt = userMap.get(followedId).head;
            if (twt == null) continue;
            pq.add(twt);
        }

        while (!pq.isEmpty()) {
            // 最多返回 10 条就够了
            if (res.size() == 10) break;
            // 弹出 time 值最大的（最近发表的）
            Tweet twt = pq.poll();
            res.add(twt.id);
            // 将下一篇 Tweet 插入进行排序
            if (twt.next != null) {
                pq.add(twt.next);
            }
        }

        return res;
    }

    /**
     * followerId 关注 followeeId
     * @param followerId 追随者
     * @param followeeId 关注者
     */
    public void follow(int followerId, int followeeId) {
        // 若 follower 不存在，则新建
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }

        // 若 followee 不存在，则新建
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }

        userMap.get(followerId).follow(followeeId);
    }

    /**
     * followerId 取消关注 followeeId，如果 followerId 不存在则什么都不做
     * @param followerId
     * @param followeeId
     */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User flwer = userMap.get(followerId);
            flwer.unfollow(followeeId);
        }
    }
}
