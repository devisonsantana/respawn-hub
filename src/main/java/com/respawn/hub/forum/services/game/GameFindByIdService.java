package com.respawn.hub.forum.services.game;

import com.respawn.hub.forum.exceptions.NotFoundException;
import com.respawn.hub.forum.models.records.game.GameFindCompleteResponse;
import com.respawn.hub.forum.repositories.GameForumRepository;
import org.springframework.stereotype.Service;

@Service
public class GameFindByIdService {

    private final GameForumRepository repository;

    public GameFindByIdService(final GameForumRepository repository) {
        this.repository = repository;
    }

    public GameFindCompleteResponse apply(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("Informação inválida para realizar a consulta");
        }

        final var result = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi possível encontrar o jogo com o ID especificado."));

        return GameFindCompleteResponse.parse(result);
    }

}
