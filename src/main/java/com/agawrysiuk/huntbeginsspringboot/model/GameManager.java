package com.agawrysiuk.huntbeginsspringboot.model;

import java.io.IOException;
import java.util.Map;

public interface GameManager {

    GameManager loadTiles() throws IOException;

    GameMap createMap();

    Map<Integer,FloorTile> getTiles();

    FloorTile getTile(int id);
}
