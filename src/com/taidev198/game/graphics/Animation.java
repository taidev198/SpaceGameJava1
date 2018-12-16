package com.taidev198.game.graphics;

import com.taidev198.game.Game;
import com.taidev198.game.control.Controller;
import com.taidev198.game.entity.Enemy;
import com.taidev198.game.entity.Items;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.util.Vector2f;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Animation {

    private BufferedImage[][] frames;
    private int timePlayed;

    private int currentFrame;
    private int count;

    private int delay;
    private int numFrames;

    private boolean isMatched;
    private LinkedList<Enemy> enemies;
    private LinkedList<Items> items;
    private LinkedList<Items> item;
    private Enemy enemy;
    private boolean isKilled;

    public Animation(BufferedImage[] frames){
        timePlayed = 0;
    }

    public Animation(){
        timePlayed = 0;
    }

    private void setFrame(BufferedImage[] images) {
        //this.frames = images;
        count = 0;
        numFrames = images.length;
        delay = 0;
        currentFrame = 0;
    }

    public void setDelay(int i) { delay = i;}
    public void setFrame(int i) { currentFrame = i;}
    public void setNumFrames(int i) { numFrames = i; }
    public  int getNumframes(){ return numFrames; }

    public void setFrames(BufferedImage[][] frames) {
        this.frames = frames;
    }

    public void tick(){
        if (delay == -1) return;
        count ++;

        if (count == delay){
            isMatched =false;
            currentFrame++ ;
            count = 0;
        }

        if (currentFrame == numFrames){
            if (isKilled){
                isMatched = true;
                remove(enemies,enemy);
            }
            currentFrame = 0;
            timePlayed++;
        }
    }

    public int getDelay() { return  delay; }
    public int getFrame() { return  currentFrame; }
    public int getCount() { return count; }

    public BufferedImage getImageAni(int row) {

        return frames[row][currentFrame];
    }

    public BufferedImage getRowImageAni(int col){
        return frames[currentFrame][col];
    }
    public BufferedImage getColImageAni(){
        return frames[currentFrame][0];
    }
    public boolean hasPlayedOnce() { return timePlayed > 0; }
    public boolean hasPlayed(int i) { return timePlayed == i; }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public void remove(LinkedList<Enemy> enemies, Enemy enemy){
        if (isMatched()){
            float x = enemy.getPos().getX();
            float y = enemy.getPos().getY();
            enemies.remove(enemy);
            addItem(new Items(new Vector2f(x,y),new SpriteSheet(Game.getSpriteSheet()),32));
        }
    }

    public void setEnemies(LinkedList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
    public void setKilled(boolean isKilled){
        this.isKilled = true;
    }
    public boolean isKilled(){ return this.isKilled; }

    public LinkedList<Items> getItems() {
        return items;
    }

    public void setItems(LinkedList<Items> items) {
        this.items = items;
    }

    public LinkedList<Items> getItem() {
        return item;
    }

    public void setItem(LinkedList<Items> item) {
        this.item = item;
    }

    public void addItem(Items item){
        items.add(item);
    }

}
