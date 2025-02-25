package com.respawn.hub.rawg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.respawn.hub.rawg.api.models.Game;
import com.respawn.hub.rawg.api.records.RawgGameDTO;
import com.respawn.hub.rawg.api.records.RawgResponseRawgAPI;
import com.respawn.hub.rawg.api.services.RawgGameService;

@RestController
@RequestMapping("/api/rawg")
public class RawgApiGameController {
    @Autowired
    private RawgGameService gameService;

    @GetMapping("/games/response")
    public RawgResponseRawgAPI getGames() {
        return gameService.getGames();
    }

    @GetMapping("/games/list")
    public List<RawgGameDTO> getGamesList() {
        return gameService.listGameDto();
    }

    @GetMapping("/games")
    public List<Game> findGames(@RequestParam(value = "list", required = true) String value) {
        if (value.equals("all")) {
            return gameService.findGames();
        }
        return null;
    }
}
