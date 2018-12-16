package com.taidev198.game.entity;

import com.taidev198.game.graphics.Animation;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.Main;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity implements Main {

    protected boolean isPause;

    private int numFrames;

    private Animation ani;
    private Vector2f pos;
    private int size;
    private SpriteSheet spriteSheet;

    private int delay;


    public Entity(Vector2f pos, int size, SpriteSheet spriteSheet) {
        this.pos = pos;
        this.size = size;
        this.spriteSheet = spriteSheet;

        ani = new Animation();
        setAnimation(1,spriteSheet.getSpriteArray(),1);
        delay = 5;

//        SoundEffect.init();
//        SoundEffect.volume = SoundEffect.Volume.LOW;  // un-mute
    }

    public Entity() {
//        SoundEffect.init();
//        SoundEffect.volume = SoundEffect.Volume.LOW;  // un-mute
    }

    private void setAnimation(int i, BufferedImage[][] frames, int delay) {
        ani.setNumFrames(getNumFrames());
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    public void setNumFrames(int numFrames) {
        this.numFrames = numFrames;
    }

    public int getNumFrames() {
        return numFrames;
    }

    public void animate() {
        setAnimation(1,spriteSheet.getSpriteArray(),delay);
    }

    public int getSize() { return size; }
    public void tick(){
        animate();
        ani.tick();
    }
    public Animation getAni(){ return ani; }
    public boolean getIsMatched(){ return ani.isMatched(); }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Rectangle getBound(){
        return new Rectangle((int)pos.x,(int)pos.y,32,32);
    }
}
