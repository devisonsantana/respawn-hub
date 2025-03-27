package com.respawn.hub.forum.controllers.game;

import com.respawn.hub.forum.models.records.game.GameFindCompleteResponse;
import com.respawn.hub.forum.models.records.game.GameRegisterRequest;
import com.respawn.hub.forum.models.records.game.GameRegisterResponse;
import com.respawn.hub.forum.services.game.GameFindByIdService;
import com.respawn.hub.forum.services.game.GameRegisterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameRegisterService registerService;
    private final GameFindByIdService findByIdService;

    public GameController(final GameRegisterService registerService,
                          final GameFindByIdService findByIdService) {
        this.registerService = registerService;
        this.findByIdService = findByIdService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameFindCompleteResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(findByIdService.apply(id));
    }

    @PostMapping
    public ResponseEntity<GameRegisterResponse> register(@RequestBody @Valid GameRegisterRequest request) {
        final var result = registerService.apply(request);
        final var uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/games/{id}")
                .buildAndExpand(result.id())
                .toUri();

        return ResponseEntity.created(uri).body(result);
    }

}
