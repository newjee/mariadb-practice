package bookmall.vo;

public class CartVo {

//    private Long userNo;
//    private Long bookNo;

    private String cartTitle;
    private Long cartCount;
    private Long cartPrice;

    @Override
    public String toString() {
        return "CartVo{" +
                "cartTitle='" + cartTitle + '\'' +
                ", cartCount=" + cartCount +
                ", cartPrice=" + cartPrice +
                '}';
    }

    public String getCartTitle() {
        return cartTitle;
    }

    public void setCartTitle(String cartTitle) {
        this.cartTitle = cartTitle;
    }

    public Long getCartCount() {
        return cartCount;
    }

    public void setCartCount(Long cartCount) {
        this.cartCount = cartCount;
    }

    public Long getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(Long cartPrice) {
        this.cartPrice = cartPrice;
    }
}
