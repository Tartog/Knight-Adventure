package com.company.model.entites;

import com.company.model.gamestate.*;
import com.company.view.main.GamePanel;
import com.company.model.objects.Block;
import com.company.model.physics.Collision;
import com.company.view.resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Rectangle {
    private static int health = 3;
    private static int coinCounter = 0;
    private static String name;

    private boolean right = false, left = false, jumping = false, falling = false;
    private boolean topCollision = false;

    private static double player_x, player_y;
    private static int width, height;

    private final double moveSpeed = 2.5;

    private final double jumpSpeed = 5;
    private double currentJumpSpeed = jumpSpeed;

    private double maxFallSpeed = 5;
    private double currentFallSpeed = 0.1;

    public Player(int width, int height) {
        player_x = 100;
        player_y = 500;
        this.width = width;
        this.height = height;
        setBounds((int)player_x, (int)player_y, width, height);
    }

    public void hit()
    {
        health--;
    }

    public static int getHealth()
    {
        return health;
    }
    public static int getCoinCounter()
    {
        return coinCounter;
    }
    public static int getPlayerWidth()
    {
        return width;
    }
    public static int getPlayerHeight()
    {
        return height;
    }
    public static double getPlayerX()
    {
        return player_x;
    }
    public static double getPlayerY()
    {
        return player_y;
    }
    public static void setHealth(int hp){health = hp;}
    public static void setCoinCounter(int coin){coinCounter = coin;}

    public void tick(Block[][] b, GameStateManager gsm) {
        setBounds((int)player_x, (int)player_y, width, height);

        int iX = (int)player_x;
        int iY = (int)player_y;

        if(health <= 0) {
            gsm.getStates().push(new GameOverState(gsm));
        }

        for(int i = 0;i < b.length;i++)
        {
            for(int j = 0;j < b[0].length;j++ ) {
                if(b[i][j].getID() == 1) {
                    // right
                    if (Collision.playerBlock(new Point(iX + width, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + width, iY + height - 1), b[i][j])) {
                        right = false;
                    }
                    // left
                    if (Collision.playerBlock(new Point(iX + - 1, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX - 1, iY + height - 1), b[i][j])) {
                        left = false;
                    }
                    // top
                    if (Collision.playerBlock(new Point(iX + 1, iY), b[i][j]) ||
                            Collision.playerBlock(new Point(iX + width - 3, iY), b[i][j])) {
                        jumping = false;
                        falling = true;
                    }
                    //bottom
                    if (Collision.playerBlock(new Point(iX + 2, iY + height + 1), b[i][j]) ||
                            Collision.playerBlock(new Point(iX + width - 3, iY + height+ 1), b[i][j])) {
                        player_y = b[i][j].getY() - height;
                        falling = false;
                        topCollision = true;
                    } else {
                        if (!topCollision && !jumping) {
                            falling = true;
                        }
                    }
                }
                if(b[i][j].getID() == 2) {
                    // right
                    if (Collision.playerBlock(new Point(iX + 30, iY + 2), b[i][j])) {
                        b[i][j].setID(0);
                        coinCounter++;
                    }
                }
                if(b[i][j].getID() == 4) {
                    // right
                    if (Collision.playerBlock(new Point(iX + width, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + width, iY + height - 1), b[i][j])) {
                        right = false;
                        hit();
                    }
                    // left
                    if (Collision.playerBlock(new Point(iX - 1, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX - 1, iY + height - 1), b[i][j])) {
                        left = false;
                        hit();
                    }
                    if (Collision.playerBlock(new Point(iX + 2, iY + height + 1), b[i][j]) ||
                            Collision.playerBlock(new Point(iX + width - 3, iY + height + 1), b[i][j])) {
                        player_y = b[i][j].getY() - height;
                        falling = false;
                        topCollision = true;
                        hit();
                    } else {
                        if (!topCollision && !jumping) {
                            falling = true;
                        }
                    }
                }
                if(b[i][j].getID() == 5) {
                    // right
                    if (Collision.playerBlock(new Point(iX + width, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + width, iY + height - 1), b[i][j])) {
                        right = false;
                        gsm.getStates().push(new GameStateWin(gsm));
                    }
                    // left
                    if (Collision.playerBlock(new Point(iX - 1, iY + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX - 1, iY + height - 1), b[i][j])) {
                        left = false;
                        gsm.getStates().push(new GameStateWin(gsm));
                    }
                    if (Collision.playerBlock(new Point(iX + 2, iY + height + 1), b[i][j]) ||
                            Collision.playerBlock(new Point(iX + width - 3, iY + height + 1), b[i][j])) {
                        player_y = b[i][j].getY() - height;
                        falling = false;
                        topCollision = true;
                        gsm.getStates().push(new GameStateWin(gsm));
                    } else {
                        if (!topCollision && !jumping) {
                            falling = true;
                        }
                    }
                }
            }
        }
        topCollision = false;
        if(right) {
            player_x += moveSpeed;
        }
        if(left) {
            player_x -= moveSpeed;
        }
        if(jumping) {
            player_y -=currentJumpSpeed;

            currentJumpSpeed -= .1;

            if(currentJumpSpeed <= 0) {
                currentJumpSpeed = jumpSpeed;
                jumping = false;
                falling = true;
            }
        }
        if(falling) {
            player_y += currentFallSpeed;
            if(currentFallSpeed < maxFallSpeed) {
                currentFallSpeed += .1;
            }
        }
        if(!falling) {
            currentFallSpeed = .1;
        }
    }
    public static void setName(String str)
    {
        name = str;
    }
    public static String getName()
    {
        return name;
    }
    public void draw(Graphics g)
    {
        Images.drawPlayer(g);
    }
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_D) right = true;
        if(k == KeyEvent.VK_A) left = true;
        if(k == KeyEvent.VK_SPACE && !jumping && !falling) jumping = true;
    }
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_D) right = false;
        if(k == KeyEvent.VK_A) left = false;
    }
}