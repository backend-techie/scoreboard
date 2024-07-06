package com.example.scoreboard.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long score;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
