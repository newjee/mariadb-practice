package bookmall.dao.test;


import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;
import hr.dao.EmployeeDao;
import hr.dao.EmployeesVo;

import java.util.List;

public class MemberDaoTest {

    public static void main(String[] args) {

        insertTest();
        findAllTest();
    }

    private static void findAllTest() {
        List<MemberVo> list = new MemberDao().findAll();
        for(MemberVo vo : list){
            System.out.println(vo);
        }

    }

    private static void insertTest() {

        MemberDao dao = new MemberDao();

//        dao.insert
    }



}
