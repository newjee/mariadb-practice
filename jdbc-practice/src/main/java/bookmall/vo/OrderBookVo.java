package bookmall.vo;

public class OrderBookVo {

    private Long ordBookNo;
    private String ordBookName;
    private Long ordBookCount;

    @Override
    public String toString() {
        return "OrderBookVo{" +
                "ordBookNo=" + ordBookNo +
                ", ordBookName='" + ordBookName + '\'' +
                ", ordBookCount=" + ordBookCount +
                '}';
    }

    public Long getOrdBookNo() {
        return ordBookNo;
    }

    public void setOrdBookNo(Long ordBookNo) {
        this.ordBookNo = ordBookNo;
    }

    public String getOrdBookName() {
        return ordBookName;
    }

    public void setOrdBookName(String ordBookName) {
        this.ordBookName = ordBookName;
    }

    public Long getOrdBookCount() {
        return ordBookCount;
    }

    public void setOrdBookCount(Long ordBookCount) {
        this.ordBookCount = ordBookCount;
    }
}
