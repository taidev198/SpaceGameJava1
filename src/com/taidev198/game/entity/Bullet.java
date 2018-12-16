package com.taidev198.game.entity;

import com.taidev198.game.graphics.BufferedImageLoader;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity{

    private Vector2f pos;
    private BufferedImage bullet;
    private BufferedImage bulletSpriteSheet;
    private BufferedImageLoader loader;
    private boolean bulletOfEnemy;

    private enum BULLET{
        lv1,lv11,lv12,lv13,lv14,lv16,
        lv17,lv18,lv19,
        lv2,
        lv3,
        lv4,
        lv5,
        lv6,
        lv7,
        lv8
    }

    private BULLET bulletLevel;

    public Bullet(Vector2f pos, BufferedImage image){
        super();
        this.pos = pos;
        SpriteSheet ss = new SpriteSheet(image);
        bullet = ss.grabImage(4,1,32,32);
    }

    public Bullet(Vector2f pos, SpriteSheet spriteSheet, int size){
        super(pos,size, spriteSheet);
        this.pos = pos;
    }

    public Bullet(Vector2f pos){
      //  super(pos,32,getBulletSpriteSheet());



    }

    @Override
    public void tick(){
        if (!isPause){
            if (bulletOfEnemy)
            pos.setY(pos.getY() + 5);
            else {
                super.tick();
                pos.setY(pos.getY() - 5);
            }
        }

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    public void render(Graphics g){
        if (bullet != null)
        g.drawImage(bullet,(int) pos.getX(),(int) pos.getY(),null);
        else {
            super.setNumFrames(4);
            g.drawImage(getAni().getImageAni(4),(int)pos.x,(int)pos.y,null);
        }

//        switch (bulletLevel){
//            case lv2:
//                super.setNumFrames(4);
//                g.drawImage(getAni().getImageAni(0),(int)pos.x,(int)pos.y,null);
//                break;
//            case lv3:
//                super.setNumFrames(4);
//                g.drawImage(getAni().getImageAni(1),(int)pos.x,(int)pos.y,null);
//                break;
//            case lv4:
//                super.setNumFrames(4);
//                g.drawImage(getAni().getImageAni(2),(int)pos.x,(int)pos.y,null);
//                break;
//            case lv5:
//                super.setNumFrames(4);
//                g.drawImage(getAni().getImageAni(3),(int)pos.x,(int)pos.y,null);
//                break;
//            case lv6:
//                super.setNumFrames(4);
//                g.drawImage(getAni().getImageAni(4),(int)pos.x,(int)pos.y,null);
//                break;
//            case lv7:
//                super.setNumFrames(6);
//                g.drawImage(getAni().getImageAni(6),(int)pos.x,(int)pos.y,null);
//                break;
//            case lv8:
//                super.setNumFrames(4);
//                g.drawImage(getAni().getImageAni(7),(int)pos.x,(int)pos.y,null);
//                break;
//        }
    }

    public Vector2f getPos() {
        return pos;
    }

    public Rectangle getBound(){
        return new Rectangle((int)pos.x,(int)pos.y,32,32);
    }

    public BufferedImage getBulletSpriteSheet(){
        loader = new BufferedImageLoader();
        return bulletSpriteSheet = loader.loadImage("/sheet/bullet.png");
    }

    public boolean isBulletOfEnemy() {
        return bulletOfEnemy;
    }

    public void setBulletOfEnemy(boolean bulletOfEnemy) {
        this.bulletOfEnemy = bulletOfEnemy;
    }
}
