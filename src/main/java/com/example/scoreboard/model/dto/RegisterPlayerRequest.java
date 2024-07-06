package com.example.scoreboard.model.dto;

import lombok.Data;

@Data
public class RegisterPlayerRequest {

    private String playerName;

    private Long gameId;

}
