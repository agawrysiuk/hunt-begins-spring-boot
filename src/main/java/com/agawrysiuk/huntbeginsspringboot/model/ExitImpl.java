package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ExitImpl implements Exit {
    private final int exitTo;
    private final FloorTile floorTile;
    @Setter
    private boolean open;
    @Setter
    private boolean connected;

    public ExitImpl(int exitTo, FloorTile floorTile) {
        this.exitTo = exitTo;
        this.floorTile = floorTile;
    }
}
