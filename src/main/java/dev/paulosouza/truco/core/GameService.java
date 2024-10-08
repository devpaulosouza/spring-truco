package dev.paulosouza.truco.core;

import dev.paulosouza.truco.exception.TrucoExcpetion;
import dev.paulosouza.truco.model.Player;
import dev.paulosouza.truco.model.Room;
import dev.paulosouza.truco.utils.DeckUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private static final List<Room> rooms = new ArrayList<>();

    public Room init() {
        Room room = new Room();

        room.setId(UUID.randomUUID());
        room.setDeck(DeckUtils.create());
        room.setPlayers(new ArrayList<>());

        GameService.rooms.add(room);

        return room;
    }

    public synchronized Player join(UUID roomId, Player request) {
        Room room = getRoom(roomId);

        if (!this.isAcceptingNewPlayers(room)) {
            throw new TrucoExcpetion("Room is full of players");
        }
        if (room.isGameRunning()) {
            throw new TrucoExcpetion("Game is already running");
        }

        Player player = new Player();

        BeanUtils.copyProperties(request, player);

        player.setId(UUID.randomUUID());
        player.setIndex(room.getPlayers().size());

        room.getPlayers().add(player);

        return player;
    }

    public void start(UUID roomId) {
        Room room = this.getRoom(roomId);

        if (!this.isPlayersCountValid(room)) {
            throw new TrucoExcpetion("It is only possible to start with 2 or 4 players");
        }

        if (room.isGameRunning()) {
            throw new TrucoExcpetion("Game is already running");
        }

        room.setGameRunning(true);
        room.setRound((short) (room.getRound() + 1));
        room.setStartPlayer((short) new SecureRandom().nextInt(0, room.getPlayers().size()));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < room.getPlayers().size(); j++) {
                room.getPlayers().get(j).getCards().add(DeckUtils.pick(room.getDeck()));
            }
        }

    }

    public Room getRoom(UUID roomId) {
        return GameService.rooms
                .stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst()
                .orElseThrow();
    }

    private boolean isAcceptingNewPlayers(Room room) {
        return room.getPlayers().size() < 4;
    }

    private boolean isPlayersCountValid(Room room) {
        return room.getPlayers().size() == 2 || room.getPlayers().size() == 4;
    }

}
