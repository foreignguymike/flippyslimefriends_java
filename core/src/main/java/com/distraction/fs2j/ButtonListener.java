package com.distraction.fs2j;

public interface ButtonListener {
    enum ButtonType {
        UP,
        LEFT,
        DOWN,
        RIGHT,
        RESTART,
        BACK,
        SWITCH,
        BUBBLE_DROP
    }

    void onButtonPressed(ButtonType type);
}
