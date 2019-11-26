package com.agawrysiuk.huntbeginsspringboot.model;

public interface FloorTile {

    int getId();

    //set up exits from file?
    void setExits(int... exits);

    //exits necessary or not?
    int[] getExits();

    String getName();

    void rotate();

    int getRotate();
}
