package com.agawrysiuk.huntbeginsspringboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FloorTileImplTest {

    @Test
    void rotate() {
        FloorTile floorTile = new FloorTileImpl(1, "test");
        floorTile.setExits(-1, 0, 1, -1);
        floorTile.rotate();
        Assertions.assertArrayEquals(new int[]{-1, 1, 0, -1}, floorTile.getExits());
    }
}