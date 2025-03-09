package com.respawn.hub.forum.services.game;

import com.respawn.hub.forum.exceptions.DatabaseErrorException;
import com.respawn.hub.forum.models.entities.GameForum;
import com.respawn.hub.forum.models.records.game.GameRegisterRequest;
import com.respawn.hub.forum.models.records.game.GameRegisterResponse;
import com.respawn.hub.forum.repositories.GameForumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GameRegisterServiceTest {

    @Mock
    private GameForumRepository repository;

    @InjectMocks
    private GameRegisterService service;

    // Variaveis para quando operação bem sucedida
    private GameRegisterRequest successRequest;
    private GameForum expectedSuccessParseToEntity;
    private Long expectedSuccessId;
    private GameRegisterResponse expectedSuccessResponse;
    private GameForum expectedSuccessSavedEntity;

    // Illegal Argument Exception para requisição nula
    private GameRegisterRequest nullRequest;

    // Database Error Exception
    private GameRegisterRequest errorRegisterRequest;
    private GameForum errorRegisterParseToEntity;

    @BeforeEach
    void setUp() throws Exception {
        // Requisição de registro esperado como bem sucedida ao registrar um jogo
        this.successRequest = new GameRegisterRequest(
                "RDR2",
                "Red Dead Redemption 2",
                "Red Dead Redemption 2 é um jogo eletrônico de ação-aventura desenvolvido e publicado pela Rockstar Games. É o terceiro título da série Red Dead e uma prequela de Red Dead Redemption, tendo sido lançado em outubro de 2018 para PlayStation 4 e Xbox One e em novembro de 2019 para Microsoft Windows e Google Stadia.",
                LocalDate.of(2018, 10, 26),
                "https://www.godisageek.com/wp-content/uploads/Red-Dead-Redemption-2-PC-review-1024x576.jpg",
                "https://www.rockstargames.com/br/reddeadredemption2"
        );

        // Entidade esperada para
        this.expectedSuccessParseToEntity = new GameForum(
                this.successRequest.name(),
                this.successRequest.originalName(),
                this.successRequest.description(),
                this.successRequest.launch(),
                this.successRequest.backgroundImageUrl(),
                this.successRequest.websiteUrl()
        );

        // Identificação esperado ao registrar jogo quando bem sucedido registro
        this.expectedSuccessId = 1L;

        // Game salvo na base de dados esperado
        this.expectedSuccessSavedEntity = new GameForum(
                this.expectedSuccessId,
                this.successRequest.name(),
                this.successRequest.originalName(),
                this.successRequest.description(),
                this.successRequest.launch(),
                this.successRequest.backgroundImageUrl(),
                this.successRequest.websiteUrl()
        );

        // Resposta de registro esperado quando bem sucedido o registro do jogo
        this.expectedSuccessResponse = new GameRegisterResponse(
                expectedSuccessId,
                this.successRequest.name(),
                this.successRequest.originalName(),
                this.successRequest.description(),
                this.successRequest.launch(),
                this.successRequest.backgroundImageUrl(),
                this.successRequest.websiteUrl()
        );

        // Requisição nula para esperar um IllegalArgumentException no service
        this.nullRequest = null;

        // Requisição para quando houver erro ao registrar Game
        this.errorRegisterRequest = this.successRequest;

        // Entidade convertida para quando houver erro ao registrar Game
        this.errorRegisterParseToEntity = this.expectedSuccessParseToEntity;
    }

    @Test
    @DisplayName("Save Game Should Save In Data Base And Return Response When Successful")
    void saveGame_shouldSaveInDataBaseAndReturnResponse_whenSuccessful() {
        when(repository.save(expectedSuccessParseToEntity)).thenReturn(expectedSuccessSavedEntity);

        final var result = service.apply(successRequest);

        verify(repository, times(1)).save(any());

        assertTrue(result instanceof GameRegisterResponse);
        assertEquals(expectedSuccessResponse.id(), result.id());
        assertEquals(expectedSuccessResponse.name(), result.name());
        assertEquals(expectedSuccessResponse.description(), result.description());
        assertEquals(expectedSuccessResponse.launch(), result.launch());
        assertEquals(expectedSuccessResponse.backgroundImageUrl(), result.backgroundImageUrl());
        assertEquals(expectedSuccessResponse.websiteUrl(), result.websiteUrl());
    }

    @Test
    @DisplayName("Save Game Should Return Illegal Argument Exception When Request Is Null")
    void saveGame_shouldReturnIllegalArgumentException_whenRequestIsNull() {
        final var messageExpected = "Não é possivel realizar a operação com informações inválidas";

        final var result = assertThrows(IllegalArgumentException.class, () ->
            service.apply(nullRequest)
        );

        assertEquals(messageExpected, result.getMessage());
    }

    @Test
    @DisplayName("Save Game Should Return Database Error Exception When Is Not Possible To Save")
    void saveGame_shouldReturnDatabaseErrorException_whenIsNotPossibleToSave() {
        final var messageExpected = "Não foi possível registrar o jogo";

        when(repository.save(errorRegisterParseToEntity)).thenReturn(null);

        final var result = assertThrows(DatabaseErrorException.class, () ->
            service.apply(errorRegisterRequest)
        );

        verify(repository, times(1)).save(any());

        assertEquals(messageExpected, result.getMessage());
    }

}