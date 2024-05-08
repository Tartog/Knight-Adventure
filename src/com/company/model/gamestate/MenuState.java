package com.company.model.gamestate;

import com.company.view.main.GamePanel;
import com.company.view.resources.Images;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static com.company.view.main.GamePanel.WIDTH;

public class MenuState extends GameState implements MouseListener, MouseMotionListener {

    private final int n;
    private final Color color1;
    private final double x;
    private final double y;
    private final double w;
    private final double h;
    private static boolean click;
    String[] list = new String[5];
    public static void setClick(boolean clk)
    {
        click = clk;
    }

    public MenuState(GameStateManager gsm) {
        super(gsm);
        x = WIDTH / 2 - 100;
        y = 0;
        w = 200;
        h = 75;
        n = 5;
        color1 = Color.WHITE;
        list[0] = "редактор";
        list[1] = "играть";
        list[2] = "рекорды";
        list[3] = "правила";
        list[4] = "выход";
    }
    public void init() { }
    public void tick() {
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 0 &&
                GamePanel.mouseY < (this.y + 100) * 0 + this.h) {
            list[0] = "РЕДАКТОР";
            if(click) {
                gsm.getStates().push(new GameEditorState(gsm));
            }
        }
        else {
            list[0] = "редактор";
        }
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 1 &&
                GamePanel.mouseY < (this.y + 100) * 1 + this.h) {
            list[1] = "ИГРАТЬ";
            if(click) {
                gsm.getStates().push(new Level1State(gsm));
            }
        }
        else {
            list[1] = "играть";
        }
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 2 &&
                GamePanel.mouseY < (this.y + 100) * 2 + this.h) {
            list[2] = "РЕКОРДЫ";
            if(click) {

                gsm.getStates().push(new GameRecordState(gsm));
            }
        }
        else {
            list[2] = "рекорды";
        }
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 3 &&
                GamePanel.mouseY < (this.y + 100) * 3 + this.h) {
            list[3] = "ПОМОЩЬ";
            if(click) {
                gsm.getStates().push(new GameHelpState(gsm));
            }
        }
        else {
            list[3] = "помощь";
        }
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 4 &&
                GamePanel.mouseY < (this.y + 100) * 4 + this.h) {
            list[4] = "ВЫХОД";
            if(click) {
                System.exit(0);
            }
        }
        else {
            list[4] = "выход";
        }
    }
    public void draw(Graphics g) {
        Images.drawMainMenu(g, n, color1, x, y, list, w, h);
    }
    public double getY()
    {
        return y;
    }
    public void keyPressed(int k) { }
    public void keyReleased(int k) { }
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
        MenuState.click = true;
    }
    public void mouseReleased(MouseEvent e) {
        MenuState.click = false;
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}
