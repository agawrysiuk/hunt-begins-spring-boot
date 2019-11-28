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
    public boolean addFloorTile(FloorTile newFloorTile) {
        //0. we start with a unique tale
        //1. if there is a unique tale, we check where there is a filler tile on the board
        //2. we try to add a tile to this exit
        //3. whether it happens or not, we return true or false;
        //3a. if it's true, we also connect two tales to each other
        log.info("addFloorTile() started. newFloorTile = {}", newFloorTile);
        if (fillerList.size() == 0 && !finished) {
            //here is a code for the opening tile
            log.info("Adding floorTile as the opening tile.");
            int x = 0;
            int y = 20;

            gameMap[x][y] = newFloorTile;
            newFloorTile.setCoordinates(x, y);

            addFillerTiles(newFloorTile);
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
                log.info("Checking for validity. Rotation = {}",rotated*90);
                if (checkExits(newFloorTile, filler) && isFloorTileValid(newFloorTile,filler.getCoordinates())) {
                    //if an exit matches and it's valid, we add it to the map and exit
                    int x = filler.getCoordinates().getX();
                    int y = filler.getCoordinates().getY();
                    gameMap[x][y] = newFloorTile;
                    newFloorTile.setCoordinates(x, y);
                    addFillerTiles(newFloorTile);
                    return true;
                }
                newFloorTile.rotate();
                rotated++;
            } while (rotated < 4);
            //if it doesn't fit after rotating it three times, we exit the method
            return false;
        }
    }

    private boolean isFloorTileValid(FloorTile newFloorTile, Coordinates coordinates) {
        //RULES:
        //unique tiles and change-direction tiles can't be next to each other
        log.info("isFloorTileValid() started. Checking for the valid tile.");
        if(!newFloorTile.getName().equals("Straight Corridor") && !newFloorTile.getName().equals("Dead End")) {
            //it's a unique tile
            FloorTile nearbyTile = getNearbyTile(coordinates);
            if (nearbyTile == null) {
                log.info("Something went wrong when looking for the nearbyTile!");
                return false;
            }
            if(!nearbyTile.getName().equals("Straight Corridor") && !nearbyTile.getName().equals("Dead End")) {
                log.info("Two unique tiles next to each other. Not a valid tile.");
                return false;
            }
            //else the nearby tile is not unique and we are good to go
        }
        //change-direction tiles can't lead to the existing tile without an open exit
        if (newFloorTile.getName().equals("L-Shape Tile") || newFloorTile.getName().equals("T-Shape Tile") || newFloorTile.getName().equals("CrossRoad")) {
            //todo finish this
        }
        //if they lead to an open exit, it gets automatically filled?
        //if the tile is one point from the border, we need to place there a dead end

        return false;
    }

    private FloorTile getNearbyTile(Coordinates coordinates) {
        int x = coordinates.getX();
        int y = coordinates.getY();

        for (int i = x - 1; i < x + 3; i++) {
            for (int j = y - 1; j < y + 3; j++) {
                FloorTile foundTile = gameMap[i][j];
                if(foundTile!=null && !foundTile.getName().contains("filler")) {
                    return foundTile;
                }
            }
        }

        return null;
    }

    private boolean checkExits(FloorTile newFloorTile, FloorTile filler) {
        log.info("checkExits() started.");
        for (int i = 0; i < 4; i++) {
            if (newFloorTile.getExits()[i] != null && filler.getExits()[i] != null) {
                //there is an exit that will connect to the existing tile
                log.info("Exits match.");
                return true;
            }
        }
        log.info("Exits don't match.");
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
