package Bean;

/*
 *预警规则 Bean
 */

public class FlowWarnRule {
    private  int warnLevel;
    private  int warnType;
    private int maxFlow;
    private int minFlow;

    public FlowWarnRule(){
        
    }

    public FlowWarnRule(int warnLevel, int warnType, int maxFlow, int minFlow) {
        this.warnLevel = warnLevel;
        this.warnType = warnType;
        this.maxFlow = maxFlow;
        this.minFlow = minFlow;
    }

    public int getLevel() {
        return warnLevel;
    }

    public void setLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }

    public int getWarnType() {
        return warnType;
    }

    public void setWarnType(int warnType) {
        this.warnType = warnType;
    }



    public int getMaxFlow() {
        return maxFlow;
    }

    public void setMaxFlow(int maxFlow) {
        this.maxFlow = maxFlow;
    }

    public int getMinFlow() {
        return minFlow;
    }

    public void setMinFlow(int minFlow) {
        this.minFlow = minFlow;
    }
}
