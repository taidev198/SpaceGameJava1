package com.taidev198.game.control;

import com.taidev198.game.Game;
import com.taidev198.game.entity.Bullet;
import com.taidev198.game.entity.Enemy;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.util.LinkedList;


public class Controller {

    private LinkedList<Bullet> bullets;
    private LinkedList<Enemy> enemies;
    private Game game;
    private Bullet templateBullet;
    private Enemy templateEnemy;
    private AABB aabb;

    public Controller(Game game){
        this.game = game;
        bullets = new LinkedList<>();
        enemies = new LinkedList<>();
    }

    public void tick(){
        for (int i = 0; i < bullets.size() ; i++ ){
            templateBullet = bullets.get(i);
            if (templateBullet.getPos().getY() <= 0)
                removeBullet(templateBullet);
            templateBullet.tick();
        }
        for (int i = 0; i < enemies.size() ; i++ ){
            templateEnemy = enemies.get(i);
            if (templateEnemy.getPos().getY() >= ((320 /12 *9)*2 ))
                removeEnemy(templateEnemy);
            templateEnemy.tick();
        }

    }

    public void render(Graphics g){
        for (int i = 0; i < bullets.size() ; i++ ){
            templateBullet = bullets.get(i);
            templateBullet.render(g);
        }

        for (int i = 0; i < enemies.size() ; i++ ){
            templateEnemy = enemies.get(i);
            templateEnemy.render(g);
        }
    }

    public void addEnemy(Enemy enemy,int numEnemy){
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

    public void collison(Vector2f posPlayer){

        aabb = new AABB(posPlayer,32,32);
        for (int i = 0 ; i < enemies.size(); i++){
            aabb.setPosB(enemies.get(i).getPos());
            if (aabb.isCollision())
                enemies.remove(enemies.get(i));
        }
        //detected collision between bullets and enemies
        for (int j = 0 ; j < bullets.size(); j ++){
            aabb = new AABB(bullets.get(j).getPos(),32,32);
            for (int i = 0 ; i < enemies.size(); i++){
                aabb.setPosB(enemies.get(i).getPos());
                if (aabb.isCollision()){
                    bullets.remove(bullets.get(j));
                    enemies.remove(enemies.get(i));
                }

            }
        }

    }
}
