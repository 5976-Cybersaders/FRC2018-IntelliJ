package org.usfirst.frc.team5976.robot;

public class Position {
    public static final int LEFT = -1;
    public static final int MIDDLE = 0;
    public static final int RIGHT = 1;
    public static final int TEST = 3;

    public static Position LEFT_POSITION = new Position(LEFT);
    public static Position MIDDLE_POSITION = new Position(MIDDLE);
    public static Position RIGHT_POSITION = new Position(RIGHT);
    public static Position TEST_POSITION = new Position(TEST);
    private static SmartValue positionValue = SmartDashboardMap.POSITION;
    private int position;

    public Position(int position) {
        this.position = position;
    }

    public static Position getPosition() {
        String stringPosition = positionValue.getString().trim().toUpperCase();
        if (stringPosition.equals("LEFT") || stringPosition.equals("" + LEFT)) return LEFT_POSITION;
        if (stringPosition.equals("RIGHT") || stringPosition.equals("" + RIGHT)) return RIGHT_POSITION;
        if (stringPosition.equals("TEST") || stringPosition.equals("" + TEST)) return TEST_POSITION;
        return MIDDLE_POSITION;
    }

    public int getIntPosition() {
        return position;
    }

    public boolean isLeft() {
        return position == LEFT;
    }

    public boolean isMiddle() {
        return position == MIDDLE;
    }

    public boolean isRight() {
        return position == RIGHT;
    }

    public boolean isTest() {
        return position == TEST;
    }
}
