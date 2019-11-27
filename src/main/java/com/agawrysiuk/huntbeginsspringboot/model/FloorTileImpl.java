package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FloorTileImpl implements FloorTile {

    @Getter
    private final int id;
    @Getter
    private final String name;
    @Getter
    private int[] exits;
    @Getter
    private double rotate;
    @Getter
    private Coordinates coordinates;

    public FloorTileImpl(int id, String name) {
        this.id = id;
        this.name = name;
        this.rotate = 0;
        this.exits = new int[4];
    }

    @Override
    public void setExits(int... exitsToSet) {
        if (exitsToSet.length != 4) {
            log.info("Incorrect number of exits: {}", exitsToSet);
            return;
        }
        for (int i = 0; i < exits.length; i++) {
            exits[i] = exitsToSet[i];
        }
    }

    @Override
    public void setOneExit(int position, int exit) {
        exits[position] = exit;
    }

    @Override
    public void setCoordinates(int x, int y) {
        coordinates = new CoordinatesImpl(x, y);
    }

    @Override
    public void rotate() {
        log.info("Before rotation = {}", Arrays.toString(exits));
        int temp = exits[3];
        for (int i = 3; i > 0; i--) {
            if (i == 3 || i == 1) {
                exits[i] = -exits[i - 1];
            } else {
                exits[i] = exits[i - 1];
            }
        }
        exits[0] = temp;
        log.info("After rotation = {}", Arrays.toString(exits));
        rotate = (rotate + 90) % 360;
    }
}
