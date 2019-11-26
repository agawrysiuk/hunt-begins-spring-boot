package com.agawrysiuk.huntbeginsspringboot.model;

import java.io.IOException;

public interface GameManager {

    GameManager loadTiles() throws IOException;

    GameMap createMap();
}
