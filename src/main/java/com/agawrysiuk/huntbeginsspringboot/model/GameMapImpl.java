package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class GameMapImpl implements GameMap {
    @Getter
    private Map<Integer,FloorTile> gameMap;

    public GameMapImpl() {
        this.gameMap = new HashMap<>();
    }

    @Override
    public GameMap addFloorTile(FloorTile floorTile) {
        gameMap.put(floorTile.getId(), floorTile);
        return this;
    }

}
