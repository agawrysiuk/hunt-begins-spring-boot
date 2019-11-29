package com.agawrysiuk.huntbeginsspringboot.model;

public interface FloorTile {

    int getId();

    void setExits(int... exits);

    FloorTile setOneExit(int position);

    Exit[] getExits();

    boolean deleteExit(Exit exit);

    String getName();

    FloorTile rotate();

    double getRotate();

    FloorTile setCoordinates(int x, int y);

    Coordinates getCoordinates();
}
