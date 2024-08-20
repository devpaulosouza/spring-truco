package dev.paulosouza.truco.utils;

import dev.paulosouza.truco.model.Card;
import dev.paulosouza.truco.model.Deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeckUtilsTest {

    @Test
    void create() {
        // given

        // when
        Deck deck = DeckUtils.create();

        // then
        Assertions.assertNotNull(deck);
        Assertions.assertNotNull(deck.getCards());
        Assertions.assertEquals(40, deck.getCards().size());
        Assertions.assertEquals(10, deck.getCards().stream().map(Card::getIndex).distinct().count());
        Assertions.assertEquals(4, deck.getCards().stream().map(Card::getSuit).distinct().count());

    }

}