package bookmall.vo;

public class BookVo {

    private String bookTitle;
    private Long price;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookVo{" +
                "bookTitle='" + bookTitle + '\'' +
                ", price=" + price +
                '}';
    }
}
