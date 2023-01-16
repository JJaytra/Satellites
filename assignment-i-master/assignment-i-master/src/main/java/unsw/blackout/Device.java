package unsw.blackout;

import java.io.File;
import java.util.ArrayList;
import unsw.utils.Angle;
import unsw.utils.MathsHelper;
import static unsw.utils.MathsHelper.RADIUS_OF_JUPITER;

public abstract class Device extends Entity {

    public Device(String id, Angle position, String type, int range) {
        super(id, RADIUS_OF_JUPITER, position, type, range);

    }

    @Override
    public ArrayList<String> getCommunicableEntities(ArrayList<Entity> entities, ArrayList<String> result) {
        for (Entity entity : entities) {
            if (MathsHelper.getDistance(entity.getHeight(), entity.getPosition(), position) < this.getRange() &&
                    MathsHelper.isVisible(entity.getHeight(), entity.getPosition(), this.position)) {
                if (!result.contains(entity.getId()) && !isDevice(entity)) {
                    result.add(entity.getId());
                    System.out.println(entity.getId());
                    if (entity.getType().equals("RelaySatellite")) {
                        result = entity.getCommunicableEntities(entities, result);
                    }
                }
            }

        }

        return result;
    }

    public boolean isDevice(Entity entity) {
        if (entity.getType().equals("HandheldDevice") ||
                entity.getType().equals("LaptopDevice") ||
                entity.getType().equals("DesktopDevice")) {
            return true;
        }
        return false;
    }

}
