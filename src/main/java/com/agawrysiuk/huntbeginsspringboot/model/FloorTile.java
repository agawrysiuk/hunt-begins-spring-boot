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

    void setRotate(double rotate);

    FloorTile setCoordinates(int x, int y);

    Coordinates getCoordinates();

    void goBackToDefault();
}
