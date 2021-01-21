package Bean;

public class PressureWarnRule {
    private  int warnLevel;
    private  int warnType;
    private  int maxPressure;
    private  int minPressure;

    public PressureWarnRule(){

    }

    public PressureWarnRule(int warnLevel, int warnType, int maxPressure, int minPressure) {
        this.warnLevel = warnLevel;
        this.warnType = warnType;
        this.maxPressure = maxPressure;
        this.minPressure = minPressure;
    }

    public int getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }

    public int getWarnType() {
        return warnType;
    }

    public void setWarnType(int warnType) {
        this.warnType = warnType;
    }

    public int getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(int maxPressure) {
        this.maxPressure = maxPressure;
    }

    public int getMinPressure() {
        return minPressure;
    }

    public void setMinPressure(int minPressure) {
        this.minPressure = minPressure;
    }
}
