package com.example.scoreboard.model.entity;

import com.example.scoreboard.model.entity.Player;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String eventName;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Player> players;
}
