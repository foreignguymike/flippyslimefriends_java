package com.distraction.fs2j.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GSM {

    private final Stack<GameState> states;
    public int depth = 1;

    public GSM() {
        states = new Stack<>();
    }

    public void push(GameState state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
    }

    public void replace(GameState state) {
        states.pop();
        states.push(state);
    }

    public void resize(int w, int h) {
        for (int i = states.size() - depth; i < states.size(); i++) {
            states.get(i).resize(w, h);
        }
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
