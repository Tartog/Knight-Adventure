package com.company.view.resources;

import com.company.model.other.ScoreTable;
import com.company.model.entites.Player;
import com.company.model.gamestate.GameRecordState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.company.view.main.GamePanel.HEIGHT;
import static com.company.view.main.GamePanel.WIDTH;
import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

public class Images {

    private static BufferedImage[] blocks;
    private static BufferedImage background;
    private static BufferedImage player;
    private static BufferedImage bulletRight;
    private static BufferedImage bulletLeft;
    private static BufferedImage enemy;
    private static BufferedImage coin;
    private static BufferedImage spike;
    private static BufferedImage castle;
    public static void drawPlayer(Graphics g)
    {
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("hp - " + Player.getHealth() + "/3", 80, 15);
        g.setColor(Color.YELLOW);
        g.drawString("coin - " + Player.getCoinCounter(), 160, 15);
        g.drawImage(player, (int)Player.getPlayerX(), (int) Player.getPlayerY(), Player.getPlayerWidth(), Player.getPlayerHeight(), null);// отрисовываем элемент в координатах
    }
    public static void drawBackground(Graphics g)
    {
        g.drawImage(background, 0, 0, WIDTH,  HEIGHT, null);// отрисовываем элемент в координатах
    }
    public static void drawBullet(Graphics g, boolean side, double x, double y, int width, int height)
    {
        if(side) {
            g.drawImage(bulletRight, (int) x, (int) y, width, height, null);// отрисовываем элемент в координатах
        }
        else
        {
            g.drawImage(bulletLeft, (int) x, (int) y, width, height, null);// отрисовываем элемент в координатах
        }
    }
    public static void drawObject(Graphics g, int id, int x, int y, int width, int height)
    {
        g.setColor(Color.BLACK);
        if(id == 1) {
            g.drawImage(blocks[0], x, y, width, height, null);
        }
        else if(id == 2) {
            g.drawImage(coin, x + 17, y + 17, 30, 30, null);
        }
        else if (id == 3) {
            g.drawImage(enemy, x, y, 32, 64, null);
        }
        else if (id == 4) {
            g.drawImage(spike, x, y, 64, 64, null);
        }
        else if (id == 5) {
            g.drawImage(castle, x, y, 64, 64, null);
        }
    }
    public static void drawMainMenu(Graphics g, int n, Color color1, double x, double y, String[] list, double w, double h)
    {
        String img;
        img = "res/Back/backMenu.png";
        g.drawImage(new ImageIcon(img).getImage(), 0, 0, null);
        for(int i = 0;i < n;i++) {
            img = "res/Button/button.png";
            g.drawImage(new ImageIcon(img).getImage(), (int)x, (int) (y + 100) * i + 10, null);

            g.setColor(color1);

            Font font = new Font("Arial", Font.ITALIC, 25);
            g.setFont(font);
            long length = (int) g.getFontMetrics().getStringBounds(list[i], g).getWidth();
            g.drawString(list[i], (int) (x + w / 2) - (int) (length / 2), (int) ((y + 100) * i + 10 + (h/3) * 2));
        }
    }
    public static void drawWinMenu(Graphics g, int n, double x, double y, Color color1, String[] list, double h, double w)
    {
        String img;
        img = "res/Back/backMenu.png";
        g.drawImage(new ImageIcon(img).getImage(), 0, 0, null);
        for(int i = 0;i < n;i++) {
            img = "res/Button/button.png";
            g.drawImage(new ImageIcon(img).getImage(), (int) x, (int) (y + 100) * i + 10, null);

            g.setColor(color1);

            Font font = new Font("Arial", Font.ITALIC, 25);
            g.setFont(font);
            long length = (int) g.getFontMetrics().getStringBounds(list[i],g).getWidth();
            g.drawString(list[i], (int) (x + w / 2) - (int) (length / 2), (int) ((y + 100) * i + 10 + (h/3) * 2));
        }
    }
    private void createTable() {
        InputStream is = this.getClass().getResourceAsStream("/Records/ScoreTable.tbl");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            for(int y = 0;y < ScoreTable.numberOfPlayers * 2;y++) {
                String line = br.readLine();
                if(y % 2 == 1) {
                    GameRecordState.setRecordsPlayers(line);
                }
                else {
                    GameRecordState.setNamesPlayers(line);
                }
            }
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawRecordMenu(Graphics g, double x, double y, double w, double h, Color color1, String list)
    {
        String img;

        img = "res/Back/backMenu.png";
        g.drawImage(new ImageIcon(img).getImage(), 0, 0, null);
        img = "res/Back/table.png";
        g.drawImage(new ImageIcon(img).getImage(), 240, 135, null);

        Font font = new Font("Arial", Font.ITALIC, 25);
        g.setFont(font);
        try {
            for (int i = 0; i < ScoreTable.numberOfPlayers; i++) {
                g.drawString(ScoreTable.getNamesPlayers().get(i), 275, 175 + 63 * i);
                g.drawString(ScoreTable.getRecordsPlayers().get(i), 580, 175 + 63 * i);
            }
        }
        catch(IndexOutOfBoundsException e) {
            createTable();
            for (int i = 0; i < ScoreTable.numberOfPlayers; i++) {
                g.drawString(GameRecordState.getNamesPlayers().get(i), 275, 175 + 63 * i);
                g.drawString(GameRecordState.getRecordsPlayers().get(i), 580, 175 + 63 * i);
            }
        }
        img = "res/Button/button.png";
        g.drawImage(new ImageIcon(img).getImage(), (int)x, (int)y, null);

        g.setColor(color1);

        long length = (int) g.getFontMetrics().getStringBounds(list, g).getWidth();
        g.drawString(list, (int) (x + w / 2) - (int) (length / 2), (int) ( (h/3) * 2));
    }
    public static void drawGameOver(Graphics g, int n, Color color1, double x, double y, double w, double h, String[] list) {
        String img;
        img = "res/Back/backMenu.png";
        g.drawImage(new ImageIcon(img).getImage(), 0, 0, null);
        for(int i = 0;i < n;i++) {
            img = "res/Button/button.png";
            g.drawImage(new ImageIcon(img).getImage(), (int) x, (int) (y + 100) * i + 10, null);

            g.setColor(color1);

            Font font = new Font("Arial", Font.ITALIC, 25);
            g.setFont(font);
            long length = (int) g.getFontMetrics().getStringBounds(list[i],g).getWidth();
            g.drawString(list[i], (int) (x + w / 2) - (int) (length / 2), (int) ((y + 100) * i + 10 + (h/3) * 2));
        }
    }
    public static void drawGameHelp(Graphics g, Color color1, double x, double y, double h, double w, String list)
    {
        String img;

        img = "res/Back/backMenu.png";
        g.drawImage(new ImageIcon(img).getImage(), 0, 0, null);
        Font font = new Font("Arial", Font.ITALIC, 19);
        g.setFont(font);

        g.setColor(color1);

        g.drawString("Управление:",100,100);
        g.drawString("D - движение вправо",100,125);
        g.drawString("A - движение влево",100, 150);
        g.drawString("SPACE - прыжок",100,175);
        img = "res/Enemies/Tower.png";
        g.drawImage(new ImageIcon(img).getImage(),100, 200, null);
        g.drawString("- башни будут в вас стрелять стрелами",200, 325);
        img = "res/Coin/coin2.png";
        g.drawImage(new ImageIcon(img).getImage(),125, 350, null);
        g.drawString("- собирайте монетки, чтобы попасть в таблицу рекордов",200, 380);
        img = "res/Castle/castle2.png";
        g.drawImage(new ImageIcon(img).getImage(),120, 425, null);
        g.drawString("- доберитесь до замка, чтобы победить",200, 465);
        img = "res/Button/button.png";
        g.drawImage(new ImageIcon(img).getImage(), (int)x, (int)y, null);
        g.drawString("Режим редактора:",300,25);
        g.drawString("Для начала выберите объект,", 300, 50);
        g.drawString("который хотите создать",300,75);
        g.drawString("0 - удалить объект",300,100);
        g.drawString("1 - блок",300,125);
        g.drawString("2 - монетка",300,150);
        g.drawString("3 - башня",300,175);
        g.drawString("4 - шипы",300,200);
        g.drawString("5 - замок",300,225);
        g.drawString("далее расставляйте выбранный объект левой кнопкой мыши,", 300, 250);
        g.drawString("затем нажмите ESC, чтобы выйти из игры и сохранить",300,275);
        g.drawString("изменения",300,300);
        long length = (int) g.getFontMetrics().getStringBounds(list, g).getWidth();
        g.drawString(list, (int) (x + w / 2) - (int) (length / 2), (int) ( (h/3) * 2));
    }
    public Images()
    {
        player = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        bulletRight = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        bulletLeft = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        enemy = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        coin = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        spike = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        castle = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/Back/wp.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            player = ImageIO.read(getClass().getResourceAsStream("/Player/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        blocks = new BufferedImage[1];
        try {
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/Blocks/block_brick.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bulletRight = ImageIO.read(getClass().getResourceAsStream("/Sword/arrowLeft.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bulletLeft = ImageIO.read(getClass().getResourceAsStream("/Sword/arrowRight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            enemy = ImageIO.read(getClass().getResourceAsStream("/Enemies/Tower.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            coin = ImageIO.read(getClass().getResourceAsStream("/Coin/coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            spike = ImageIO.read(getClass().getResourceAsStream("/Spike/spike.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            castle = ImageIO.read(getClass().getResourceAsStream("/Castle/castle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
