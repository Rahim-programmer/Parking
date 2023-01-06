package model;

import java.util.Random;

public enum CarState {
    DRIVING(3),
    PARKED(3);

    private static final Random RND = new Random();
    private int switchStateChancePercent;

    CarState(int switchStateChancePercent) {
        this.switchStateChancePercent = switchStateChancePercent;
    }

    public boolean willChangeState(){
        return RND.nextInt(1000) < (switchStateChancePercent / 100.d) * 1000.d;
    }
}
