package com.taidev198.game;


import com.taidev198.game.audio.Audio;
import com.taidev198.game.control.Controller;
import com.taidev198.game.entity.Bullet;
import com.taidev198.game.entity.Enemy;
import com.taidev198.game.entity.Player;
import com.taidev198.game.graphics.BufferedImageLoader;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;
import javafx.scene.media.AudioClip;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 2;
    public final String TITLE = "2D Space Game";

    public Thread thread;
    public boolean running;

    private BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet;
    private BufferedImage bg;

    private Player player;
    private Controller controller;
    private static int y = 200;

    private AudioClip backgroundClip;
    private Audio audio;

    public void init(){
        BufferedImageLoader loader = new BufferedImageLoader();
        spriteSheet = loader.loadImage("/sheet/spritesheet.png");


        player = new Player(new Vector2f(getWidth() /2 ,getHeight() * SCALE/2),spriteSheet);
        controller = new Controller(this);

        bg = loader.loadImage("/background.png");

        addKeyListener(new KeyHandler(this));
        addMouseListener(new MouseHandler(this));
//        backgroundClip = new AudioClip("audio/spaceTrash1.wav");
//        audio = new Audio(backgroundClip);
//        audio.playSound();

    }

    public synchronized void start(){
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;
        running = false;

        try {
            thread.join();
            System.out.println("thanh tai nguyen");
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
         System.exit(1);

    }

    @Override
    public void run() {
        requestFocus();

        init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta > 1){
                tick();
                delta--;
                updates++;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println(updates + "Ticks,fps" + frames);
                Random ran = new Random();
                for (int i = 1; i < 4; i++){
                    int x = ran.nextInt(620) + 10;
                    controller.addEnemy(new Enemy(new Vector2f(x,ran.nextInt(10) +1),spriteSheet),x);
                }
                frames = 0;
                updates = 0;
            }
        }

        stop();
    }

    private void tick() {
        player.tick();
        controller.tick();
        controller.collison(player.getPos());

    }

    private void render(){

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(bufferedImage,0,0,getWidth(),getHeight(),null);
        g.drawImage(bg,0,0,getWidth(),getHeight(),null);
        player.render(g);
        controller.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String...args){
        Game game = new Game();

        game.setPreferredSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
        game.setMaximumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));
        game.setMinimumSize(new Dimension(WIDTH * SCALE , HEIGHT * SCALE));

        JFrame frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        game.start();
    }


    public void keyPressed(KeyEvent e) {
        int event = e.getKeyCode();
       if (event == KeyEvent.VK_UP){
           player.getPos().setVelY(-5);
       }
        else if (event == KeyEvent.VK_DOWN){
           player.getPos().setVelY(5);
        }
        else if (event == KeyEvent.VK_RIGHT){
           player.getPos().setVelX(5);
        }
        else if (event == KeyEvent.VK_LEFT){
           player.getPos().setVelX(-5);
        }else if (event == KeyEvent.VK_SPACE){
           
           controller.addBullet(new Bullet(new Vector2f(player.getPos().getX(),player.getPos().getY() -30),spriteSheet));

       }
    }


    public void keyReleased(KeyEvent e) {
        int event = e.getKeyCode();
        if (event == KeyEvent.VK_UP){
            player.getPos().setVelY(0);
        }
        else if (event == KeyEvent.VK_DOWN){
            player.getPos().setVelY(0);
        }
        else if (event == KeyEvent.VK_RIGHT){
            player.getPos().setVelX(0);
        }
        else if (event == KeyEvent.VK_LEFT){
            player.getPos().setVelX(0);
        }
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }
}
