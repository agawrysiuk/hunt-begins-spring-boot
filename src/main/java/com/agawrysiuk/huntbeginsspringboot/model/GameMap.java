package com.agawrysiuk.huntbeginsspringboot.model;

public interface GameMap {
    FloorTile[][] getGameMap();

    boolean addFloorTile(FloorTile floorTile);

    boolean isValid();

    boolean isFinished();
}
