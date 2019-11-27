package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class GameMapImpl implements GameMap {
    @Getter
    private Map<Integer,FloorTile> gameTiles;
    @Getter
    private FloorTile[][] gameMap;
    private boolean finished;

    public GameMapImpl() {
        this.gameTiles = new HashMap<>();
        this.gameMap = new FloorTile[40][40];
        this.finished = false;
    }

    @Override
    public boolean addFloorTile(FloorTile floorTile) {
        //unique tales can't be next to each other
        //change-direction tiles can't be next to each other
        //change-direction tiles can't lead to the existing tile without an open exit
        //if they lead to an open exit, it gets automatically filled
        //if the tile is one point from the border, we need to place there a dead end
        //
        //0. we start with a unique tale
        //1. if there is a unique tale, we check where there is an open exit checkForExit()
        //2. we try to add a tile to this exit
        //3. whether it happens or not, we return true or false;
        //3a. if it's true, we also connect two tales to each other
        if(gameTiles.size()==0) {
            gameTiles.put(floorTile.getId(), floorTile);
        } else {
            gameTiles.put(floorTile.getId(), floorTile);
        }
        return true;
    }

    @Override
    public boolean isValid() {
        //1. all tiles need to be connected;
        //2. tiles not connected can't be placed next to each other
        return false;
    }

    @Override
    public boolean isFinished() {
        if(finished) {
            return true;
        }
        //else check:
        //it's finished when it's valid and
        //tiles don't have open exits
        return false;
    }

    @Override
    public FloorTile checkForOpenTile() {
        return null;
    }

    //for testing purposes
    public void printMap() {
        // TODO: 2019-11-26 finish printing to console
    }
}
