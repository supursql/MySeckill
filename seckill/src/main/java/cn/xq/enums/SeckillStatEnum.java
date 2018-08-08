package cn.xq.enums;

public enum SeckillStatEnum {
    SUCCESS(1, "seconds kill success"),
    END(0, "seconds kill end"),
    REPEAT_KILL(-1, "repeat seconds kill"),
    INNER_ERROR(-2, "system exception"),
    DATA_REWRITE(-3, "data tampering");


    private int state;

    private String stateInfo;

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }

        return null;
    }
}
