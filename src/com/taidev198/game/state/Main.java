package com.taidev198.game.state;

import com.taidev198.game.util.KeyHandler;
import com.taidev198.game.util.MouseHandler;

import java.awt.*;

public interface Main {

    void tick();
    void input(MouseHandler mouse, KeyHandler key);
    void render(Graphics g);
}
