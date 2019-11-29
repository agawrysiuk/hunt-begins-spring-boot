package com.agawrysiuk.huntbeginsspringboot.model;

import com.agawrysiuk.huntbeginsspringboot.data.Database;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@Slf4j
public class GameManagerImpl implements GameManager {

    private GameMap gameMap;
    @Getter
    private List<FloorTile> tiles;

    public GameManagerImpl() {
        this.tiles = new ArrayList<>();
    }

    /*    14x Straight Corridors    id 1-14
      8x  L-Shape Tiles             id 15-22
      8x  T-Shape Tiles             id 23-30
      12x Crossroads                id 31-42
      12x Dead Ends                 id 43-54
      12x Air Vent Tiles
      5   Rooms (Escape Pod / Laboratory,
            Armoury, Bridge, Hibernation Room,
            Engine Room)            id 55-59
      12  Door Tiles*/
    @Override
    public GameManager loadTiles() throws IOException {
        BufferedReader br = Database.getDatabase().getData();
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            String[] array = currentLine.split(",");
            FloorTile floorTile = new FloorTileImpl(Integer.parseInt(array[0]), array[1]);
            floorTile.setExits(
                    Arrays.stream(new String[]{array[2], array[3], array[4], array[5]})
                            .mapToInt(Integer::parseInt)
                            .toArray());
            tiles.add(floorTile);
        }
        return this;
    }

    @Override
    public GameMap createMap() {
        this.gameMap = new GameMapImpl();
        //1. check if it's empty
        //  - yes -> put first tile, a Dead End or a Special tile, loop;
        //  - no -> move to point 2;
        //2. check if map is finished
        //  - yes -> end it;
        //  - no -> go to point 3
        //3. try to add a tile
        Random random = new Random();
        int i = 0;
        while (!gameMap.isFinished()) {
            i++;
            if(i%10==0) {
                log.info("Iteration number = {}",i);

            }

            FloorTile tileToAdd;

            int tileNumber;

            if (gameMap.getFillerList().isEmpty()) {//we need to choose a starting tile if it's an empty map
                tileNumber = tiles.size() - 1 - random.nextInt(5);
                tileToAdd = tiles.get(tileNumber);
            } else {
                tileNumber = random.nextInt(tiles.size()-1);
                tileToAdd = tiles.get(tileNumber);
//                log.info("tileToAdd = {}",tileToAdd);
                if(tileToAdd==null) {
//                    log.info("We already used that tile. Trying another tile.");
                    continue;
                } else if(tiles.size()>35){
                    if(tileToAdd.getName().equals("Dead End")) {
//                        log.info("We reached dead end too soon. Trying another tile.");
                        continue;
                    } else if(tileToAdd.getId()>=55) {
//                        log.info("We reached unique tile too soon. Trying another tile.");
                        continue;
                    }

                }

            }
            if (gameMap.addFloorTile(tileToAdd)) { //we try adding this tile
//                log.info("tileToAdd number {} successfully added.",tileToAdd.getId());
                tiles.remove(tileNumber);
            } else {
                tileToAdd.goBackToDefault();
//                log.info("tileToAdd number {} not added. Trying again",tileToAdd.getId());
            }
            if(i==100000) {
                break;
            }
            if(i%100==0) {
                gameMap.printMap();
            }
        }
        log.info("Map created.");
        gameMap.printMap();
        return gameMap;
    }

    public FloorTile getTile(int id) {
        return tiles.get(id);
    }
}
