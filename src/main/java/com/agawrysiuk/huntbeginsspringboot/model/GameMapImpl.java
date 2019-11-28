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
        //unique tales can't be next to each other
        //change-direction tiles can't be next to each other
        //change-direction tiles can't lead to the existing tile without an open exit
        //if they lead to an open exit, it gets automatically filled
        //if the tile is one point from the border, we need to place there a dead end
        //
        //0. we start with a unique tale
        //1. if there is a unique tale, we check where there is a filler tile on the board
        //2. we try to add a tile to this exit
        //3. whether it happens or not, we return true or false;
        //3a. if it's true, we also connect two tales to each other
        log.info("floorTile = {}", floorTile);
        if (fillerList.size() == 0 && !finished) {
            log.info("Adding floorTile as an opening tile.");
            int x = 0;
            int y = 20;

            gameMap[x][y] = floorTile;
            floorTile.setCoordinates(x, y);

            addFillerTiles(floorTile);
            return true;
        } else {
            log.info("Trying to find a filler tile.");
            if (fillerList.size() == 0) {
                log.info("No filler tile found. Map is completed.");
                return false;
            }
            FloorTile fillerTale = fillerList.get(0);
            log.info("Filler tile found. fillerTale = {}", fillerTale);
            int rotated = 0;
            do {
                //todo here we add our tile on top of filler if it has the same exit
                //todo else, we rotate and try it again
                //todo if it fits, we check the rules, add it and return true
                //todo if it doesn't end after three tries, we return false;

                floorTile.rotate();
                rotated++;
            } while (rotated < 4);
            return false;
            //here we need to put a regular tile instead
            //but it needs to have an exit in the same place the fillerTale has
        }
    }

    private void addFillerTiles(FloorTile floorTile) {
        Exit[] exits = floorTile.getExits();
        for (int i = 0; i < 4; i++) {
            //if there is an exit option, we add a filler tile
            //which needs to be replaced with an actual tile in the future
            if (exits[i] != null) {
                FloorTile fillerTale =
                        new FloorTileImpl(-1, "filler")
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
