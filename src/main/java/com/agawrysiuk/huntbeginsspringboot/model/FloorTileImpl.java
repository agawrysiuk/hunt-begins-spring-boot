package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class FloorTileImpl implements FloorTile {

    private boolean unique;
    private ExitAvP[] exits;
    private String name;

    public FloorTileImpl(String name) {
        this.name = name;
        this.unique = !name.toLowerCase().contains("regular");
        this.exits = new ExitAvP[4];
    }

    @Override
    public boolean isUnique() {
        return unique;
    }

    @Override
    public void setExits(boolean... exitsToSet) {
        if (exitsToSet.length != 4) {
            log.info("Incorrect number of exits: {}", exitsToSet);
            return;
        }
        for (int i = 0; i < exitsToSet.length; i++) {
            exits[i] = new ExitAvpImpl(exitsToSet[i]);
        }
    }

    @Override
    public ExitAvP[] getExits() {
        return exits;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void rotate() {
        log.info("Before rotation = {}", Arrays.toString(exits));
        ExitAvP temp = exits[3];
        for (int i = 0; i < 3; i++) {
            exits[i + 1] = exits[i];
        }
        exits[0] = temp;
        log.info("After rotation = {}", Arrays.toString(exits));
    }
}
