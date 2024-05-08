package com.company.model.gamestate;

import com.company.view.main.GamePanel;
import com.company.view.resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GameHelpState extends GameState implements MouseListener, MouseMotionListener
{

    private final int n;
    private final Color color1;
    private final double x;
    private final double y;
    private final double w;
    private final double h;
    public static boolean click;
    private ArrayList<String> recordsPlayers = new ArrayList<String>();
    private ArrayList<String> namesPlayers = new ArrayList<String>();
    private String list = new String();

    public GameHelpState(GameStateManager gsm) {
        super(gsm);
        x = 0;
        y = 0;
        w = 200;
        h = 75;
        n = 1;
        color1 = Color.WHITE;
        list = "назад";
    }
    public void init() { }
    public void tick() {
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 0 &&
                GamePanel.mouseY < (this.y + 100) * 0 + this.h) {
            list = "НАЗАД";
            if(click) {
                gsm.getStates().pop();
            }
        }
        else {
            list = "назад";
        }
    }
    public void draw(Graphics g) {
        Images.drawGameHelp(g, color1, x, y, h, w, list);
    }
    public void keyPressed(int k) {}
    public void keyReleased(int k) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) { GameHelpState.click = true;}
    public void mouseReleased(MouseEvent e) { GameHelpState.click = false; }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}
