package com.example.scoreboard.model.dto;

import lombok.Getter;

@Getter
public class UpdateScoreRequest {

    private long playerId;
    private long score;

}
