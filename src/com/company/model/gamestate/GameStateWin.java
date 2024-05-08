package com.company.model.gamestate;

import com.company.model.other.ScoreTable;
import com.company.view.TextField;
import com.company.model.entites.Player;
import com.company.view.main.GamePanel;
import com.company.view.resources.Images;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static com.company.view.main.GamePanel.WIDTH;

public class GameStateWin extends GameState implements MouseListener, MouseMotionListener{

    private final int n;
    private final Color color1;
    private final double x;
    private final double y;
    private final double w;
    private final double h;
    public static boolean click;

    String[] list = new String[4];

    public GameStateWin(GameStateManager gsm) {
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
    public void init() {
        try {
            if(Player.getCoinCounter() > Integer.parseInt(ScoreTable.getRecordsPlayers().get(ScoreTable.numberOfPlayers - 1))) {
                TextField test = new TextField();
            }
        }
        catch(IndexOutOfBoundsException e)
        {}
    }
    public void tick() {
        if(GamePanel.mouseX > this.x && GamePanel.mouseX < this.x + this.w && GamePanel.mouseY > (this.y + 100) * 0 &&
                GamePanel.mouseY < (this.y + 100) * 0 + this.h) {
            list[0] = "ЗАНОВО";
            if(click) {
                System.out.println("HELLO");
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
        Images.drawWinMenu(g, n, x, y, color1, list, h, w);
    }
    public void keyPressed(int k) {}
    public void keyReleased(int k) {}
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) { click = true; }
    public void mouseReleased(MouseEvent e) { click = false; }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}