package com.distraction.fs2j.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GSM {

    private Stack<GameState> states;
    public int depth = 1;

    public GSM() {
        states = new Stack<>();
    }

    public void push(GameState state) {
        states.push(state);
    }

    public GameState pop() {
        return states.pop();
    }

    public GameState replace(GameState state) {
        GameState s = states.pop();
        states.push(state);
        return s;
    }

    public void update(float dt) {
        for (int i = states.size() - depth; i < states.size(); i++) {
            states.get(i).update(dt);
        }
    }

    public void render(SpriteBatch sb) {
        for (int i = states.size() - depth; i < states.size(); i++) {
            states.get(i).render(sb);
        }
    }

}
