package smarthome.pattern.facade;

import smarthome.model.*;
import smarthome.pattern.singleton.HomeConfiguration;

public final class SmartHomeFacade {
    private final IDevice light;
    private final IDevice fan;
    private final ILockable door;

    public SmartHomeFacade(IDevice light, IDevice fan, ILockable door) {
        this.light = light;
        this.fan = fan;
        this.door = door;
    }

    public void goToWork() {
        light.turnOff();
        fan.turnOff();
        door.lock();
    }

    public void goToSleep() {
        fan.turnOff();
        door.lock();
        light.turnOn();
        int level = HomeConfiguration.getInstance().getNightLightLevel();
        System.out.println("Night light level: " + level + "%");
    }
}
