package com.respawn.hub.forum.services.game;

import com.respawn.hub.forum.exceptions.NotFoundException;
import com.respawn.hub.forum.models.entities.GameForum;
import com.respawn.hub.forum.models.records.game.GameFindCompleteResponse;
import com.respawn.hub.forum.repositories.GameForumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class GameFindByIdServiceTest {

    @Mock
    private GameForumRepository repository;

    @InjectMocks
    private GameFindByIdService service;

    private Long idSuccessFound;
    private Long idNotFound;
    private Long idNull;
    private Long idNegative;
    private GameForum entityFounded;
    private GameFindCompleteResponse responseFounded;

    @BeforeEach
    void setUp() throws Exception {
        this.idSuccessFound = 1L;

        this.entityFounded = new GameForum(
                this.idSuccessFound,
                "RDR2",
                "Red Dead Redemption 2",
                "Red Dead Redemption 2 é um jogo eletrônico de ação-aventura desenvolvido e publicado pela Rockstar Games. É o terceiro título da série Red Dead e uma prequela de Red Dead Redemption, tendo sido lançado em outubro de 2018 para PlayStation 4 e Xbox One e em novembro de 2019 para Microsoft Windows e Google Stadia.",
                LocalDate.of(2018, 10, 26),
                "https://www.godisageek.com/wp-content/uploads/Red-Dead-Redemption-2-PC-review-1024x576.jpg",
                "https://www.rockstargames.com/br/reddeadredemption2"
        );

        this.responseFounded = new GameFindCompleteResponse(
                this.entityFounded.getId(),
                this.entityFounded.getName(),
                this.entityFounded.getOriginalName(),
                this.entityFounded.getDescription(),
                this.entityFounded.getLaunch(),
                this.entityFounded.getBackgroundImageUrl(),
                this.entityFounded.getWebsiteUrl()
        );

        this.idNotFound = 10000L;

        this.idNull = null;

        this.idNegative = -1L;
    }

    @Test
    @DisplayName("Find By Id Should Return Founded Response When Successfully Found")
    void findById_shouldReturnFoundedResponse_whenSuccessfullyFound() {
        when(repository.findById(idSuccessFound)).thenReturn(Optional.of(entityFounded));

        final var result = service.apply(idSuccessFound);

        assertTrue(result instanceof GameFindCompleteResponse);
        assertEquals(responseFounded.id(), result.id());
        assertEquals(responseFounded.name(), result.name());
        assertEquals(responseFounded.originalName(), result.originalName());
        assertEquals(responseFounded.description(), result.description());
        assertEquals(responseFounded.launch(), result.launch());
        assertEquals(responseFounded.backgroundImageUrl(), result.backgroundImageUrl());
        assertEquals(responseFounded.websiteUrl(), result.websiteUrl());
    }

    @Test
    @DisplayName("Find By Id Should Return Illegal Argument Exception When Requested Id Is Null")
    void findById_shouldReturnIllegalArgumentException_whenRequestedIdIsNull() {
        final var messageExpected = "Informação inválida para realizar a consulta";

        final var result = assertThrows(IllegalArgumentException.class, () ->
                service.apply(idNull)
        );

        assertEquals(messageExpected, result.getMessage());
    }

    @Test
    @DisplayName("Find By Id Should Return Illegal Argument Exception When Requested Id Is Negative")
    void findById_shouldReturnIllegalArgumentException_whenRequestedIdIsNegative() {
        final var messageExpected = "Informação inválida para realizar a consulta";

        final var result = assertThrows(IllegalArgumentException.class, () ->
                service.apply(idNegative)
        );

        assertEquals(messageExpected, result.getMessage());
    }

    @Test
    @DisplayName("Find By Id Should Return Not Found Exception When The Game Is Not Found For The Requested Id")
    void findById_shouldReturnNotFoundException_whenTheGameIsNotFoundForTheRequestedId() {
        final var messageExpected = "Não foi possível encontrar o jogo com o ID especificado.";

        when(repository.findById(idNotFound)).thenReturn(Optional.empty());

        final var result = assertThrows(NotFoundException.class, () ->
                service.apply(idNotFound)
        );

        verify(repository, times(1)).findById(any());

        assertEquals(messageExpected, result.getMessage());
    }

}