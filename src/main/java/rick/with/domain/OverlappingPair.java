package rick.with.domain;

/**
 * POJO for holding pair info
 */
public class OverlappingPair {

    /**
     * The overlapped String
     */
    private String overlappedStr;

    /**
     * The original left string used in this overlap
     */
    private String leftStr;

    /**
     * The original right string used in this overlap
     */
    private String rightStr;

    /**
     * The amount of overlapping characters
     */
    private int amount;

    /**
     * Constructor accepts the original left and right strings
     *
     * @param leftStr Left String
     * @param rightStr Right String
     */
    public OverlappingPair(String leftStr, String rightStr) {
        this.leftStr = leftStr;
        this.rightStr = rightStr;
    }

    public String getOverlappedStr() {
        return overlappedStr;
    }

    public void setOverlappedStr(String overlappedStr) {
        this.overlappedStr = overlappedStr;
    }

    public String getLeftStr() {
        return leftStr;
    }

    public void setLeftStr(String leftStr) {
        this.leftStr = leftStr;
    }

    public String getRightStr() {
        return rightStr;
    }

    public void setRightStr(String rightStr) {
        this.rightStr = rightStr;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OverlappingPair{" +
                "overlappedStr='" + overlappedStr + '\'' +
                ", leftStr='" + leftStr + '\'' +
                ", rightStr='" + rightStr + '\'' +
                ", amount=" + amount +
                '}';
    }
}
