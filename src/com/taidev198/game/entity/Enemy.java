package com.taidev198.game.entity;

import com.taidev198.game.Game;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.Main;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity implements Main {
    private Vector2f pos;
    private BufferedImage enemy;

    protected SpriteSheet spriteSheet;
    Bullet b;
    private boolean isKilled;
    private int hitted;
    private boolean isLevelUp;

    public Enemy(Vector2f pos, SpriteSheet spriteSheet, int size){
        super(pos,size,spriteSheet);
        this.pos = pos;
        this.spriteSheet = spriteSheet;
        enemy = spriteSheet.grabImage(3,1,32,32);
        b =  new Bullet(new Vector2f(getPos().getX(),getPos().getY() +30), Game.getSpriteSheet());
        b.setBulletOfEnemy(true);
        isLevelUp = false;
    }

    public Enemy(Vector2f pos, SpriteSheet spriteSheet, int width, int height, int row, int col, int level){
        super(pos,width,spriteSheet);
        this.pos = pos;
        this.spriteSheet = spriteSheet;
        spriteSheet.setW(width);
        spriteSheet.setH(height);
        enemy = spriteSheet.grabImage(1,1,width,height);
        isLevelUp = true;
    }

    @Override
    public void tick(){

        if (!isKilled && !isPause && hitted != 2) {

            if (!isLevelUp){
                b.tick();
                pos.setY(pos.getY() +1);
            }

        }

        else {
                super.tick();
                pos.setY(pos.getY());
                setHitted(0);
        }

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics g){
        if (getPos().getY() <= Game.HEIGHT && !isLevelUp) {
            b.render(g);
        }
        if (!isKilled && !isPause && hitted != 2) {
            g.drawImage(enemy,(int) pos.getX(),(int) pos.getY(),null);
        }
        else {
            if (!isLevelUp){
                //setDelay(2);
                super.setNumFrames(4);
                g.drawImage(getAni().getImageAni(1),(int) pos.getX(),(int) pos.getY(),null);
            }
            //setDelay(2);
//            System.out.println("thanh tai nguyen");
//            super.setNumFrames(4);
//            g.drawImage(getAni().getImageAni(1),(int) pos.getX(),(int) pos.getY(),null);
        }

    }


    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void setKilled(boolean killed) {
        isKilled = killed;
    }

    public Rectangle getBound(){
        return new Rectangle((int)pos.x,(int)pos.y,32,32);
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public int getHitted() {
        return hitted;
    }

    public void setHitted(int hitted) {
        this.hitted = hitted;
    }

    public boolean isLevelUp() {
        return isLevelUp;
    }

    public void setLevelUp(boolean levelUp) {
        isLevelUp = levelUp;
    }
}
