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
    private Exit[] exits;
    @Getter
    private double rotate;
    @Getter
    private Coordinates coordinates;

    public FloorTileImpl(int id, String name) {
        this.id = id;
        this.name = name;
        this.rotate = 0;
        this.exits = new Exit[4];
    }

    @Override
    public void setExits(int... exitsToSet) {
        if (exitsToSet.length != 4) {
            log.info("Incorrect number of exits: {}", exitsToSet);
            return;
        }
        for (int i = 0; i < exits.length; i++) {
            if (exitsToSet[i] != 0) {
                exits[i] = Exit.values()[i];
            }
        }
    }

    @Override
    public void setOneExit(int position) {
        switch (position) {
            case 0:
                exits[position] = Exit.TOP_EXIT;
                break;
            case 1:
                exits[position] = Exit.RIGHT_EXIT;
                break;
            case 2:
                exits[position] = Exit.BOT_EXIT;
                break;
            case 3:
                exits[position] = Exit.LEFT_EXIT;
                break;
        }
    }

    @Override
    public void setCoordinates(int x, int y) {
        coordinates = new CoordinatesImpl(x, y);
    }

    @Override
    public void rotate() {
        log.info("Before rotation = {}", Arrays.toString(exits));
        Exit temp = exits[3];
        for (int i = 3; i > 0; i--) {
            exits[i] = exits[i - 1];
        }
        exits[0] = temp;
        for (int i = 0; i < 4; i++) {
            if (exits[i] != null) {
                setOneExit(i);
            }
        }
        log.info("After rotation = {}", Arrays.toString(exits));
        rotate = (rotate + 90) % 360;
    }
}
