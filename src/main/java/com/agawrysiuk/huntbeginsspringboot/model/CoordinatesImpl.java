package com.agawrysiuk.huntbeginsspringboot.model;

import lombok.Data;

@Data
public class CoordinatesImpl implements Coordinates {
    private final int x;
    private final int y;

    public CoordinatesImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
