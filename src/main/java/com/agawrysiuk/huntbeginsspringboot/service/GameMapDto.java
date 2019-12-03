package com.agawrysiuk.huntbeginsspringboot.service;

import com.agawrysiuk.huntbeginsspringboot.model.FloorTile;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class GameMapDto {
    @Getter
    @Setter
    private List<List<FloorTile>> gameListMap;

    public GameMapDto() {
    }
}
