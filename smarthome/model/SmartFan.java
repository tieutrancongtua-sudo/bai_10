package smarthome.model;

public final class SmartFan implements IDevice {
    private final String name;

    public SmartFan(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        System.out.println(name + ": ON");
    }

    @Override
    public void turnOff() {
        System.out.println(name + ": OFF");
    }
}
