package com.taidev198.game.sprite;

import com.taidev198.game.entity.Font;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;

    private static Font currentFont;

    public SpriteSheet(BufferedImage image){
        this.image = image;
        w = TILE_SIZE;
        h = TILE_SIZE;
        wSprite = image.getWidth() / w;
        hSprite = image.getHeight() / h;
        loadSpriteArray();
    }

    public SpriteSheet(BufferedImage image,int w, int h){
        this.image = image;
        this.w = w;
        this.h = h;
        wSprite = image.getWidth() / w;
        hSprite = image.getHeight() / h;
        loadSpriteArray();
    }

    public SpriteSheet( int row, int level,BufferedImage image){
        this.image =image;
        if (level != 5){
            w = TILE_SIZE * 4;
            h = TILE_SIZE * 3;
        }else {
            w = TILE_SIZE;
            h = TILE_SIZE;
        }
       // loadRowSpriteArray(row);
    }

    public void loadRowSpriteArray(int row){
        spriteArray = new BufferedImage[hSprite][wSprite];
            for (int y = 0; y < wSprite ; y++){
                spriteArray[row][y] = getSprite(y +1,row+1,w,w);
        }
    }

    public void loadSpriteArray(){

        spriteArray = new BufferedImage[hSprite][wSprite];
        for (int x = 0 ; x < hSprite ; x++ ){
            for (int y = 0; y < wSprite ; y++){
                spriteArray[x][y] = getSprite(y +1,x+1,w,w);
            }
        }
    }

    private BufferedImage getSprite(int x, int y,int width, int height) {
        return image.getSubimage((x * w) - w, (y * w) - w,height,width);
    }

    public BufferedImage[][] getSpriteArray(){
        return spriteArray;
    }

    public BufferedImage grabImage(int col, int row,int width, int height){
        return image.getSubimage((col * w) - w, (row * h) - h, width,height);
    }

    public static void drawArray(Graphics g, String word, Vector2f pos, int size, int xOffset) {
        drawArray(g, currentFont, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics g, Font f, String word, Vector2f pos, int size, int xOffset) {
        drawArray(g, f, word, pos, size, size, xOffset, 0);
    }

    public static void drawArray(Graphics g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        currentFont = f;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != 32)
                g.drawImage(f.getFont(word.charAt(i)), (int) x, (int) y, width, height, null);
            x += xOffset;
            y += yOffset;
        }
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }
}
