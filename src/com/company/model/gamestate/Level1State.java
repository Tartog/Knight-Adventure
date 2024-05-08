package com.company.model.gamestate;

import com.company.view.Back;
import com.company.model.other.ScoreTable;
import com.company.model.entites.Player;
import com.company.model.objects.Block;
import com.company.model.objects.Bullet;
import com.company.model.mapping.Map;
import java.awt.*;
import java.util.ArrayList;

public class Level1State extends GameState{
    private Player player;
    private Map map;
    private Back back;
    private ArrayList<Bullet> bullets;
    private Block[][] blocks;
    private static ScoreTable scoreTable;
    public Level1State(GameStateManager gsm) {
        super(gsm);
        scoreTable = new ScoreTable(player.getCoinCounter());
    }
    public static ScoreTable getScoreTable()
    {
        return scoreTable;
    }
    public void init() {
        player = new Player(50,  50);
        map = new Map("/Maps/map1.map");
        back = new Back();
        bullets = new ArrayList<Bullet>();
        blocks = map.getBlocks();
        player.setHealth(3);
        player.setCoinCounter(0);
        for(int i = 0;i < blocks.length;i++) {
            for(int j = 0;j < blocks[0].length;j++) {
                if(blocks[i][j].getID() == 3) {
                    bullets.add(new Bullet(45, 15, blocks[i][j].blockSize * j, blocks[i][j].blockSize * i, true));
                    bullets.add(new Bullet(45, 15, blocks[i][j].blockSize * j, blocks[i][j].blockSize * i, false));
                }
            }
        }
    }
    public void tick() {
        player.tick(map.getBlocks(), gsm);
        for(int i = 0;i < bullets.size();i++) {
            bullets.get(i).tick(blocks, player);
        }
    }
    public void draw(Graphics g) {
        back.draw(g);
        player.draw(g);
        map.draw(g);
        for(int i = 0;i < bullets.size();i++) {
            bullets.get(i).draw(g, blocks);
        }
    }
    public void keyPressed(int k) {
        player.keyPressed(k);
    }
    public void keyReleased(int k) {
        player.keyReleased(k);
    }
}