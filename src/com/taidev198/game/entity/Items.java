package com.taidev198.game.entity;

import com.taidev198.game.Game;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.Main;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;

import java.awt.*;

public class Items extends Entity implements Main {

    private Vector2f pos;

    public Items(Vector2f pos, SpriteSheet spriteSheet, int size){
        super(pos,size,spriteSheet);
        this.pos = pos;
    }


    @Override
    public void tick() {
        super.tick();
//        if (pos.getY() >= Game.HEIGHT - 100)
//        pos.setY(pos.getY() +1);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

    }

    @Override
    public void render(Graphics g) {
        super.setNumFrames(7);
        g.drawImage(getAni().getImageAni(3),(int)pos.x,(int)pos.y,null);
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }
}
