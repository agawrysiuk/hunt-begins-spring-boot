package com.agawrysiuk.huntbeginsspringboot.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class GameMapImplTest {

    private GameManager gameManager = new GameManagerImpl();

    @Test
    void addFloorTile() throws IOException {
        gameManager.loadTiles();
        gameManager.createMap();
    }
}