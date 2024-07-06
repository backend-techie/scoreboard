package com.example.scoreboard.repository;

import com.example.scoreboard.model.entity.Player;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Player p SET p.score = :newScore WHERE p.id = :playerId")
    int updatePlayerScoreById(long playerId, long newScore);
}
