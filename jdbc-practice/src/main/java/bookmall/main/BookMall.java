package bookmall.main;


import bookmall.dao.*;
import bookmall.vo.*;

import java.util.List;

public class BookMall {

    public static void main(String[] args) {
//        MemberDao memberDao = new MemberDao();

//        memberDao.insert(memberVo1);
//        memberDao.insert(memberVo2);
        new CategoryDao().insertCategory();
        new BookDao().insertBook();
        new MemberDao().insertMember();
        new CartDao().insertCartByUserNo(2L);
        List<String> ordNoList = new OrderDao().insertOrderByUserNo(2L);
        new OrderDao().insertOrderBook(ordNoList, 2L);
        // 책번호 숨겨놓고,

        // 화면 출력
        // 이것만 실행했는데 제출산출물 내용이 쫘악
        System.out.println("## 회원리스트");

        List<MemberVo> mbrList = new MemberDao().findAll();
        for (MemberVo vo : mbrList) {

            System.out.printf("이름: %s, 전화번호: %s, 이메일: %s, 비밀번호: %s%n",
                    vo.getMbrName(), vo.getMbrPhone(), vo.getMbrEmail(), vo.getMbrPwd());

        }


        System.out.println("## 카테고리");

        List<CategoryVo> categoryList = new CategoryDao().findAll();
        for (CategoryVo vo : categoryList) {

            System.out.printf("카테고리번호 : %d, 카테고리명: %s%n", vo.getCtgNo(), vo.getCtgName());
        }

        System.out.println("## 상품");

        List<BookVo> bookList = new BookDao().findAll();
        for (BookVo vo : bookList) {

            System.out.printf("제목: %s, 가격: %,d원%n", vo.getBookTitle(), vo.getPrice());
        }

        System.out.println("## 카트");

        List<CartVo> cartList = new CartDao().findByUserNo(2L);
        for (CartVo vo : cartList) {

            System.out.printf("도서 제목: %s, 수량: %d개, 가격: %,d원%n",vo.getCartTitle(), vo.getCartCount(), vo.getCartPrice());
        }

        System.out.println("## 주문");

        List<OrderVo> OrderList = new OrderDao().findByUserNo(2L);
        OrderVo vo = OrderList.get(0);

        System.out.printf("주문 번호: %s, 주문자: %s, 결제금액: %,d원, 배송지: %s%n",vo.getOrdNo(), vo.getOrdName(), vo.getOrdPrice(), vo.getOrdAddress());



        System.out.println("## 주문도서");
        List<OrderBookVo> OrderBookList = new OrderDao().findOrderBook(ordNoList);

        for (OrderBookVo vo1 : OrderBookList) {

            System.out.printf("도서번호: %s, 도서제목: %s, 수량: %d개%n",vo1.getOrdBookNo(), vo1.getOrdBookName(), vo1.getOrdBookCount());
        }


        // 테스트용 삭제


    }
}
