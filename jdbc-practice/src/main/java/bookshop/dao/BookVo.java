package bookshop.dao;

public class BookVo {

    private int no;
    private String title;
    private String rent;
    private int authorNo;
    private String authorName;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public int getAuthorNo() {
        return authorNo;
    }

    public void setAuthorNo(int authorNo) {
        this.authorNo = authorNo;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "BookVo{" +
                "no=" + no +
                ", title='" + title + '\'' +
                ", rent='" + rent + '\'' +
                ", authorNo=" + authorNo +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
