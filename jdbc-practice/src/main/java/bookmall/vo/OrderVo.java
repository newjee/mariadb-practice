package bookmall.vo;

public class OrderVo {

    private String ordNo;
    private String ordName;
    private Long ordPrice;
    private String ordAddress;

    public String getOrdNo() {
        return ordNo;
    }

    public void setOrdNo(String ordNo) {
        this.ordNo = ordNo;
    }

    public String getOrdName() {
        return ordName;
    }

    public void setOrdName(String ordName) {
        this.ordName = ordName;
    }

    public Long getOrdPrice() {
        return ordPrice;
    }

    public void setOrdPrice(Long ordPrice) {
        this.ordPrice = ordPrice;
    }

    public String getOrdAddress() {
        return ordAddress;
    }

    public void setOrdAddress(String ordAddress) {
        this.ordAddress = ordAddress;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "ordNo='" + ordNo + '\'' +
                ", ordName='" + ordName + '\'' +
                ", ordPrice=" + ordPrice +
                ", ordAddress='" + ordAddress + '\'' +
                '}';
    }
}
