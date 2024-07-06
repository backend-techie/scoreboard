package com.example.scoreboard.service;

import com.example.scoreboard.model.dto.RegisterGameRequest;
import com.example.scoreboard.model.dto.RegisterPlayerRequest;
import com.example.scoreboard.model.dto.UpdateScoreRequest;
import com.example.scoreboard.model.entity.Game;
import com.example.scoreboard.model.entity.Player;
import com.example.scoreboard.repository.GameRepository;
import com.example.scoreboard.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.resps.Tuple;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    CachingServiceImpl cachingService;

    public void registerGame(RegisterGameRequest request){
        Game game = new Game();
        game.setEventName(request.getEventName());
        gameRepository.save(game);
    }


    public void registerPlayer(RegisterPlayerRequest request) throws Exception{
        Player player = new Player();
        player.setName(request.getPlayerName());
        Optional<Game> optionalGame = gameRepository.findById(request.getGameId());

        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            player.setGame(game);
            playerRepository.save(player);
        } else {
            throw new Exception("Game not found for id: " + request.getGameId());
        }
    }

    @Transactional
    public int updatePlayerScore(UpdateScoreRequest request) {
        cachingService.addOrUpdateUser(Long.toString(request.getPlayerId()), request.getScore());
        return playerRepository.updatePlayerScoreById(request.getPlayerId(), request.getScore());
    }

    public List<Tuple> getTop(){
        return cachingService.getTopUsers(3);
    }
}
