package com.example.scoreboard.service;

import com.example.scoreboard.model.dto.RegisterGameRequest;
import com.example.scoreboard.model.dto.RegisterPlayerRequest;
import com.example.scoreboard.model.dto.UpdateScoreRequest;
import redis.clients.jedis.resps.Tuple;

import java.util.List;

public interface GameService {

    void registerGame(RegisterGameRequest request);

    void registerPlayer(RegisterPlayerRequest request) throws Exception;

    int updatePlayerScore(UpdateScoreRequest request);

    public List<Tuple> getTop();

}
