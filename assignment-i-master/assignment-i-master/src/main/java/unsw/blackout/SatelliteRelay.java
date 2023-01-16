package unsw.blackout;

import unsw.utils.Angle;

public class SatelliteRelay extends Satellite {
    public int speed = 1500;
    public int maxRange = 300000;
    public int maxFiles = 0;
    public int maxBytes = 0;

    public int receiveSpeed; // no bandwidth limits
    public int sendSpeed;

    SatelliteRelay(String satelliteID, double height, Angle position) {
        super(satelliteID, height, position, "RelaySatellite", true, 300000);
    }

    @Override
    public void movePosition() {
        // calculate velocity
        Angle movement = Angle.fromRadians(this.speed / this.getHeight());

        // out of bounds, go positive
        if (this.getPosition().compareTo(Angle.fromDegrees(345)) == 1
                && this.getPosition().compareTo(Angle.fromDegrees(140)) == -1) {
            Angle newPos = this.position.add(movement);
            this.clockwise = false;
            this.setPosition(newPos);
        }
        // out of bounds, go negative
        else if (this.getPosition().compareTo(Angle.fromDegrees(190)) == 1) {
            Angle newPos = this.position.subtract(movement);
            this.setPosition(newPos);
        }
        // in range, go negative
        else if (this.clockwise) {
            Angle newPos = this.position.subtract(movement);
            // reaches 140 going clockwise
            if (newPos.compareTo(Angle.fromDegrees(140)) == -1 || newPos.compareTo(Angle.fromDegrees(140)) == 0) {
                this.clockwise = false;
            }
            this.setPosition(newPos);
        }
        // in range, go positive
        else {
            Angle newPos = this.position.add(movement);
            // reaches 190 going anticlockwise
            if (newPos.compareTo(Angle.fromDegrees(190)) == 1 || newPos.compareTo(Angle.fromDegrees(190)) == 0) {
                this.clockwise = false;
            }
            this.setPosition(newPos);
        }
    }

}
