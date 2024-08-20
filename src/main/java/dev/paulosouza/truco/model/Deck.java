package dev.paulosouza.truco.model;

import lombok.Data;

import java.util.Deque;
import java.util.UUID;

@Data
public class Deck {

    private UUID id;

    private Deque<Card> cards;

}
