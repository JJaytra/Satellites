package unsw.blackout;

import unsw.utils.Angle;

public class SatelliteTeleporting extends Satellite {
    public int speed = 1000;
    public int maxRange = 200000;
    public int maxFiles = Integer.MAX_VALUE; // AS MANY THAT CAN FIT INTO MAXBYTES
    public int maxBytes = 200;

    public int receiveSpeed = 15;
    public int sendSpeed = 10;

    SatelliteTeleporting(String satelliteID, double height, Angle position) {
        super(satelliteID, height, position, "TeleportingSatellite", false, 200000);
    }

    @Override
    public void movePosition() {

        Angle turnPoint = Angle.fromDegrees(180);
        Angle startPoint = Angle.fromDegrees(0);

        // calculate velocity
        Angle movement = Angle.fromRadians(this.speed / this.getHeight());
        // Clockwise
        Angle newPos = this.position;
        if (this.clockwise) {
            newPos = this.position.subtract(movement);
            if (newPos.compareTo(turnPoint) == -1 || newPos.compareTo(turnPoint) == 0) {
                newPos = startPoint;
                this.clockwise = false;
            }

        } else {
            newPos = this.position.add(movement);
            if (newPos.compareTo(turnPoint) == 1 || newPos.compareTo(turnPoint) == 0) {
                newPos = startPoint;
                this.clockwise = true;
            }
        }
        this.setPosition(newPos);

    }

}
