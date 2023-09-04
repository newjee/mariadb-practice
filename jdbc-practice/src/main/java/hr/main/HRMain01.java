package hr.main;

import hr.dao.EmployeeDao;
import hr.dao.EmployeesVo;

import java.util.List;
import java.util.Scanner;

public class HRMain01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("이름> ");
            String keyword = scanner.nextLine();
            if ("quit".equals(keyword)) {
                break;
            }

            List<EmployeesVo> list = new EmployeeDao().findByName(keyword);

            for (EmployeesVo vo : list) {

                System.out.println(vo.getEmpNo() + ":" + vo.getFirstName() + ":" + vo.getLastName());
            }
        }
        scanner.close();
    }

}
