package dev.paulosouza.truco.model;

import lombok.Data;

import java.util.List;

@Data
public class Room {

    private List<Player> players;

    private Deck deck;

}
