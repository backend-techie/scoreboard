package com.example.scoreboard.controller;

import com.example.scoreboard.model.dto.RegisterGameRequest;
import com.example.scoreboard.model.dto.RegisterPlayerRequest;
import com.example.scoreboard.model.dto.UpdateScoreRequest;
import com.example.scoreboard.service.GameServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.resps.Tuple;

import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameServiceImpl gameService;

    @PostMapping("/registerGame")
    public void registerGame(@RequestBody RegisterGameRequest request){
        gameService.registerGame(request);
    }

    @PostMapping("/registerPlayer")
    public void registerPlayer(@RequestBody RegisterPlayerRequest request) throws Exception{
        gameService.registerPlayer(request);
    }

    @PostMapping("/updateScore")
    public void updateScore(@RequestBody UpdateScoreRequest request) throws Exception{
        gameService.updatePlayerScore(request);
    }

    @GetMapping("/getTop")
    public List<Tuple> getTop(){
        return gameService.getTop();
    }

}
