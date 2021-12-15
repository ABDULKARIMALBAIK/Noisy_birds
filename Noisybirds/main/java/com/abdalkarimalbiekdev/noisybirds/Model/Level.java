package com.abdalkarimalbiekdev.noisybirds.Model;

public class Level {

    private int levelNo;
    private String levelName;
    private boolean isOpen;

    public Level() {
    }

    public Level(int levelNo, String levelName, boolean isOpen) {
        this.levelNo = levelNo;
        this.levelName = levelName;
        this.isOpen = isOpen;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
