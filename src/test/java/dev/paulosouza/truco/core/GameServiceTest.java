package dev.paulosouza.truco.core;

import dev.paulosouza.truco.model.Deck;
import dev.paulosouza.truco.model.Player;
import dev.paulosouza.truco.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService service;

    @Mock
    private DeckService deckService;

    @Test
    void init() {
        // given
        when(this.deckService.create()).thenReturn(new Deck());

        // when
        Room room = this.service.init();

        // then
        Assertions.assertNotNull(room);
        verify(this.deckService, Mockito.times(1)).create();
    }

    @Test
    void joinSuccessfully() {
        // given

        Room room = this.service.init();
        UUID roomId = room.getId();

        List<Player> response = new ArrayList<>();

        // when
        response.add(this.service.join(roomId, new Player()));
        response.add(this.service.join(roomId, new Player()));
        response.add(this.service.join(roomId, new Player()));
        response.add(this.service.join(roomId, new Player()));

        // then
        Assertions.assertEquals(4, response.size());
        response.stream().map(Player::getId).forEach(Assertions::assertNotNull);
    }

}