package com.example.scoreboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;
import java.util.List;

@Service
public class CachingServiceImpl {

    @Autowired
    private Jedis jedis;

    private final String LEADERBOARD_KEY = "leaderboard";

    public void addOrUpdateUser(String userId, double score) {
        jedis.zadd(LEADERBOARD_KEY, score, userId);
    }

    public void removeUser(String userId) {
        jedis.zrem(LEADERBOARD_KEY, userId);
    }

    public long getUserRank(String userId) {
        Long rank = jedis.zrevrank(LEADERBOARD_KEY, userId);
        return rank == null ? -1 : rank + 1; // Rank is 0-based, so add 1 to make it 1-based
    }

    public double getUserScore(String userId) {
        Double score = jedis.zscore(LEADERBOARD_KEY, userId);
        return score == null ? 0 : score;
    }

    public List<Tuple> getTopUsers(int topN) {
        return jedis.zrevrangeWithScores("leaderboard", 0, topN - 1);
    }

    public List<Tuple> getLowUsers(int topN) {
        return jedis.zrangeByScoreWithScores("leaderboard", 0, topN - 1);
    }


    public List<Tuple> getUsersInRange(int start, int end) {
        return jedis.zrevrangeWithScores(LEADERBOARD_KEY, start - 1, end - 1);
    }

    public long getTotalUsers() {
        return jedis.zcard(LEADERBOARD_KEY);
    }

}
