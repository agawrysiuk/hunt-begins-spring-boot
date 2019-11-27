package com.agawrysiuk.huntbeginsspringboot.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.agawrysiuk.huntbeginsspringboot.model.Exit.*;

class FloorTileImplTest {

    @Test
    void rotate_NewArrayIsCorrect() {
        FloorTile floorTile = new FloorTileImpl(1, "test");
        floorTile.setExits(1, 0, 1, 1);
        floorTile.rotate();
        Assertions.assertArrayEquals(
                new Exit[]{TOP_EXIT, RIGHT_EXIT, null, LEFT_EXIT},
                floorTile.getExits()
        );
    }
}