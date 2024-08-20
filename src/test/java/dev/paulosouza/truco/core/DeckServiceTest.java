package dev.paulosouza.truco.core;

import dev.paulosouza.truco.model.Card;
import dev.paulosouza.truco.model.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeckServiceTest {

    @InjectMocks
    private DeckService service;

    @Test
    void create() {
        // given

        // when
        Deck deck = this.service.create();

        // then
        Assertions.assertNotNull(deck);
        Assertions.assertNotNull(deck.getCards());
        Assertions.assertEquals(40, deck.getCards().size());
        Assertions.assertEquals(10, deck.getCards().stream().map(Card::getIndex).distinct().count());
        Assertions.assertEquals(4, deck.getCards().stream().map(Card::getSuit).distinct().count());

    }

}