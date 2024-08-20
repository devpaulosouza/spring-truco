package dev.paulosouza.truco.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Player {

    private UUID id;

    private String name;

    private int index;

    private List<Card> cards = new ArrayList<>();

}
