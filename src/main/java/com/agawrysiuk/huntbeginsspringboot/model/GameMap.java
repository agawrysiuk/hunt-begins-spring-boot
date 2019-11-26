package com.agawrysiuk.huntbeginsspringboot.model;

import java.util.Map;

public interface GameMap {
    Map<Integer,FloorTile> getGameMap();
    GameMap addFloorTile(FloorTile floorTile);
}
