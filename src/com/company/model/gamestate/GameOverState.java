package com.company.model.gamestate;

import com.company.view.main.GamePanel;
import com.company.view.resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static com.company.view.main.GamePanel.WIDTH;

public class GameOverState extends GameState implements MouseListener, MouseMotionListener{

    private final int n;
    private final Color color1;
    private final double x;
    private final double y;
    private final double w;
    private final double h;
    public static boolean click;

    private final String[] list = new String[4];

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        x = WIDTH / 2 - 100;
        y = 0;
        w = 200;
        h = 75;
        n = 4;
        color1 = Color.WHITE;

        list[0] = "заново";
        list[1] = "рекорды";
        list[2] = "помощь";
        list[3] = "помощь";
    }
    public void init() {}
    public void tick() {
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 0 &&
                GamePanel.mouseY < (this.y + 100) * 0 + this.h) {
            list[0] = "ЗАНОВО";
            if(click) {
                gsm.getStates().pop();
                gsm.init();
            }
        }
        else {
            list[0] = "заново";
        }
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 1 &&
                GamePanel.mouseY < (this.y + 100) * 1 + this.h) {
            list[1] = "РЕКОРДЫ";
            if(click) {
                gsm.getStates().push(new GameRecordState(gsm));
            }
        }
        else {
            list[1] = "рекорды";
        }
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 2 &&
                GamePanel.mouseY < (this.y + 100) * 2 + this.h) {
            list[2] = "ПОМОЩЬ";
            if(click) {
                gsm.getStates().push(new GameHelpState(gsm));
            }
        }
        else {
            list[2] = "помощь";
        }
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 3 &&
                GamePanel.mouseY < (this.y + 100) * 3 + this.h) {
            list[3] = "ВЫХОД";
            if(click) {
                System.exit(0);
            }
        }
        else {
            list[3] = "выход";
        }
    }
    public void draw(Graphics g) {
        Images.drawGameOver(g, n, color1, x, y, w, h, list);
    }
    public void keyPressed(int k) {}
    public void keyReleased(int k) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) { GameOverState.click = true;}
    public void mouseReleased(MouseEvent e) { GameOverState.click = false; }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}
