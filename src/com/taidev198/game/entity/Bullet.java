package com.taidev198.game.entity;

import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet {

    private Vector2f pos;
    private BufferedImage bullet;
    public Bullet(Vector2f pos, BufferedImage image){
        this.pos = pos;
        SpriteSheet ss = new SpriteSheet(image);
        bullet = ss.grabImage(2,1,32,32);
    }

    public void tick(){
        pos.setY(pos.getY() - 3);
    }

    public void render(Graphics g){
        g.drawImage(bullet,(int) pos.getX(),(int) pos.getY(),null);
    }

    public Vector2f getPos() {
        return pos;
    }
}
