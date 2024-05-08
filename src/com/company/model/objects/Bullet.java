package com.company.model.objects;

import com.company.model.entites.Player;
import com.company.model.physics.Collision;
import com.company.view.resources.Images;
import java.awt.*;

public class Bullet{
    private double x;
    private final double y;
    private final double start_x;
    private final double start_y;
    private final int speed;
    private final int width;
    private final int height;
    private final boolean side;
    public Bullet(int width, int height, int x, int y, boolean side) {
        start_x = x;
        start_y = y + 20;
        this.x = x;
        this.y = y + 20;
        this.width = width;
        this.height = height;
        speed = 9;
        this.side = side;
    }
    public void tick(Block[][] b, Player p) {
        if (Collision.playerBullet(new Point((int)x, (int)y), p)) {
            x = start_x;
            p.hit();
        }
        for(int i = 0;i < b.length;i++) {
            for(int j = 0;j < b[0].length;j++) {
                if (b[i][j].getID() == 1) {
                    if ((Collision.playerBlock(new Point((int)x, (int)y), b[i][j]))
                        || Collision.playerBlock(new Point((int)x + 30, (int)y), b[i][j]))  {
                        x = start_x;
                    }
                }
            }
        }
        if(side) {
            x -= speed;
        }
        else {
            x += speed;
        }
    }
    public void draw(Graphics g, Block[][] b) {
        Images.drawBullet(g, side, x, y, width, height);
    }
}