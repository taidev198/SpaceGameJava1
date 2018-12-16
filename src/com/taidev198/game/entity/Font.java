package com.taidev198.game.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Font {

    private BufferedImage FONTSHEET = null;
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    private int wLetter;
    private int hLetter;

    public Font(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        FONTSHEET = loadFont(file);

        System.out.println("width sprite sheet"+FONTSHEET.getWidth());
        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;

        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }


    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wLetter = FONTSHEET.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hLetter = FONTSHEET.getHeight() / h;
    }

    public int getWidth() { return w; }
    public int getHeight() { return h; }

    private BufferedImage loadFont(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getResource(file));
            System.out.println("Loading: " + file + "...");
        } catch(Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadFontArray() {
        spriteArray = new BufferedImage[hLetter][wLetter];

        for (int x = 0 ; x < hLetter ; x++ ){
            for (int y = 0; y < wLetter ; y++){
                spriteArray[x][y] = getLetter(y,x);
            }
        }
    }

    public BufferedImage getFontSheet() {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y) {
        return FONTSHEET.getSubimage(x * w, y*h, w, h);
    }

    public BufferedImage getFont(char letter) {
        int value = letter;

        int x = value % wLetter;
        int y = value / wLetter;
        return getLetter(x, y);
    }
}
