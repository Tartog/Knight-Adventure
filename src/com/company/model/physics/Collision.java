package com.company.model.physics;

import com.company.model.entites.Player;
import com.company.model.objects.Block;
import java.awt.*;

public class Collision {
    public static boolean playerBlock(Point p, Block b) {
        return b.contains(p);
    }
    public static boolean playerBullet(Point p, Player plr) { return plr.contains(p);}
}