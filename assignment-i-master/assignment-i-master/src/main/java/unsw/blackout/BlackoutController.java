package unsw.blackout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import unsw.response.models.EntityInfoResponse;
import unsw.response.models.FileInfoResponse;
import unsw.utils.Angle;

public class BlackoutController {

    // create list for devices and satellites
    // ArrayList<Device> devices = new ArrayList<Device>();
    // ArrayList<Satellite> satellites = new ArrayList<Satellite>();

    ArrayList<Entity> entities = new ArrayList<Entity>();

    public void createDevice(String deviceId, String type, Angle position) {
        switch (type) {
            case "HandheldDevice":
                entities.add(new HandheldDevice(deviceId, position));
                break;
            case "LaptopDevice":
                entities.add(new LaptopDevice(deviceId, position));
                break;
            case "DesktopDevice":
                entities.add(new DesktopDevice(deviceId, position));
                break;
        }
    }

    public void removeDevice(String deviceId) {
        for (Entity entity : entities) {
            if ((entity.getId()).equals(deviceId)) {
                entities.remove(entity);
                break;
            }
        }
    }

    public void createSatellite(String satelliteId, String type, double height, Angle position) {
        switch (type) {
            case "StandardSatellite":
                entities.add(new SatelliteStandard(satelliteId, height, position));
                break;
            case "TeleportingSatellite":
                entities.add(new SatelliteTeleporting(satelliteId, height, position));
                break;
            case "RelaySatellite":
                entities.add(new SatelliteRelay(satelliteId, height, position));
                break;
        }
    }

    public void removeSatellite(String satelliteId) {
        for (Entity entity : entities) {
            if ((entity.getId()).equals(satelliteId)) {
                entities.remove(entity);
                break;
            }
        }
    }

    public List<String> listDeviceIds() {
        ArrayList<String> result = new ArrayList<String>();

        for (Entity entity : entities) {
            if (entity.getType().equals("HandheldDevice") ||
                    entity.getType().equals("LaptopDevice") ||
                    entity.getType().equals("DesktopDevice")) {
                result.add(entity.getId());
            }
        }
        return result;
    }

    public List<String> listSatelliteIds() {
        ArrayList<String> result = new ArrayList<String>();

        for (Entity entity : entities) {
            if ((entity.getType()).equals("StandardSatellite") ||
                    (entity.getType()).equals("TeleportingSatellite") ||
                    (entity.getType()).equals("RelaySatellite")) {
                result.add(entity.getId());
            }
        }
        return result;
    }

    public void addFileToDevice(String deviceId, String filename, String content) {
        for (Entity entity : entities) {
            if ((entity.getId()).equals(deviceId)) {
                entity.addFile(filename, content);
                break;
            }
        }

    }

    public EntityInfoResponse getInfo(String id) {
        for (Entity entity : entities) {
            if ((entity.getId()).equals(id)) {
                // create new map
                HashMap<String, String> files = entity.getFiles();
                HashMap<String, FileInfoResponse> temp = new HashMap<String, FileInfoResponse>();
                // get all files
                for (Map.Entry<String, String> file : files.entrySet()) {
                    String name = file.getKey();
                    String content = file.getValue();
                    int fileSize = content.length();
                    FileInfoResponse fileInfo = new FileInfoResponse(name, content, fileSize, true);
                    temp.put(name, fileInfo);
                }
                return new EntityInfoResponse(id, entity.getPosition(), entity.getHeight(),
                        entity.getType(), temp);

            }
        }

        return null;
    }

    public void simulate() {
        for (Entity entity : entities) {
            if (entity.getType().equals("StandardSatellite") ||
                    entity.getType().equals("TeleportingSatellite") ||
                    entity.getType().equals("RelaySatellite"))
                ((Satellite) entity).movePosition();
        }
    }

    /**
     * Simulate for the specified number of minutes.
     * You shouldn't need to modify this function.
     */
    public void simulate(int numberOfMinutes) {
        for (int i = 0; i < numberOfMinutes; i++) {
            simulate();
        }
    }

    public List<String> communicableEntitiesInRange(String id) {

        ArrayList<String> communicableEntities = new ArrayList<String>();
        for (Entity entity : entities) {
            if ((entity.getId()).equals(id)) {
                communicableEntities = entity.getCommunicableEntities(entities, communicableEntities);
                break;
            }
        }

        return communicableEntities;
    }

    public void sendFile(String fileName, String fromId, String toId) throws FileTransferException {
        // TODO: Task 2 c)
    }

    public void createDevice(String deviceId, String type, Angle position, boolean isMoving) {
        createDevice(deviceId, type, position);
        // TODO: Task 3
    }

    public void createSlope(int startAngle, int endAngle, int gradient) {
        // TODO: Task 3
        // If you are not completing Task 3 you can leave this method blank :)
    }

}
