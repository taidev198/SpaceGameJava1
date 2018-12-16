package com.taidev198.game.entity;

import com.taidev198.game.Game;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.Main;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity implements Main {

    private Vector2f pos;

    public Player(Vector2f pos, SpriteSheet spriteSheet,int size){
        super(pos,size,spriteSheet);
        this.pos = pos;
    }

    public Vector2f getPos(){
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void tick(){
        if (!isPause){
            super.tick();
            pos.x += pos.getVelX();
            pos.y += pos.getVelY();

            if (pos.getX() <= 0)
                pos.setX(0);
            else if (pos.getX() > Game.WIDTH * Game.SCALE - 16)
                pos.setX(Game.WIDTH * Game.SCALE - 16);
            if (pos.getY() <= 0)
                pos.setY(0);
            if (pos.getY() >= Game.HEIGHT * Game.SCALE -32 )
                pos.setY(Game.HEIGHT * Game.SCALE - 32);
        }
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    public void render(Graphics g){
        super.setNumFrames(4);
        g.drawImage(getAni().getImageAni(2),(int)pos.x,(int)pos.y,null);
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
}
