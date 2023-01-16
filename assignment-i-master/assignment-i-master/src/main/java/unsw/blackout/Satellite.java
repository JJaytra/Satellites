package unsw.blackout;

import unsw.utils.Angle;

public abstract class Satellite extends Entity {

    public int speed;
    public int maxFiles;
    public int maxBytes;
    public int receiveSpeed;
    public int sendSpeed;
    public boolean clockwise;

    public Satellite(String id, double height, Angle position, String type, boolean direction, int range) {
        super(id, height, position, type, range);
        this.clockwise = direction;
    }

    public int getSpeed() {
        return this.speed;
    }

    public abstract void movePosition();

}
