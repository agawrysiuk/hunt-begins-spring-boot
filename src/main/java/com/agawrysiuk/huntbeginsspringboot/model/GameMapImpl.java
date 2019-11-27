package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
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
        log.info("floorTile = {}",floorTile);
        if(gameTiles.size()==0) {
            log.info("Adding floorTile as an opening tile.");
            gameTiles.put(floorTile.getId(), floorTile);
            int x = 0;
            int y = 20;
            gameMap[x][y] = floorTile;
            floorTile.setCoordinates(x,y);
        } else {
            log.info("Trying to find an open tile.");
            FloorTile openTile = findFirstOpen();
            if (openTile == null) {
                log.info("No open tile found. Map is completed.");
                return false;
            }
            log.info("Open tile found. openTile = {}",openTile);
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
    public FloorTile findFirstOpen() {
        for (Map.Entry<Integer,FloorTile> tile : gameTiles.entrySet()) {
            int[] exits = tile.getValue().getExits();
            for (int i = 0; i < exits.length; i++) {
                if (exits[i]!=0) {
                    return tile.getValue();
                }
            }
        }
        finished = true;
        return null;
    }

    //for testing purposes
    public void printMap() {
        // TODO: 2019-11-26 finish printing to console
    }
}
