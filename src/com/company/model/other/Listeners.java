package com.company.model.other;

import com.company.model.gamestate.MenuState;
import com.company.view.main.GamePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listeners implements MouseListener, MouseMotionListener {
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            MenuState.setClick(true);
        }
    }
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            MenuState.setClick(false);
        }
    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseDragged(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}
