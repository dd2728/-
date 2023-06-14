package com.iweb.view;

import com.iweb.controller.EPositionController;
import com.iweb.controller.EmployeeController;
import com.iweb.pojo.EPosition;
import com.iweb.pojo.Employee;
import com.iweb.service.EPositionService;
import com.iweb.service.EmployeeService;
import com.iweb.util.Print;

import java.util.List;
import java.util.Scanner;

/**
 * @author zxy
 * @create 2023/6/12 19:20
 */
public class EmployeeView {
    public static Scanner sc = new Scanner(System.in);
    public static void rootView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加员工");
        Print.print("2.查询所有员工");
        Print.print("3.根据姓名查询员工");
        Print.print("4.根据身份证号查询员工");
        Print.print("5.根据手机号查询员工");
        Print.print("6.根据性别查询员工");
        Print.print("7.根据职位查询员工");
        Print.print("8.根据部门查询员工");
        Print.print("9.删除员工");
        Print.print("10.修改员工");
        Print.print("11.返回上一页");
        Print.print("12.退出系统");
        String inputKey = sc.nextLine();
        EmployeeController.rootController(inputKey);
    }
    //添加员工
    public static void insertView() {
        Print.print("---添加员工界面---");
        Print.print("请输入员工姓名:");
        String name = sc.nextLine();
        Print.print("请输入员工身份证号:");
        String idNumber = sc.nextLine();
        Print.print("请输入员工手机号:");
        String phoneNumber = sc.nextLine();
        Print.print("请输入员工性别:");
        String sex = sc.nextLine();

        if(name.isEmpty()||idNumber.isEmpty()||phoneNumber.isEmpty()||sex.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            insertView();
        }else{
            //将获取的输入信息封装成对象
            Employee insertEmployee = new Employee();
            insertEmployee.setName(name);
            insertEmployee.setId_number(idNumber);
            insertEmployee.setPhone_number(phoneNumber);
            insertEmployee.setSex(sex);
            //视图层应该将上面的insertUser传递给业务方法 根据业务方法判断用户是否添加成功
            EmployeeService employeeService = new EmployeeService();
            employeeService.insert(insertEmployee);
        }
    }

    // 查询所有员工
    public static void selectAllView() {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employeeList = employeeService.selectAll();
        if (employeeList != null&&!employeeList.isEmpty()) {
            for (Employee employee : employeeList) {
                System.out.println(employee.toString());
            }
            System.out.println("查询完成，即将返回上一页");
        } else {
            System.out.println("系统内没有员工，即将返回上一页");
        }
        EmployeeView.rootView();
    }

    // 根据姓名查询员工(模糊查询)
    public static void selectByNameView() {
        Print.print("输入您要查询的员工姓名:");
        String selectname = sc.nextLine();
        if(selectname.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selectByNameView();
        }else {
            //将获取的输入信息封装成对象
            Employee selectEmployee = new Employee();
            selectEmployee.setName(selectname);
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employeeList = employeeService.listByNameLike(selectname);
            if (employeeList != null && !employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    System.out.println(employee);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该员工，即将返回上一页");
            }
            EmployeeView.rootView();
        }
    }

    // 根据身份证号查询员工(模糊查询)
    public static void selectByIdNumberView() {
        Print.print("输入您要查询的员工身份证号:");
        String selectId_number = sc.nextLine();
        if(selectId_number.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selectByIdNumberView();
        }else {
            //将获取的输入信息封装成对象
            Employee selectEmployee = new Employee();
            selectEmployee.setId_number(selectId_number);
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employeeList = employeeService.listByNameLike(selectId_number);
            if (employeeList != null && !employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    System.out.println(employee);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该员工，即将返回上一页");
            }
            EmployeeView.rootView();
        }
    }

    // 根据手机号查询员工(模糊查询)
    public static void selectByPhoneNumberView() {
        Print.print("请输入要查询的员工手机号:");
        String phoneNumber = sc.nextLine();
        if(phoneNumber.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selectByPhoneNumberView();
        }else {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employeeList = employeeService.listByPhoneNumberLike(phoneNumber);
            if (employeeList != null && !employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    System.out.println(employee);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该员工，即将返回上一页");
            }
            EmployeeView.rootView();
        }
    }

    // 根据性别查询员工(模糊查询)
    public static void selectBySexView() {
        Print.print("请输入要查询的员工性别:");
        String sex = sc.nextLine();
        if(sex.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
           selectBySexView();
        }else {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employeeList = employeeService.listBySex(sex);
            if (employeeList != null && !employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    System.out.println(employee);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该员工，即将返回上一页");
            }
            EmployeeView.rootView();
        }
    }

    // 根据职位查询员工(模糊查询)
    public static void selectByPositionView() {
        Print.print("请输入要查询的职位ID:");
        Integer positionId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(positionId==null){
            System.out.println("信息不可为空,请重新输入");
            selectByPositionView();
        }else {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employeeList = employeeService.listByPosition(positionId);
            if (employeeList != null && !employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    System.out.println(employee);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该员工，即将返回上一页");
            }
            EmployeeView.rootView();
        }
    }

    // 根据部门查询员工(模糊查询)
    public static void selectByDepartmentView() {
        Print.print("请输入要查询的部门ID:");
        Integer departmentId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(departmentId==null){
            System.out.println("信息不可为空,请重新输入");
            selectByDepartmentView();
        }else {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employeeList = employeeService.listByDepartment(departmentId);
            if (employeeList != null && !employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    System.out.println(employee);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该员工，即将返回上一页");
            }
            EmployeeView.rootView();
        }
    }

    // 删除员工
    public static void deleteView() {
        Print.print("请输入要删除的员工ID:");
        Integer deleteId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(deleteId==null){
            System.out.println("信息不可为空,请重新输入");
            deleteView();
        }else {
            Print.print("确定执行删除操作吗？（y/n）");
            String answer=sc.nextLine();
            if(answer.equals("y")||answer.equals("Y")){
                Employee deleteemployee = new Employee();
                deleteemployee.setId(deleteId);
                EmployeeService employeeService = new EmployeeService();
                employeeService.delete(deleteemployee);
            } else if(answer.equals("n")||answer.equals("N")){
                System.out.println("退出删除操作，即将返回上一页");
                EmployeeView.rootView();
            }else{
                System.out.println("输入信息有误，退出删除操作，即将返回上一页");
                EmployeeView.rootView();
            }

        }
    }

    // 修改员工
    public static void updateView() {
        Print.print("请输入要修改的员工ID:");
        Integer employeeId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(employeeId==null){
            System.out.println("信息不可为空,请重新输入");
            updateView();
        }else {
            Print.print("请输入员工姓名:");
            String name = sc.nextLine();
            Print.print("请输入员工身份证号:");
            String idNumber = sc.nextLine();
            Print.print("请输入员工手机号:");
            String phoneNumber = sc.nextLine();
            Print.print("请输入员工性别:");
            String sex = sc.nextLine();
            if(name.isEmpty()||idNumber.isEmpty()||phoneNumber.isEmpty()||sex.isEmpty()){
                System.out.println("信息不可为空,请重新输入");
                selectByIdNumberView();
            }else {
                // 将获取的输入信息封装成对象
                Employee updatedEmployee = new Employee();
                updatedEmployee.setName(name);
                updatedEmployee.setId_number(idNumber);
                updatedEmployee.setPhone_number(phoneNumber);
                updatedEmployee.setSex(sex);
                updatedEmployee.setId(employeeId);

                EmployeeService employeeService = new EmployeeService();
                employeeService.update(updatedEmployee);
            }
        }

    }


}
