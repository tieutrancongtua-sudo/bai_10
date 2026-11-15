package smarthome.app;

import smarthome.model.*;
import smarthome.pattern.factory.DeviceFactory;
import smarthome.pattern.facade.SmartHomeFacade;
import smarthome.pattern.singleton.HomeConfiguration;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        System.out.println("=== Demo ===");

        IDevice light = DeviceFactory.create("light", "Living room light");
        IDevice fan = DeviceFactory.create("fan", "Living room fan");
        IDevice door = DeviceFactory.create("door", "Main door");

        light.turnOn();
        fan.turnOff();
        ((ILockable) door).lock();

        HomeConfiguration config1 = HomeConfiguration.getInstance();
        HomeConfiguration config2 = HomeConfiguration.getInstance();
        System.out.println(config1 == config2); 

        if (config1 != config2) {
            throw new IllegalStateException("Singleton implementation is incorrect.");
        }

        SmartHomeFacade facade = new SmartHomeFacade(light, fan, (ILockable) door);
        System.out.println("-- goToWork --");
        facade.goToWork();
        System.out.println("-- goToSleep --");
        facade.goToSleep();
        System.out.println();

        System.exit(Tests.run());
    }
}