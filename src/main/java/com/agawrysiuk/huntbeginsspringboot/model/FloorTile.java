package com.agawrysiuk.huntbeginsspringboot.model;

import java.util.List;

public interface FloorTile {

    //for Escape Pod / Laboratory, Armoury, Bridge, Hibernation Room, Engine Room
    boolean isUnique();

    //set up exits from file?
    void setExits(boolean... exits);

    //exits necessary or not?
    ExitAvP[] getExits();

    String getName();

    void rotate();
}
