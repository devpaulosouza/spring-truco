package dev.paulosouza.truco.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Deck {

    private UUID id;

    private List<Card> cards;

}
