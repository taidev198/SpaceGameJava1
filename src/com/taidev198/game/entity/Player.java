package com.taidev198.game.entity;

import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private double x;
    private double y;

    private Vector2f pos;
    private BufferedImage player;
    public Player(Vector2f pos, BufferedImage image){
        this.pos = pos;
        SpriteSheet ss = new SpriteSheet(image);
        player = ss.grabImage(1,1,32,32);
    }

    public Vector2f getPos(){
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void tick(){
        pos.x += pos.getVelX();
        pos.y += pos.getVelY();

        if (pos.getX() <= 0)
            pos.setX(0);
        else if (pos.getX() > 640 - 16)
            pos.setX(640 - 16);
        if (pos.getY() <= 0)
            pos.setY(0);
        if (pos.getY() >= 480 -32 )
            pos.setY(480 - 32);

    }

    public void render(Graphics g){
        g.drawImage(player,(int)pos.x,(int)pos.y,null);

    }
}
