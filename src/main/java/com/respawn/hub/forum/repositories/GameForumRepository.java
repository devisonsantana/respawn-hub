package com.respawn.hub.forum.repositories;

import com.respawn.hub.forum.models.entities.GameForum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameForumRepository extends JpaRepository<GameForum, Long> {
}
