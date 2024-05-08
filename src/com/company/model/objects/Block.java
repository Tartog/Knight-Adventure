package com.company.model.objects;

import com.company.view.resources.Images;
import java.awt.*;

public class Block extends Rectangle {
    private static final long serialVersionUID = 1L;
    public static final int blockSize = 64;
    private int id;
    public Block(int x, int y, int id) {
        setBounds(x, y, blockSize, blockSize);
        this.id = id;
    }
    public void tick() { }
    public void draw(Graphics g) {
        Images.drawObject(g, id, x, y, width, height);
    }
    public void setID(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    }
}
