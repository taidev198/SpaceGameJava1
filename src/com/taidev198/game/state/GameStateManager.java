package com.taidev198.game.state;

import com.taidev198.game.Game;
import com.taidev198.game.util.Vector2f;

import java.util.ArrayList;
import java.util.Arrays;

public class GameStateManager {

    private STATE state;
    private ArrayList<GameState> gameStates;
    private Vector2f map;
    public GameStateManager(){

        map = new Vector2f(Game.WIDTH * Game.SCALE,Game.HEIGHT * Game.SCALE);
        Vector2f.setWorldVar(map.x,map.y);
        gameStates = new ArrayList<>();
    }

    public void processing(){

    }


}
