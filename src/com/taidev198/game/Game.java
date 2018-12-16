package com.taidev198.game;



import com.taidev198.game.control.Controller;
import com.taidev198.game.entity.*;
import com.taidev198.game.entity.Font;
import com.taidev198.game.graphics.BufferedImageLoader;
import com.taidev198.game.graphics.GameOverScreen;
import com.taidev198.game.graphics.MenuScreen;
import com.taidev198.game.graphics.PauseScreen;
import com.taidev198.game.sound.SoundEffect;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.STATE;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;

import javax.sound.midi.Sequencer;
import javax.sound.sampled.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 320;
    public static final int HEIGHT = 450;
    public static final int SCALE = 2;
    private final String TITLE = "2D Space Game";
    public static int HEALTH = 120;
    private SoundEffect soundEffect;
    private Thread thread;
    private boolean running;

    private BufferedImage bufferedImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private static BufferedImage spriteSheet;
    private BufferedImage bg;

    public Player player;
    public Controller controller;
    public  STATE state = STATE.Menu;
    private MouseHandler mouse;
    private KeyHandler key;
    private Font font;

    private MenuScreen menuScreen;
    private PauseScreen pauseScreen;
    private SplashScreen splashScreen;
    private GameOverScreen gameOverScreen;

    private BufferedImageLoader loader;
    private BufferedImage pauseBtn;
    private BufferedImage healthBarSpriteSheet;

    private BufferedImage bullet;
    private BufferedImage coin;
    private Items items;

    private boolean isPause;
    private static int LEVEL1 = 20;
    private static int LEVEL2 = 100;
    private static int LEVEL3 = 150;
    public AudioClip clip;
    private boolean isOnce;
    private boolean isSpawn;

    public int y;
    Clip clip1 ;
    private void init(){


        URL url = this.getClass().getClassLoader().getResource("/audio/background1.wav");
        // Set up an audio input stream piped from the sound file.
        AudioInputStream audioInputStream ;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("/audio/background1.wav"));
            // Get a clip resource.
            clip1 = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip1.open(audioInputStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        isSpawn = true;
        isPause = false;
        isOnce = false;
        loader = new BufferedImageLoader();
        bullet = loader.loadImage("/sheet/bullet.png");
        spriteSheet = loader.loadImage("/sheet/spritesheet.png");

        soundEffect = new SoundEffect();
        items = new Items(new Vector2f(getWidth() /2 ,getHeight()  /2),new SpriteSheet(spriteSheet),32);
        player = new Player(new Vector2f(getWidth() /2 ,getHeight() *SCALE ),new SpriteSheet(spriteSheet),32);
        controller = new Controller(this);

        bg = loader.loadImage("/background.png");
        healthBarSpriteSheet = loader.loadImage("/sheet/healthbar.png");

        key = new KeyHandler(this);
        mouse = new MouseHandler(this);
        //set font
        font = new Font("/font/font.png",10,10);

        pauseBtn = loader.loadImage("/sheet/menuSprite1.png").getSubimage(2*64,2*64,64,64);
        menuScreen = new MenuScreen(this,loader.loadImage("/sheet/menuSprite1.png"),128,64);
        pauseScreen = new PauseScreen(this,loader.loadImage("/sheet/menuSprite1.png"),128,64);
    }

    public  void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public  void stop(){

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
        this.requestFocus();
        init();

        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1_000_000_000L / amountOfTicks;
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
                if (!isPause && state == STATE.Start){
                    if ( controller.point <= 5 && !isOnce && isSpawn){

                        Random ran = new Random();
                        for (int i = 1; i < 4; i++){
                            int x = ran.nextInt(getWidth() -32);
                            controller.addEnemy(new Enemy(new Vector2f(x,ran.nextInt(10) +1),new SpriteSheet(spriteSheet),32));
                        }
                    }
                      if ( isOnce && !isSpawn){
                        BufferedImage ss  = loader.loadImage("/sheet/enemy.png");
                        Enemy enemy = new  Enemy(new Vector2f(30,100), new SpriteSheet(0,1,ss),32*4,32*3,0,0,1);
                        enemy.setLevelUp(true);
                        enemy.setHitted(3);
                        System.out.println("big enemy");
                        controller.addEnemy(enemy);
                        isOnce = false;
                        isSpawn = true;
                    }
                }

                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void tick() {
        switch (state){
            case Start:
                player.tick();
                controller.tick();
                controller.collision(player.getPos());
                break;
            case Menu:
                menuScreen.tick();
                break;
            case Pause:
                pauseScreen.tick();
                break;
        }
        input(mouse,key);
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        //background

        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        if (state == STATE.Menu) {

            menuScreen.render(g);
        }
        else if (state == STATE.Start ) {

            clip1.loop(Sequencer.LOOP_CONTINUOUSLY);

             g.drawImage(bufferedImage, 0, 0, getWidth(), getHeight(), null);
             String fps = " SPACE GAME";
             SpriteSheet.drawArray(g, font, fps, new Vector2f(getWidth() / 2 - fps.length() * 16, 10), 32, 32);
             g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

             String point1 = Controller.point + " ";
             SpriteSheet.drawArray(g, point1, new Vector2f(point1.length() - 1, 10), 32, 24);

             //   healthBar = healthBarSpriteSheet.getSubimage(0, y, 280, 45);
            // g.drawImage(healthBar, 0, 30, null);
            g.setColor(Color.GRAY);
            g.fillRect(0, 40, 120, 20);
            g.setColor(Color.GREEN);
            g.fillRect(0, 40, HEALTH , 20);
            g.setColor(Color.BLACK);
            g.drawRect(0, 40, 120, 20);
             g.drawImage(pauseBtn, getWidth() - 64, 0, null);

             controller.render(g);
             player.render(g);
        }
        else if (state == STATE.Pause){
                pauseScreen.render(g);
            }

//            case Option:
//                player.render(g);
//            break;
//            case HighScore:
//                break;
//            case Exit:
//                break;
//            case OnMusic:
//                break;
//            case Restart:
//                break;
//            case Settings:
//                break;
//
            String fps1 = " SPACE GAME";
            SpriteSheet.drawArray(g, font, fps1, new Vector2f(getWidth() / 2 - fps1.length() * 16, 10), 32, 32);
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

    public static BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void input(MouseHandler mouse, KeyHandler key){
        switch (state){
            case Pause:pauseScreen.input(mouse,key);
            break;
            case Menu:menuScreen.input(mouse,key);
            break;
            case Start:player.input(mouse,key);
            int mx = mouse.getMouseX();
            int my = mouse.getMouseY();

            if (mx >= getWidth() -64 && mx <= getWidth() - 32){
                if (my >= 0 && my <= 64) {
                    setPause(true);
                    setState(STATE.Pause);
                }
            }
            break;

        }
    }

    public STATE getState() {
        return state;
    }

    public synchronized void setState(STATE state) {
        this.state = state;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return y;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        player.setPause(pause);
        isPause = pause;
    }

    public SoundEffect getSoundEffect() {
        return soundEffect;
    }

    public boolean isOnce() {
        return isOnce;
    }

    public void setOnce(boolean once) {
        isOnce = once;
    }

    public boolean isSpawn() {
        return isSpawn;
    }

    public void setSpawn(boolean spawn) {
        isSpawn = spawn;
    }
}
