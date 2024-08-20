package dev.paulosouza.truco.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Player {

    private UUID id;

    private String name;

    private int index;

    private Hand hand;

}
