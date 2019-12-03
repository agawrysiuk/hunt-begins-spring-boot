package com.agawrysiuk.huntbeginsspringboot.service;

import com.agawrysiuk.huntbeginsspringboot.model.FloorTile;
import com.agawrysiuk.huntbeginsspringboot.model.GameMap;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMapDto {
    @Getter
    private List<List<FloorTile>> gameListMap;

    public GameMapDto(GameMap gameMap) {
        gameListMap = new ArrayList<>();
        for (FloorTile[] row : gameMap.getGameMap()) {
            List<FloorTile> list = new ArrayList<>(Arrays.asList(row));
            gameListMap.add(list);
        }
    }
}
