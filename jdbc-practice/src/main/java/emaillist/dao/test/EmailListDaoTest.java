package emaillist.dao.test;

import emaillist.dao.EmailListDao;
import emaillist.vo.EmailListVo;

import java.util.List;

public class EmailListDaoTest {

    public static void main(String[] args) {

        EmailListVo vo = new EmailListVo();
        vo.setFirstName("둘");
        vo.setLastName("리");
        vo.setEmail("dool@gmail.com");

        testInsert(vo);
        testFindAll();
        testDeletByEmail("dool@gmail.com");
        testFindAll();

    }

    private static void testDeletByEmail(String mail) {
        new EmailListDao().deleteByEmail(mail);

    }

    private static void testFindAll() {
        List<EmailListVo> list = new EmailListDao().findAll();
        for (EmailListVo vo : list) {
            System.out.println(vo);
        }
    }

    private static void testInsert(EmailListVo vo) {
        new EmailListDao().insert(vo);

    }
}
