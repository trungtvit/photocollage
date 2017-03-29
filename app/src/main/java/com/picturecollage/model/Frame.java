package com.picturecollage.model;

/**
 * Created by TrungDK on 2/13/2017.
 */

public class Frame {
    private int id;
    private int frameImage;

    public Frame(int id,int frameImage) {
        this.id = id;
        this.frameImage=frameImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrameImage() {
        return frameImage;
    }

    public void setFrameImage(int frameImage) {
        this.frameImage = frameImage;
    }
}
