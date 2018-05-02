package com.taidev198.game.control;

import com.taidev198.game.util.Vector2f;

public class AABB {

    private Vector2f posA;
    private Vector2f posB;
    private int width;
    private int height;
    public AABB(Vector2f posA,int width, int height){
        this.posA = posA;
        this.width = width;
        this.height = height;
    }

    public boolean isCollision(){

//        if (Math.max(posA.getX() + width,posA.getX()) >= Math.min(posB.getX() + width,posB.getX())){
//            if(Math.min(posA.getX() + height,posA.getY()) <= Math.max(posB.getY() + height,posB.getY())){
//                return true;
//            }
//
//        }

        if (posA.getX() < posB.getX() + width &&
                posA.getX() + width > posB.getX() &&
                posA.getY() < posB.getY() + height &&
                height + posA.getY() > posB.getY())
            return true;

        return false;
    }

    public Vector2f getPosA() {
        return posA;
    }

    public void setPosA(Vector2f posA) {
        this.posA = posA;
    }

    public Vector2f getPosB() {
        return posB;
    }

    public void setPosB(Vector2f posB) {
        this.posB = posB;
    }
}
