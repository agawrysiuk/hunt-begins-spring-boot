package com.agawrysiuk.huntbeginsspringboot.service;

import com.agawrysiuk.huntbeginsspringboot.model.FloorTile;
import com.agawrysiuk.huntbeginsspringboot.model.GameManager;
import com.agawrysiuk.huntbeginsspringboot.model.GameMap;
import com.agawrysiuk.huntbeginsspringboot.repository.GameMapDto;
import com.agawrysiuk.huntbeginsspringboot.repository.GameMapRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameManager gameManager;
    private final GameMapRepository gameMapRepository;

    public GameServiceImpl(GameManager gameManager, GameMapRepository gameMapRepository) {
        this.gameManager = gameManager;
        this.gameMapRepository = gameMapRepository;
    }

    @Override
    public List<GameMapDto> findAll() {
        return gameMapRepository.findAll();
    }

    @Override
    public void addMapToRepository(GameMapDto gameMapDto) {
        gameMapRepository.save(gameMapDto);
    }

    @Override
    public GameMapDto getMap() {
        try {
            gameManager.loadTiles();
            for (FloorTile floorTile : gameManager.getTiles()) {
                floorTile.setImgPath("/tiles/"+floorTile.getName()+".png");
            }
            return transferMap(gameManager.createMap());
        } catch (IOException e) {
            return null;
        }
    }

    private GameMapDto transferMap(GameMap gameMap) {
        List<List<FloorTile>> gameListMap = new ArrayList<>();
        for (FloorTile[] row : gameMap.getGameMap()) {
            List<FloorTile> list = new ArrayList<>(Arrays.asList(row));
            gameListMap.add(list);
        }
        GameMapDto gameMapDto = new GameMapDto();
        gameMapDto.setGameListMap(gameListMap);
        return gameMapDto;
    }
}
