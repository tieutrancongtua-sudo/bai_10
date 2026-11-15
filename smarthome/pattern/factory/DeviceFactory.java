package smarthome.pattern.factory;

import smarthome.model.*;

import java.util.Locale;

public final class DeviceFactory {
    private DeviceFactory() {
    }

    public static IDevice create(String type, String name) {
        if (type == null) {
            throw new IllegalArgumentException("Device type must not be null.");
        }
        return switch (type.trim().toLowerCase(Locale.ROOT)) {
            case "light" -> new SmartLight(name);
            case "fan" -> new SmartFan(name);
            case "door" -> new SmartDoor(name);
            default -> throw new IllegalArgumentException("Unsupported device type: " + type);
        };
    }
}
