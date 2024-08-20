package dev.paulosouza.truco.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Room {

    private UUID id;

    private List<Player> players;

    private Deck deck;

    private boolean isGameRunning;

}
