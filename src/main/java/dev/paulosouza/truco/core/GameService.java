package dev.paulosouza.truco.core;

import dev.paulosouza.truco.model.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

    private final DeckService deckService;

    public Room init() {
        Room room = new Room();

        room.setDeck(this.deckService.create());

        return room;
    }

}
