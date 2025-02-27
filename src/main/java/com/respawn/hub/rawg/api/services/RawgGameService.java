package com.respawn.hub.rawg.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.respawn.hub.rawg.api.models.Game;
import com.respawn.hub.rawg.api.records.games.RawgGameDTO;
import com.respawn.hub.rawg.api.records.games.RawgGameDetailDTO;
import com.respawn.hub.rawg.api.records.games.RawgResponseGameEndpoint;

@Service
public class RawgGameService {
    @Value("${rawg.endpoint}")
    private String endpoint;
    @Value("${rawg.path}")
    private String path;
    @Value("${rawg.query}")
    private String query;
    @Value("${rawg.apikey}")
    private String apiKey;
    @Autowired
    private RawgAPI rawg;

    // METODOS DE TESTE NÃO PERMANENTES
    public RawgResponseGameEndpoint apiResponse() {
        var url = endpoint + path + apiKey;
        String json = rawg.getJson(url);
        var data = rawg.toDTO(json, RawgResponseGameEndpoint.class);
        return data;
    }

    // METODOS DE TESTE NÃO PERMANENTES
    public List<RawgGameDTO> listGameDto() {
        var data = apiResponse();
        return data.games().stream().collect(Collectors.toList());
    }

    public List<Game> getGames() {
        // Declarando os parametros para as requisições
        apiKey = "?key=";
        path = "/games";

        // Demostração de url para facilitar o entendimento
        // https://api.rawg.io/api/games?key=
        var url = endpoint + path + apiKey;

        // A partir desse JSON pegamos o ID dos jogos e fazemos uma requisição detalhada
        var json = rawg.getJson(url);
        var dataGames = rawg.toDTO(json, RawgResponseGameEndpoint.class);

        // IMPORTANTE! Variavel guarda o link da proxima pagina
        // var next = dataGames.next();

        var result = dataGames.games().stream().map(g -> {
            // Demostração de url
            // https://api.rawg.io/api/games/3498?key=
            var urlGameId = String.format("%s%s/%d%s", endpoint, path, g.id(), apiKey);

            // Execução das novas requisições detalhadas dos jogos
            var gameDetailJson = rawg.getJson(urlGameId);
            var gameDetailDto = rawg.toDTO(gameDetailJson, RawgGameDetailDTO.class);

            // Cria nova instancia de Game, Verifique o construtor em Models.
            var game = new Game(gameDetailDto);
            return game;
        }).toList();
        return result;
    }
}
