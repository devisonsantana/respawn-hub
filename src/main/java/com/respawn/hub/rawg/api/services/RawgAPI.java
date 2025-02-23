package com.respawn.hub.rawg.api.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.respawn.hub.rawg.api.interfaces.IRawgMethods;

@Component
public class RawgAPI implements IRawgMethods {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getJson(String endpoint) {
        URI uri = URI.create(endpoint);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        String json = response.body();
        return json;
    }

    @Override
    public <T> T toDTO(String json, Class<T> classType) {
        try {
            return mapper.readValue(json, classType);
        } catch (JsonMappingException ex) {
            throw new RuntimeException(ex);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

}
