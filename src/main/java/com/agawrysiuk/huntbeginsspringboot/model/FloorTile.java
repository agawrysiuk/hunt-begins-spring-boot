package com.agawrysiuk.huntbeginsspringboot.model;

import java.util.List;

public interface FloorTile {

    //for Escape Pod / Laboratory, Armoury, Bridge, Hibernation Room, Engine Room
    boolean isUnique();

    //set up exits from file?
    void setExits(int... exits);

    //exits necessary or not?
    ExitAvP[] getExits();

    /*    14x Straight Corridors
          8x  L-Shape Tiles
          8x  T-Shape Tiles
          12x Crossroads
          12x Dead Ends
          12x Air Vent Tiles
          5   Rooms (Escape Pod / Laboratory,
                Armoury, Bridge, Hibernation Room,
                Engine Room)
          12  Door Tiles*/
}
