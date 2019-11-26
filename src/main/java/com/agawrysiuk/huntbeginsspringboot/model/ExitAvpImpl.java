package com.agawrysiuk.huntbeginsspringboot.model;

public class ExitAvpImpl implements ExitAvP {
    private boolean closed;
    private boolean connected;

    public ExitAvpImpl(boolean closed) {
        this.closed = closed;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public boolean isConnected() {
        return false;
    }
}
