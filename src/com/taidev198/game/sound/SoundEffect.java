package com.taidev198.game.sound;

import com.taidev198.game.Game;

import java.applet.Applet;
import java.applet.AudioClip;

import java.net.URL;

public class   SoundEffect {

    private static final String EXPLODE = "/audio/explode.wav";   // explosion
  private static final String GotItem = "/audio/cheering.wav";         // gong
  private static final String BACKGROUND = "/audio/background.wav";//background
  private static final String SHOOT = "/audio/shot.wav";       // bullet

    // Each sound effect has its own clip, loaded with its own sound file.
    private AudioClip clipExplode;
    private AudioClip clipGotItem;
    private AudioClip clipBackground;
    private AudioClip clipShot;



    // Constructor to construct each element of the enum with its own sound file.
    public SoundEffect() {
        URL url = Game.class.getResource(EXPLODE);
        clipExplode = Applet.newAudioClip(url);
         url = Game.class.getResource(BACKGROUND);
        clipBackground = Applet.newAudioClip(url);
         url = Game.class.getResource(SHOOT);
        clipShot = Applet.newAudioClip(url);
        url = Game.class.getResource(GotItem);
        clipGotItem = Applet.newAudioClip(url);
    }

    public synchronized void Explode(){
        clipExplode.play();
    }

    public void background(){
        clipBackground.play();
    }

    public synchronized void shot(){
        clipShot.play();

    }

    public void gotItem(){
        clipGotItem.play();
    }

}
