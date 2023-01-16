package unsw.blackout;

import java.util.ArrayList;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;

public class SatelliteStandard extends Satellite {

    public int speed = 2500;
    public int maxRange = 150000;
    public int maxFiles = 3;
    public int maxBytes = 80;

    public int receiveSpeed = 1;
    public int sendSpeed = 1;

    /**
     * Create a new StandardSatellite
     */
    SatelliteStandard(String satelliteID, double height, Angle position) {
        super(satelliteID, height, position, "StandardSatellite", true, 150000);
    }

    public void movePosition() {
        double velocity = Math.toDegrees(this.speed / this.getHeight());
        this.setPosition(this.position.subtract(Angle.fromDegrees(velocity)));
    }

    @Override
    public ArrayList<String> getCommunicableEntities(ArrayList<Entity> entities, ArrayList<String> result) {
        for (Entity entity : entities) {
            if (entity.getId().equals(this.id) || (entity.getType()).equals("DesktopDevice"))
                continue;

            if (MathsHelper.getDistance(this.height, this.position, entity.getPosition()) <= this.getRange() &&
                    MathsHelper.isVisible(this.height, this.position, entity.getPosition())) {
                if (!result.contains(entity.getId())) {
                    result.add(entity.getId());
                    if (entity.getType().equals("RelaySatellite")) {
                        result = entity.getCommunicableEntities(entities, result);
                    }
                }
            }
        }
        return result;
    }

}
