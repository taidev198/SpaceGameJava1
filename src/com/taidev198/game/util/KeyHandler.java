package com.taidev198.game.util;

import com.taidev198.game.Game;
import com.taidev198.game.entity.Bullet;
import com.taidev198.game.entity.Entity;
import com.taidev198.game.graphics.BufferedImageLoader;
import com.taidev198.game.sound.SoundEffect;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.STATE;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class KeyHandler  implements KeyListener {

    private Game game;
    private BufferedImageLoader loader;

    public KeyHandler(Game game){
        game.addKeyListener(this);
        this.game = game;
        loader = new BufferedImageLoader();
        // Pre-load all the sound files
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.state == STATE.Start ){
            int event = e.getKeyCode();
            if (event == KeyEvent.VK_UP){
                game.player.getPos().setVelY(-5);
            }
            else if (event == KeyEvent.VK_DOWN){
                game.player.getPos().setVelY(5);
            }
            else if (event == KeyEvent.VK_RIGHT){
                game.player.getPos().setVelX(5);
            }
            else if (event == KeyEvent.VK_LEFT){
                game.player.getPos().setVelX(-5);
            }else if (event == KeyEvent.VK_SPACE){

                game.getSoundEffect().shot();
                BufferedImage ss  = loader.loadImage("/sheet/bullet.png");
                Bullet b = new Bullet(new Vector2f(game.player.getPos().getX() + 5,game.player.getPos().getY() -30), new SpriteSheet(ss),32);
                b.setBulletOfEnemy(false);
                game.controller.addBullet(b);

            }
            else if (event == KeyEvent.VK_P){
                game.setPause(true);
                System.out.println("pause");
            }
            else if (event == KeyEvent.VK_R){
                game.setPause(false);
                System.out.println("pause");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (game.state == STATE.Start ){
            int event = e.getKeyCode();
            if (event == KeyEvent.VK_UP){
                game.player.getPos().setVelY(0);
            }
            else if (event == KeyEvent.VK_DOWN){
                game.player.getPos().setVelY(0);
            }
            else if (event == KeyEvent.VK_RIGHT){
                game.player.getPos().setVelX(0);
            }
            else if (event == KeyEvent.VK_LEFT){
                game.player.getPos().setVelX(0);
            }
        }


    }

}
