package com.agawrysiuk.huntbeginsspringboot.model;

import java.util.Map;

public interface GameMap {
    Map<Integer, FloorTile> getGameTiles();

    FloorTile[][] getGameMap();

    boolean addFloorTile(FloorTile floorTile);

    boolean isValid();

    boolean isFinished();

    FloorTile findFirstOpen();
}
