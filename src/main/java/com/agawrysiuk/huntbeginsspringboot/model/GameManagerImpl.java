package com.agawrysiuk.huntbeginsspringboot.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManagerImpl implements GameManager {

    private List<FloorTile> tiles;

    public GameManagerImpl() {
        this.tiles = new ArrayList<>();
    }

    /*    14x Straight Corridors
      8x  L-Shape Tiles
      8x  T-Shape Tiles
      12x Crossroads
      12x Dead Ends
      12x Air Vent Tiles
      5   Rooms (Escape Pod / Laboratory,
            Armoury, Bridge, Hibernation Room,
            Engine Room)
      12  Door Tiles*/
    @Override
    public GameManager loadTiles()  throws IOException {

        return null;
    }

    @Override
    public GameMap createMap() {
        return null;
    }
}
