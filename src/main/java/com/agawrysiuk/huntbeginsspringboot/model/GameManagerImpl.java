package com.agawrysiuk.huntbeginsspringboot.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameManagerImpl implements GameManager {

    private Map<Integer,FloorTile> tiles;

    public GameManagerImpl() {
        this.tiles = new HashMap<>();
    }

    /*    14x Straight Corridors    id 1-14
      8x  L-Shape Tiles             id 15-22
      8x  T-Shape Tiles             id 23-30
      12x Crossroads                id 31-42
      12x Dead Ends                 id 43-54
      12x Air Vent Tiles
      5   Rooms (Escape Pod / Laboratory,
            Armoury, Bridge, Hibernation Room,
            Engine Room)            id 55-59
      12  Door Tiles*/
    @Override
    public GameManager loadTiles()  throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("/static/game/tiles.txt"));
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            String[] array = currentLine.split(",");
            FloorTile floorTile = new FloorTileImpl(Integer.parseInt(array[0]), array[1]);
            floorTile.setExits(
                    Arrays.stream(array[2].split(""))
                            .mapToInt(Integer::parseInt)
                            .toArray());
            tiles.put(floorTile.getId(), floorTile);
        }
        return this;
    }

    @Override
    public GameMap createMap() {
        return null;
    }
}
