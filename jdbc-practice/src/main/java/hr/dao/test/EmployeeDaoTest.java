package hr.dao.test;

import hr.dao.EmployeeDao;
import hr.dao.EmployeesVo;

import javax.naming.Name;
import java.util.List;

public class EmployeeDaoTest {

    public static void main(String[] args) {
//        testFindByName("ma");
        testFindBySalary(10000,50000);

    }

    private static void testFindBySalary(int minSalary, int maxSalary) {
        List<EmployeesVo> list = new EmployeeDao().findBySalary(minSalary,maxSalary);
        int count = 0;
        for(EmployeesVo vo : list){
            if (count >= 10) {
                break;
            }
            System.out.println(     vo);
            count++;
        }
    }


    private static void testFindByName(String name) {

        List<EmployeesVo> list = new EmployeeDao().findByName(name);
        for(EmployeesVo vo : list){
            System.out.println(vo);
        }


    }
}
