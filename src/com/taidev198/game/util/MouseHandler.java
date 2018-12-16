package com.taidev198.game.util;

import com.taidev198.game.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;


    private Game game;

    public MouseHandler(Game game){
        game.addMouseListener(this);
    }

    public  int getMouseX() {
        return mouseX;
    }

    public  int getMouseY() {
        return mouseY;
    }

    public  int getMouseB() {
        return mouseB;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseB = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
        mouseY = -1;
        mouseX = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseDragged(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
    }
}
