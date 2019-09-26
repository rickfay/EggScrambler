package rick.with.domain;

/**
 * POJO for holding pair info
 */
public class OverlappingPair {

    /**
     * The overlapped String
     */
    private StringBuffer overlappedStr;

    /**
     * The original left string used in this overlap
     */
    private StringBuffer leftStr;

    /**
     * The original right string used in this overlap
     */
    private StringBuffer rightStr;

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
    public OverlappingPair(StringBuffer leftStr, StringBuffer rightStr) {
        this.leftStr = leftStr;
        this.rightStr = rightStr;
    }

    public StringBuffer getOverlappedStr() {
        return overlappedStr;
    }

    public void setOverlappedStr(StringBuffer overlappedStr) {
        this.overlappedStr = overlappedStr;
    }

    public StringBuffer getLeftStr() {
        return leftStr;
    }

    public void setLeftStr(StringBuffer leftStr) {
        this.leftStr = leftStr;
    }

    public StringBuffer getRightStr() {
        return rightStr;
    }

    public void setRightStr(StringBuffer rightStr) {
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
