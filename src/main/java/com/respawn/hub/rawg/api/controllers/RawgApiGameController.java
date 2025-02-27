package com.respawn.hub.rawg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.respawn.hub.rawg.api.models.Game;
import com.respawn.hub.rawg.api.records.games.RawgGameDTO;
import com.respawn.hub.rawg.api.records.games.RawgResponseGameEndpoint;
import com.respawn.hub.rawg.api.services.RawgGameService;

@RestController
@RequestMapping("/api/rawg")
public class RawgApiGameController {
    @Autowired
    private RawgGameService gameService;

    // Controller para testes
    @GetMapping("/response")
    public RawgResponseGameEndpoint getGames() {
        return gameService.apiResponse();
    }

    // Controller para testes
    @GetMapping("/list/dto")
    public List<RawgGameDTO> getGamesList() {
        return gameService.listGameDto();
    }

    // Esse controller retorna Game com todas as informações necessarias
    // já prontas pra enviar pro banco de dados, caso queira adicionar mais campos
    // não se esqueça de adicionar nos MODELS e nos RECORDS
    @GetMapping("/game/entity")
    public ResponseEntity<List<Game>> getGameEntity() {
        var body = gameService.getGames();
        return ResponseEntity.ok(body);
    }
}
