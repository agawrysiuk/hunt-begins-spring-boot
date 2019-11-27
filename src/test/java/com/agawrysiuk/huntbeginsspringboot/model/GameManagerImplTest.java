package com.agawrysiuk.huntbeginsspringboot.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameManagerImplTest {

    @Test
    void loadTiles_LoadsTilesToDatabase() throws IOException {
        GameManager gameManager = new GameManagerImpl();
        assertNotEquals(0,gameManager.loadTiles().getTiles().size());
    }
}