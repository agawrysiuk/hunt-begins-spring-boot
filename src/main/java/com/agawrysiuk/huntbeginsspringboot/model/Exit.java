package com.agawrysiuk.huntbeginsspringboot.model;

public interface Exit {
    int getExitTo();

    FloorTile getFloorTile();

    void setOpen(boolean open);

    void setConnected(boolean connected);

    boolean isOpen();

    boolean isConnected();
}
