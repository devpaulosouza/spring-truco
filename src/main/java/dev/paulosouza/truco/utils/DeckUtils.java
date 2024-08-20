package dev.paulosouza.truco.utils;

import dev.paulosouza.truco.core.RandomComparator;
import dev.paulosouza.truco.model.Card;
import dev.paulosouza.truco.model.Deck;

import java.util.*;
import java.util.stream.Collectors;

public class DeckUtils {

    private DeckUtils() {

    }

    public static Deck create() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();

        for (short i = 0; i < 10; i++) {
            for (short j = 0; j < 4; j++) {
                cards.add(new Card(i, j));
            }
        }

        deck.setCards(DeckUtils.shuffle(cards));
        deck.setId(UUID.randomUUID());

        return deck;
    }

    public static Card pick(Deck deck) {
        return deck.getCards().removeFirst();
    }

    private static Deque<Card> shuffle(List<Card> deck) {
        return deck
                .stream()
                .sorted(new RandomComparator<>())
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

}
