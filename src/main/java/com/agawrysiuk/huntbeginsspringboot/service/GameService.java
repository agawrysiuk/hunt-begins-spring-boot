package com.agawrysiuk.huntbeginsspringboot.service;

import com.agawrysiuk.huntbeginsspringboot.repository.GameMapDto;

import java.util.List;

public interface GameService {
    GameMapDto getMap();

    List<GameMapDto> findAll();

    void addMapToRepository(GameMapDto gameMapDto);
}
