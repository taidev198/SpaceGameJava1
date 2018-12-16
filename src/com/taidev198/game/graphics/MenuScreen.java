package com.taidev198.game.graphics;

import com.taidev198.game.Game;

import com.taidev198.game.control.Controller;
import com.taidev198.game.entity.Font;
import com.taidev198.game.sprite.SpriteSheet;
import com.taidev198.game.state.Main;
import com.taidev198.game.state.STATE;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;
import com.taidev198.game.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuScreen implements Main {

    private Game game;
    private BufferedImage image;

    private BufferedImage start;
    private BufferedImage option;
    private BufferedImage menu;
    private BufferedImage setting;
    private BufferedImage highscore;
    private BufferedImage exit;
    private BufferedImage back;

    private BufferedImage onMusic;
    private BufferedImage offMusic;
    private BufferedImage pause;
    private BufferedImage resume;
    private BufferedImage next;
    private BufferedImage backIcon;
    private BufferedImage settings;
    private BufferedImage restart;

    private Font font;

    private int w;
    private int h;
    public MenuScreen(Game game, BufferedImage image, int w, int h){
        this.game = game;
        this.image = image;
        this.h = h;
        this.w = w;
        setSprite();
        font = new Font("/font/font.png",10,10);
    }

    private void setSprite() {
        this.start = image.getSubimage(0*w,0*h,128,64);
        menu = image.getSubimage(1*w,0*h,128,64);
        option = image.getSubimage(2*w,0*h,128,64);
        highscore = image.getSubimage(0*w,1*h,128,64);
        exit = image.getSubimage(3*w,0*h,128,64);
        back = image.getSubimage(1*w,1*h,128,64);


        onMusic = getGrap(image,0,2,64,64);
        offMusic = getGrap(image,1,2,64,64);
        pause= getGrap(image,2,2,64,64);
        resume= getGrap(image,3,2,64,64);
        next= getGrap(image,4,2,64,64);
        backIcon= getGrap(image,5,2,64,64);
        settings= getGrap(image,6,2,64,64);
        restart= getGrap(image,7,2,64,64);

    }

    public BufferedImage getGrap(BufferedImage image, int x, int y, int width, int height){
        return image.getSubimage(x*width,y*h,width,height);
    }

    @Override
    public void tick() {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {

        int y = game.getHeight() / 3;
        int mouseX = mouse.getMouseX();
        int mouseY = mouse.getMouseY();
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >=y && mouseY <= y +h){
                game.setState(STATE.Start);
            }
        }
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >= y*2 && mouseY <= y*2 +h)
                game.setState(STATE.Pause);
        }
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >=y*3 && mouseY <= y*3 +h)
                game.setState(STATE.Option);
        }
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >=y*4 && mouseY <= y*4 +h)
                game.setState(STATE.HighScore);
        }
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >=y*5 && mouseY <= y*5 +h)
                System.out.println("Exit");
        }

        //option

        if ((mouseX >= game.getWidth()/2 - h*2 )&& mouseX <=(game.getWidth()/2 - h*2 + 60)){
            if (mouseY >=y*6 && mouseY <= y*6 +h)
                System.out.println("on music");
        }
        if ((mouseX >= game.getWidth()/2 - h/2 )&& mouseX <=game.getWidth()/2 - h/2 + 60){
            if (mouseY >=y*6 && mouseY <= y*6 +h)
                System.out.println("settings");
        }
        if ((mouseX >= game.getWidth()/2 + h )&& mouseX <=game.getWidth()/2 + h + 60){
            if (mouseY >=y*6 && mouseY <= y*6 +h)
                System.out.println("restart");
        }
    }

    @Override
    public void render(Graphics g) {
        int y = game.getHeight() / 3;
        g.drawImage(start,game.getWidth()/2 - h,y,null);
        g.drawImage(menu,game.getWidth()/2 - h,y + h,null);
        g.drawImage(option,game.getWidth()/2 - h,y+h *2,null);
        g.drawImage(highscore,game.getWidth()/2 - h,y +h *3,null);
        g.drawImage(exit,game.getWidth()/2 - h,y + h*4,null);
        //option
        g.drawImage(onMusic,game.getWidth()/2 - h*2,y + h*5,null);
        g.drawImage(settings,game.getWidth()/2  - h/2,y + h*5,null);
        g.drawImage(restart,game.getWidth()/2 + h,y + h*5,null);
    }
}
