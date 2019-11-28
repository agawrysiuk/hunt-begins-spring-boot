package com.agawrysiuk.huntbeginsspringboot.model;

import java.util.List;

public interface GameMap {
    FloorTile[][] getGameMap();

    List<FloorTile> getFillerList();

    boolean addFloorTile(FloorTile floorTile);

    boolean isValid();

    boolean isFinished();

    void printMap();
}
