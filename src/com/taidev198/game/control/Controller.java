package com.taidev198.game.control;

import com.taidev198.game.Game;
import com.taidev198.game.entity.Bullet;
import com.taidev198.game.entity.Enemy;
import com.taidev198.game.entity.Items;
import com.taidev198.game.graphics.Animation;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.Main;
import com.taidev198.game.state.STATE;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

import static com.taidev198.game.Game.SCALE;
public class Controller implements Main {

    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemies;
    private Game game;
    private Bullet templateBullet;
    private Enemy templateEnemy;
    private Items templateItem;
    private AABB aabb;
    private LinkedList<Items> items;
    public static int point;
    private int hit;
    private boolean isKilled;

    public Controller(Game game){
        this.game = game;
        bullets = new LinkedList<>();
        enemies = new LinkedList<>();
        items = new LinkedList<>();
        hit = 0;
    }


    @Override
    public void tick(){
        if (!game.isPause()){
            for (int i = 0; i < bullets.size() ; i++ ){
                templateBullet = bullets.get(i);
                if (templateBullet.getPos().getY() == 0)
                    removeBullet(templateBullet);
                templateBullet.tick();
            }

            for (int i = 0; i < enemies.size() ; i++ ){
                templateEnemy = enemies.get(i);
                if (templateEnemy.getPos().getY() == Game.HEIGHT * Game.SCALE)
                    removeEnemy(templateEnemy);
                templateEnemy.tick();
            }

            for (int i = 0; i < items.size() ; i++ ){
                templateItem = items.get(i);
                templateItem.tick();
            }
        }


    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
    }

    @Override
    public void render(Graphics g){
        for (Bullet bullet : bullets) {

            bullet.render(g);
            if (bullet.getPos().getY() == 0)
                removeBullet(bullet);
        }

        for (Enemy enemy : enemies) {

            enemy.render(g);

        }
        for (Items item : items) {
            item.render(g);
        }
    }

    public void addEnemy(Enemy enemy){
            enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemy);
    }

    public void addBullet(Bullet block){
        bullets.add(block);
    }

    public void removeBullet(Bullet block){
        bullets.remove(block);
    }

    public void addItem(Items item){
        items.add(item);
    }

    public void removeItem(Items item){
        items.remove(item);
    }
    public void collision(Vector2f posPlayer){
       
        aabb = new AABB(posPlayer,32,32);
        Iterator itr = enemies.iterator();
        while (itr.hasNext()) {
            Enemy temp = (Enemy) itr.next();
            aabb.setPosB(temp.getPos());
            if (aabb.isCollision()) {
                enemies.remove(temp);
                Game.HEALTH -= 5;
                if (Game.HEALTH == 0) {
                    game.player.setPos(new Vector2f(game.getWidth() / 2, game.getHeight() * SCALE / 2));
                    Game.HEALTH = 120;
                    enemies.clear();
                    game.setState(STATE.Pause);
                }

            }
        }

        //detected collision between bullets and enemies
        for (int j = 0 ; j < bullets.size(); j ++){
            templateBullet = bullets.get(j);
            aabb = new AABB(templateBullet.getPos(),32,32);
            for (int i = 0 ; i < enemies.size(); i++){
                templateEnemy= enemies.get(i);
                Animation ani = templateEnemy.getAni();
                if (!templateEnemy.isKilled() && templateEnemy != null){
                    aabb.setPosB(templateEnemy.getPos());
                    if (templateEnemy.isLevelUp()){
                        aabb.setWidthB(32*4);
                        aabb.setHeightB(32*3);
                        templateEnemy.setHitted(3);
                    }

                    if (point == 5){
                        game.setOnce(true);
                        game.setSpawn(false);
                    }

                    if (templateEnemy.isLevelUp())
                        System.out.println("Level up!");

                    if (aabb.isCollision()){
                        templateEnemy.setHitted(templateEnemy.getHitted() + 1);
                        if ((templateEnemy.getHitted() == 2 && !templateEnemy.isLevelUp()) ||
                                (templateEnemy.getHitted() == 3 )){
                            game.getSoundEffect().Explode();
                            templateEnemy.setKilled(true);
                            point++;
                            ani.setEnemies(enemies);
                            ani.setEnemy(templateEnemy);
                            ani.setKilled(true);
                            ani.setItems(items);
                        }
                            bullets.remove(templateBullet);

                    }
                }

            }
        }

        //detected collision between player and item;
        aabb = new AABB(posPlayer,32,32);
        for (int i = 0 ; i < items.size(); i++){
            aabb.setPosB(items.get(i).getPos());
            if (aabb.isCollision()){
                items.remove(items.get(i));
                game.getSoundEffect().gotItem();
                point += 2;
            }
        }
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    public int getSizeEnemy(){
        return enemies.size();
    }

    public void removeAllEnemies(){
        enemies.removeAll(enemies);
    }
}
