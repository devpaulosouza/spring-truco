package dev.paulosouza.truco.core;

import dev.paulosouza.truco.exception.TrucoExcpetion;
import dev.paulosouza.truco.model.Player;
import dev.paulosouza.truco.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {

    private final DeckService deckService;

    private static final List<Room> rooms = new ArrayList<>();

    public Room init() {
        Room room = new Room();

        room.setId(UUID.randomUUID());
        room.setDeck(this.deckService.create());
        room.setPlayers(new ArrayList<>());

        GameService.rooms.add(room);

        return room;
    }

    public Player join(UUID roomId, Player request) {
        Room room = GameService.rooms
                .stream()
                .filter(r -> r.getId().equals(roomId))
                .findFirst()
                .orElseThrow();

        if (!this.acceptingNewPlayers(room)) {
            throw new TrucoExcpetion("Room is full of players");
        }

        Player player = new Player();

        BeanUtils.copyProperties(request, player);

        player.setId(UUID.randomUUID());

        return player;
    }

    private synchronized boolean acceptingNewPlayers(Room room) {
        return room.getPlayers().size() < 4;
    }

}
