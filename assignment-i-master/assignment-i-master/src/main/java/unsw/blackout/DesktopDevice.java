package unsw.blackout;

import unsw.utils.Angle;

public class DesktopDevice extends Device {

    public DesktopDevice(String deviceId, Angle position) {
        super(deviceId, position, "DesktopDevice", 200000);
    }

}
