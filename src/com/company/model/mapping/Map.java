package com.company.model.mapping;

import com.company.model.objects.Block;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {
    private final String path;
    private Block[][] blocks;
    public Map(String loadPath) {
        path = loadPath;
        loadMap();
    }
    public void draw(Graphics g) {
        for(int i = 0;i < blocks.length;i++) {
            for(int j = 0;j < blocks[0].length;j++) {
                blocks[i][j].draw(g);
            }
        }
    }
    public void loadMap() {
        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int width;
        int height;
        try {
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());

            blocks = new Block[height][width];

            for(int y = 0;y < height;y++) {
                String line = br.readLine();
                String[] tokens = line.split("\\s+");
                for(int x = 0;x < width;x++) {
                    blocks[y][x] = new Block(x * Block.blockSize, y * Block.blockSize, Integer.parseInt(tokens[x]));
                }
            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Block[][] getBlocks()
    {
        return blocks;
    }
}
