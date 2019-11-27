package com.agawrysiuk.huntbeginsspringboot.model;

public interface FloorTile {

    int getId();

    void setExits(int... exits);

    int[] getExits();

    String getName();

    void rotate();

    double getRotate();
}
