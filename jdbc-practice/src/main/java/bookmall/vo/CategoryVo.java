package bookmall.vo;

public class CategoryVo {

    private Long ctgNo;
    private String ctgName;

    @Override
    public String toString() {
        return "CategoryVo{" +
                "ctgNo=" + ctgNo +
                ", ctgName='" + ctgName + '\'' +
                '}';
    }

    public Long getCtgNo() {
        return ctgNo;
    }

    public void setCtgNo(Long ctgNo) {
        this.ctgNo = ctgNo;
    }

    public String getCtgName() {
        return ctgName;
    }

    public void setCtgName(String ctgName) {
        this.ctgName = ctgName;
    }
}
