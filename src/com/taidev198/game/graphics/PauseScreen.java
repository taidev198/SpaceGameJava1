package com.taidev198.game.graphics;

import com.taidev198.game.Game;
import com.taidev198.game.state.Main;
import com.taidev198.game.state.STATE;
import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseScreen implements Main {

    private BufferedImage image;
    private Game game;
    private int w;
    private int h;

    private BufferedImage pause;
    private BufferedImage resume;
    private BufferedImage option;
    private BufferedImage menu;
    private BufferedImage exit;

    private BufferedImage settings;
    private BufferedImage onMusic;
    private BufferedImage offMusic;
    private BufferedImage restart;

    private int mouseX;
    private int mouseY;

    public PauseScreen( Game game,BufferedImage image, int w, int h) {
        this.image = image;
        this.game = game;
        this.w = w;
        this.h = h;
        setSprite();
    }

    public void setSprite(){
        menu = image.getSubimage(1*w,0*h,128,64);
        option = image.getSubimage(2*w,0*h,128,64);
        exit = image.getSubimage(3*w,0*h,128,64);
        resume=image.getSubimage(2*w,1*h,128,64);


        onMusic = getGrap(image,0,2,64,64);
        offMusic = getGrap(image,1,2,64,64);
        pause= getGrap(image,2,2,64,64);
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
                game.setPause(false);
            }

        }
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >= y*2 && mouseY <= y*2 +h)
                game.setState(STATE.Menu);
        }
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >=y*3 && mouseY <= y*3 +h)
                game.setState(STATE.Option);
        }
        if ((mouseX >= game.getWidth()/2 - h )&& mouseX <=game.getWidth()/2 - h + 120){
            if (mouseY >=y*4 && mouseY <= y*4 +h)
                game.setState(STATE.HighScore);
        }

        //option

        if ((mouseX >= game.getWidth()/2 - h*2 )&& mouseX <=(game.getWidth()/2 - h*2 + 60)){
            if (mouseY >=y*5 && mouseY <= y*5 +h)
                System.out.println("on music");
        }
        if ((mouseX >= game.getWidth()/2 - h/2 )&& mouseX <=game.getWidth()/2 - h/2 + 60){
            if (mouseY >=y*5 && mouseY <= y*5 +h)
                System.out.println("settings");
        }
        if ((mouseX >= game.getWidth()/2 + h )&& mouseX <=game.getWidth()/2 + h + 60){
            if (mouseY >=y*5 && mouseY <= y*5 +h)
                System.out.println("restart");
        }
    }

    @Override
    public void render(Graphics g) {
        int y = game.getHeight() / 3;
        g.drawImage(resume,game.getWidth()/2 - h,y ,null);
        g.drawImage(menu,game.getWidth()/2 - h,y+h,null);
        g.drawImage(option,game.getWidth()/2 - h,y + h*2,null);
        g.drawImage(exit,game.getWidth()/2 - h,y + h*3 ,null);
        //option
        g.drawImage(onMusic,game.getWidth()/2 - h*2,y + h*4,null);
        g.drawImage(settings,game.getWidth()/2  - h/2,y + h*4,null);
        g.drawImage(restart,game.getWidth()/2 + h,y + h*4,null);
    }
}
