package com.respawn.hub.forum.services.game;

import com.respawn.hub.forum.exceptions.DatabaseErrorException;
import com.respawn.hub.forum.models.records.game.GameRegisterRequest;
import com.respawn.hub.forum.models.records.game.GameRegisterResponse;
import com.respawn.hub.forum.repositories.GameForumRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GameRegisterService {

     private final GameForumRepository repository;

    public GameRegisterService(final GameForumRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public GameRegisterResponse apply(GameRegisterRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Não é possivel realizar a operação com informações inválidas");
        }

        final var saved = repository.save(request.parseToEntity());
        if (saved == null) {
            throw new DatabaseErrorException("Não foi possível registrar o jogo");
        }

        return GameRegisterResponse.parse(saved);
    }

}