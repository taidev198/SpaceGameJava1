package com.taidev198.game.entity;

import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy {
    private Vector2f pos;
    private BufferedImage enemy;
    public Enemy(Vector2f pos, BufferedImage image){
        this.pos = pos;
        SpriteSheet ss = new SpriteSheet(image);
        enemy = ss.grabImage(3,1,32,32);
    }

    public void tick(){
        pos.setY(pos.getY() + 1);
    }

    public void render(Graphics g){
        g.drawImage(enemy,(int) pos.getX(),(int) pos.getY(),null);
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }
}
