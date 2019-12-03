package com.agawrysiuk.huntbeginsspringboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GameMapRepository extends MongoRepository<GameMapDto,String> {
    List<GameMapDto> findAll();
}
