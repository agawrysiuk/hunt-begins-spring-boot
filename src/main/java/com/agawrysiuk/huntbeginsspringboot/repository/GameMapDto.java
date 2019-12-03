package com.agawrysiuk.huntbeginsspringboot.repository;

import com.agawrysiuk.huntbeginsspringboot.model.FloorTile;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="avp")
@Data
public class GameMapDto {

    @Id
    private String id;
    private List<List<FloorTile>> gameListMap;

    public GameMapDto() {
    }
}
