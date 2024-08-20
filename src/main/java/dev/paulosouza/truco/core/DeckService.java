package dev.paulosouza.truco.core;

import dev.paulosouza.truco.model.Card;
import dev.paulosouza.truco.model.Deck;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeckService {

    public Deck create() {
        Deck deck = new Deck();
        List<Card> cards = new ArrayList<>();

        for (short i = 0; i < 10; i++) {
            for (short j = 0; j < 4; j++) {
                cards.add(new Card(i, j));
            }
        }

        deck.setCards(this.shuffle(cards));
        deck.setId(UUID.randomUUID());

        return deck;
    }

    private List<Card> shuffle(List<Card> deck) {
        return deck
                .stream()
                .sorted(new RandomComparator<>())
                .collect(Collectors.toList());
    }

}
