package bookshop.main;

import bookshop.dao.BookDao;
import bookshop.dao.BookVo;
import bookshop.example.Book;

import java.util.List;
import java.util.Scanner;

public class BookShop {

    public static void main(String[] args) {

        displayBookInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
        int no = scanner.nextInt();
        scanner.close();

        // (1) 입력된 번호에 맞는 책을 찾아 대여 되었음(상태코드=0)을 체크 합니다.
        BookVo vo = new BookVo();
        vo.setNo(no);
        vo.setRent("Y");

        new BookDao().updateRent(vo);

        displayBookInfo();
        // (2) Book 객체의 정보를 출력
        System.out.println("******도서 정보 출력하기******");
    }

    private static void displayBookInfo() {
        //select

        System.out.println("******도서 정보 출력하기******");

        List<BookVo> list = new BookDao().findAll();
    }
}
