package com.respawn.hub.rawg.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.respawn.hub.rawg.api.records.RawgGameDTO;
import com.respawn.hub.rawg.api.records.RawgResponseRawgAPI;
import com.respawn.hub.rawg.api.services.RawgGameService;

@RestController
@RequestMapping("/api/rawgw")
public class RawgApiGameController {
    @Autowired
    private RawgGameService gameService;

    @GetMapping("/games/response")
    public RawgResponseRawgAPI getGames() {
        return gameService.getGames();
    }

    @GetMapping("/games")
    public List<RawgGameDTO> getGamesList() {
        return null;
    }
}
