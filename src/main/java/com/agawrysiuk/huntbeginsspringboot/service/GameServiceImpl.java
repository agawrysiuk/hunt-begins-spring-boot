package com.agawrysiuk.huntbeginsspringboot.service;

import com.agawrysiuk.huntbeginsspringboot.model.FloorTile;
import com.agawrysiuk.huntbeginsspringboot.model.GameManager;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GameServiceImpl implements GameService {

    private final GameManager gameManager;

    public GameServiceImpl(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public GameMapDto getMap() {
        try {
            gameManager.loadTiles();
            for (FloorTile floorTile : gameManager.getTiles()) {
                floorTile.setImgPath("/tiles/"+floorTile.getName()+".png");
            }
            return new GameMapDto(gameManager.createMap());
        } catch (IOException e) {
            return null;
        }
    }
}
