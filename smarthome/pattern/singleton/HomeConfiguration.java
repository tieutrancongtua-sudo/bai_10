package smarthome.pattern.singleton;

public final class HomeConfiguration {
    
    private static final class Holder {
        private static final HomeConfiguration INSTANCE = new HomeConfiguration();
    }

    public static HomeConfiguration getInstance() {
        return Holder.INSTANCE;
    }

    private String homeName = "My Home";
    private int nightLightLevel = 30;

    private HomeConfiguration() {
    }

    public String getHomeName() { return homeName; }
    public void setHomeName(String homeName) { this.homeName = homeName; }
    public int getNightLightLevel() { return nightLightLevel; }
    public void setNightLightLevel(int nightLightLevel) { this.nightLightLevel = nightLightLevel; }
}
