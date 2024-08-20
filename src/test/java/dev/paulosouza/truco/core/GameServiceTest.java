package dev.paulosouza.truco.core;

import dev.paulosouza.truco.exception.TrucoExcpetion;
import dev.paulosouza.truco.model.Player;
import dev.paulosouza.truco.model.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService service;

    @Test
    void init() {
        // given
        // when
        Room room = this.service.init();

        // then
        Assertions.assertNotNull(room);
        Assertions.assertNotNull(room.getDeck());
        Assertions.assertEquals(40, room.getDeck().getCards().size());
    }

    @Test
    void joinSuccessfully() {
        // given

        Room room = this.service.init();
        UUID roomId = room.getId();

        List<Player> response = new ArrayList<>();

        // when
        response.add(this.service.join(roomId, createPlayer()));
        response.add(this.service.join(roomId, createPlayer()));
        response.add(this.service.join(roomId, createPlayer()));
        response.add(this.service.join(roomId, createPlayer()));

        // then
        Assertions.assertEquals(4, response.size());
        response.stream().map(Player::getId).forEach(Assertions::assertNotNull);
    }

    @Test
    void joinRoomFullOfPlayersException() {
        // given
        Room room = this.service.init();
        UUID roomId = room.getId();

        List<Player> response = new ArrayList<>();

        response.add(this.service.join(roomId, createPlayer()));
        response.add(this.service.join(roomId, createPlayer()));
        response.add(this.service.join(roomId, createPlayer()));
        response.add(this.service.join(roomId, createPlayer()));

        // when

        Player excedingPlayer = createPlayer();

        TrucoExcpetion exception = Assertions.assertThrows(TrucoExcpetion.class, () -> this.service.join(roomId, excedingPlayer));

        // then
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(4, response.size());
    }

    @Test
    void joinWhenGameIsRunningException() {
        // given
        Room room = this.service.init();
        UUID roomId = room.getId();

        List<Player> response = new ArrayList<>();

        response.add(this.service.join(roomId, createPlayer()));
        response.add(this.service.join(roomId, createPlayer()));

        this.service.start(roomId);

        // when
        Player excedingPlayer = createPlayer();

        TrucoExcpetion exception = Assertions.assertThrows(TrucoExcpetion.class, () -> this.service.join(roomId, excedingPlayer));

        // then
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(2, response.size());
    }

    @Test
    void startSuccessfully() {
        // given
        Room room = this.service.init();
        UUID roomId = room.getId();

        this.service.join(roomId, createPlayer());
        this.service.join(roomId, createPlayer());

        // when
        this.service.start(roomId);

        // then
        Assertions.assertTrue(room.isGameRunning());
        Assertions.assertEquals(34, room.getDeck().getCards().size());

        for (Player player : room.getPlayers()) {
            Assertions.assertEquals(3, player.getCards().size());
        }
    }

    private Player createPlayer() {
        return new Player();
    }

}