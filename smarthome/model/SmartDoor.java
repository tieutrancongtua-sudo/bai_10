package smarthome.model;

public final class SmartDoor implements IDevice, ILockable {
    private final String name;

    public SmartDoor(String name) {
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

    @Override
    public void lock() {
        System.out.println(name + ": LOCKED");
    }

    @Override
    public void unlock() {
        System.out.println(name + ": UNLOCKED");
    }
}
