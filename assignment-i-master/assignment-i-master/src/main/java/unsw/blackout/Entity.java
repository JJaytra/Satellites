package unsw.blackout;

import java.util.ArrayList;
import java.util.HashMap;

import unsw.utils.Angle;
import unsw.utils.MathsHelper;

public abstract class Entity {
    public String id;
    public Angle position;
    public String type;
    public int range;
    public double height;

    // ArrayList<File> files;
    HashMap<String, String> files = new HashMap<String, String>();

    public Entity(String id, double height, Angle position, String type, int range) {
        this.id = id;
        this.position = position;
        this.height = height;
        this.type = type;
        this.range = range;
    }

    public String getId() {
        return this.id;
    }

    public Angle getPosition() {
        return this.position;
    }

    public void setPosition(Angle position) {
        this.position = position;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRange() {
        return range;
    }

    public double getHeight() {
        return this.height;
    }

    public void addFile(String fileName, String content) {
        files.put(fileName, content);
    }

    public HashMap<String, String> getFiles() {
        return this.files;
    }

    public ArrayList<String> getCommunicableEntities(ArrayList<Entity> entities, ArrayList<String> result) {
        for (Entity entity : entities) {
            if (MathsHelper.getDistance(entity.getHeight(), entity.getPosition(), this.position) <= this.range &&
                    MathsHelper.isVisible(entity.getHeight(), entity.getPosition(), this.position)) {
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
