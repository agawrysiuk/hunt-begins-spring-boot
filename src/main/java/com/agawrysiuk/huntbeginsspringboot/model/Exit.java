package com.agawrysiuk.huntbeginsspringboot.model;

public enum Exit {
    TOP_EXIT(0,0,-1),
    RIGHT_EXIT(1,1,0),
    BOT_EXIT(2,0,1),
    LEFT_EXIT(3,-1,0);

    private int position;
    private int x;
    private int y;

    Exit(int position, int x, int y) {
        this.position = position;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPosition() {
        return position;
    }
}
