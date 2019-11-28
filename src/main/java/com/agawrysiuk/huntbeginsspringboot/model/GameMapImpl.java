package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GameMapImpl implements GameMap {
    @Getter
    private FloorTile[][] gameMap;
    @Getter
    private List<FloorTile> fillerList;
    private boolean finished;

    public GameMapImpl() {
        this.gameMap = new FloorTile[40][40];
        this.finished = false;
        this.fillerList = new ArrayList<>();
    }

    @Override
    public boolean addFloorTile(FloorTile floorTile) {
        //0. we start with a unique tale
        //1. if there is a unique tale, we check where there is a filler tile on the board
        //2. we try to add a tile to this exit
        //3. whether it happens or not, we return true or false;
        //3a. if it's true, we also connect two tales to each other
        log.info("floorTile = {}", floorTile);
        if (fillerList.size() == 0 && !finished) {
            //here is a code for the opening tile
            log.info("Adding floorTile as the opening tile.");
            int x = 0;
            int y = 20;

            gameMap[x][y] = floorTile;
            floorTile.setCoordinates(x, y);

            addFillerTiles(floorTile);
            return true;
        } else {
            //here is the code for the rest of the mapping
            log.info("Trying to find a filler tile.");
            if (fillerList.size() == 0) {
                //there is no more tiles with open exits
                log.info("No filler tile found. Map is completed.");
                return false;
            }
            FloorTile filler = fillerList.get(0); //we pick the first filler we get
            log.info("Filler tile found. fillerTile at {}", filler.getCoordinates());
            int rotated = 0;
            do {
                //here we add our tile on top of filler if it has the same exit
                //else, we rotate and try it again
                //if it fits, we check the rules, add it and return true
                //if it doesn't end after three tries, we return false;
                if (checkExits(floorTile, filler) && isFloorTileValid(floorTile)) {
                    //if an exit matches and it's valid, we add it to the map and exit
                    int x = filler.getCoordinates().getX();
                    int y = filler.getCoordinates().getY();
                    gameMap[x][y] = floorTile;
                    floorTile.setCoordinates(x, y);
                    addFillerTiles(floorTile);
                    return true;
                }
                floorTile.rotate();
                rotated++;
            } while (rotated < 4);
            //if it doesn't fit after rotating it three times, we exit the method
            return false;
        }
    }

    private boolean isFloorTileValid(FloorTile floorTile) {
        //RULES:
        //unique tales can't be next to each other
        //change-direction tiles can't be next to each other
        //change-direction tiles can't lead to the existing tile without an open exit
        //if they lead to an open exit, it gets automatically filled
        //if the tile is one point from the border, we need to place there a dead end

        return false;
    }

    private boolean checkExits(FloorTile floorTile, FloorTile filler) {
        for (int i = 0; i < 4; i++) {
            if (floorTile.getExits()[i] != null && filler.getExits()[i] != null) {
                //there is an exit that will connect to the existing tile
                return true;
            }
        }
        //there is no way to connect those tiles
        return false;
    }

    private void addFillerTiles(FloorTile floorTile) {
        Exit[] exits = floorTile.getExits();
        for (int i = 0; i < 4; i++) {
            //if there is an exit option, we add a filler tile
            //which needs to be replaced with an actual tile in the future
            if (exits[i] != null) {
                FloorTile fillerTale =
                        new FloorTileImpl(-1, FillerTile.FILLER_TILE_NAME)
                                .setCoordinates(
                                        floorTile.getCoordinates().getX() + exits[i].getX(),
                                        floorTile.getCoordinates().getY() + exits[i].getY())
                                .setOneExit(i)
                                .rotate()
                                .rotate();
                fillerList.add(fillerTale);
            }
        }
    }

    @Override
    public boolean isValid() {
        //1. all tiles need to be connected;
        //2. tiles not connected can't be placed next to each other
        return false;
    }

    @Override
    public boolean isFinished() {
        if (finished) {
            return true;
        }
        //else check:
        //it's finished when it's valid and
        //tiles don't have open exits
        return false;
    }

    //for testing purposes
    public void printMap() {
        // TODO: 2019-11-26 finish printing to console
    }
}
