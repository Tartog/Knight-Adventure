package com.company.model.gamestate;

import com.company.model.mapping.Map;
import com.company.model.objects.Block;
import com.company.model.physics.Collision;
import com.company.view.Back;
import com.company.view.main.GamePanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.FileWriter;
import java.io.IOException;

public class GameEditorState extends GameState implements MouseListener, MouseMotionListener {
    private Map map;
    private Back back;
    private Block[][] blocks;
    private static boolean click;
    private boolean block;
    private boolean coin;
    private boolean enemy;
    private boolean spike;
    private boolean castle;
    private boolean empty;

    public GameEditorState(GameStateManager gsm)
    {
        super(gsm);
    }
    public void init() {
        map = new Map("/Maps/map1.map");
        back = new Back();
        blocks = map.getBlocks();
    }
    public void tick() {
        for(int i = 0;i < blocks.length;i++) {
            for(int j = 0;j < blocks[0].length;j++) {
                if (Collision.playerBlock(new Point(GamePanel.mouseX, GamePanel.mouseY), blocks[i][j])) {
                    if(click) {
                        if(block) {
                            blocks[i][j].setID(1);
                        }
                        else if(coin) {
                            blocks[i][j].setID(2);
                        }
                        else if(enemy) {
                            blocks[i][j].setID(3);
                        }
                        else if(spike) {
                            blocks[i][j].setID(4);
                        }
                        else if(castle) {
                            blocks[i][j].setID(5);
                        }
                        else {
                            blocks[i][j].setID(0);
                        }
                    }
                }
            }
        }
    }

    public void draw(Graphics g) {
        back.draw(g);
        map.draw(g);
    }
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_1) {
            block = true;
            empty = coin = enemy = spike = castle = false;
        }
        else if(k == KeyEvent.VK_2) {
            coin = true;
            empty = block = enemy = spike = castle = false;
        }
        else if(k == KeyEvent.VK_3) {
            enemy = true;
            empty = block = coin = spike = castle = false;
        }
        else if(k == KeyEvent.VK_4) {
            spike = true;
            empty = block = enemy = coin = castle = false;
        }
        else if(k == KeyEvent.VK_5) {
            castle = true;
            empty = block = enemy = spike = coin = false;
        }
        else if(k == KeyEvent.VK_ESCAPE) {
            FileWriter writer;
            try {
                writer = new FileWriter("res/Maps/map1.map");
                writer.write(14 + "\n");
                writer.write(9 + "\n");
                for(int i = 0;i < blocks.length;i++) {
                    for(int j = 0;j < blocks[0].length;j++) {
                        writer.write(blocks[i][j].getID() + " ");
                    }
                    writer.write("\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
        else {
            empty = true;
            coin = block = enemy = spike = castle = false;
        }
    }
    public void keyReleased(int k) {
    }
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
        GameEditorState.click = true;
    }
    public void mouseReleased(MouseEvent e) {
        GameEditorState.click = false;
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}
