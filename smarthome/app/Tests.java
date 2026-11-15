package smarthome.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import smarthome.model.*;
import smarthome.pattern.factory.DeviceFactory;
import smarthome.pattern.facade.SmartHomeFacade;
import smarthome.pattern.singleton.HomeConfiguration;

public final class Tests {
    private static int failed = 0;

    private Tests() {
    }

    private static String capture(Runnable action) {
        PrintStream old = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf));
        try {
            action.run();
        } finally {
            System.setOut(old);
        }
        return buf.toString();
    }

    private static void check(String name, boolean ok) {
        System.out.println("[" + (ok ? "PASS" : "FAIL") + "] " + name);
        if (!ok) failed++;
    }

    private static boolean hasAll(String text, String... parts) {
        for (String p : parts) {
            if (!text.contains(p)) return false;
        }
        return true;
    }

    public static int run() {
        System.out.println("=== Tests ===");

        IDevice light = DeviceFactory.create("light", "Light");
        IDevice fan = DeviceFactory.create(" FAN ", "Fan");
        IDevice door = DeviceFactory.create("Door", "Door");
        check("T1 Factory tra dung kieu",
                light instanceof SmartLight && fan instanceof SmartFan && door instanceof SmartDoor);

        boolean thrown = false;
        try {
            DeviceFactory.create("camera", "Cam");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        check("T2 camera -> IllegalArgumentException", thrown);

        check("T3 Singleton cung tham chieu",
                HomeConfiguration.getInstance() == HomeConfiguration.getInstance());

        SmartHomeFacade facade = new SmartHomeFacade(light, fan, (ILockable) door);

        check("T4 goToWork",
                hasAll(capture(facade::goToWork), "Light: OFF", "Fan: OFF", "Door: LOCKED"));

        int level = HomeConfiguration.getInstance().getNightLightLevel();
        check("T5 goToSleep",
                hasAll(capture(facade::goToSleep), "Fan: OFF", "Door: LOCKED", "Light: ON",
                        "Night light level: " + level + "%"));

        System.out.println(failed == 0 ? "All tests passed." : failed + " test(s) failed.");
        return failed == 0 ? 0 : 1;
    }
}
