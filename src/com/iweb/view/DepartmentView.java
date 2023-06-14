package com.iweb.view;

import com.iweb.controller.DepartmentController;
import com.iweb.controller.EUserController;
import com.iweb.pojo.Department;
import com.iweb.pojo.EUser;
import com.iweb.service.DepartmentService;
import com.iweb.service.EUserService;
import com.iweb.util.Print;

import java.util.List;
import java.util.Scanner;

/**
 * @author zxy
 * @create 2023/6/12 15:56
 */
public class DepartmentView {
    public static Scanner sc = new Scanner(System.in);

    public static void rootView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加部门");
        Print.print("2.查询所有部门");
        Print.print("3.根据名称查询部门");
        Print.print("4.删除部门");
        Print.print("5.修改部门");
        Print.print("6.返回上一页");
        Print.print("7.退出系统");
        String inputKey = sc.nextLine();
        DepartmentController.rootController(inputKey);
    }

    public static void userView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.查询所有部门");
        Print.print("2.根据名称查询部门");
        Print.print("3.返回上一页");
        Print.print("4.退出系统");
        String inputKey = sc.nextLine();
        DepartmentController.userController(inputKey);
    }

    //添加部门
    public static void insertView(){
        Print.print("---添加部门界面---");
        Print.print("请输入添加部门的名称");
        String insertDepartmentname=sc.nextLine();
        if(insertDepartmentname.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            insertView();
        }else{
            //将获取的输入信息封装成对象
            Department insertDepartment = new Department();
            insertDepartment.setDepartment_name(insertDepartmentname);
            DepartmentService departmentService = new DepartmentService();
            departmentService.insert(insertDepartment);
        }
    }

    //查询全部部门
    public static void selectallView() {
        DepartmentService departmentService = new DepartmentService();
        List<Department> departmentList = departmentService.selectAll();
        if (departmentList != null&&!departmentList.isEmpty()) {
            for (Department department : departmentList) {
                System.out.println(department.toString());
            }
            System.out.println("查询完成，即将返回上一页");
        } else {
            System.out.println("系统内没有部门，即将返回上一页");
        }

        String currentUserType = EUser.getCurrentUserType();
        if(currentUserType.equals("管理员")){
            DepartmentView.rootView();
        }else{
            DepartmentView.userView();
        }
    }

    //根据名称模糊查询
    public static void selectnameView(){
        Print.print("输入您要查询的部门名称:");
        String selectname = sc.nextLine();
        if(selectname.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selectnameView();
        }else {
            //将获取的输入信息封装成对象
            Department selectdepartment = new Department();
            selectdepartment.setDepartment_name(selectname);
            DepartmentService departmentService = new DepartmentService();
            List<Department> departmentList = departmentService.listByNameLike(selectname);
            if (departmentList != null && !departmentList.isEmpty()) {
                for (Department department : departmentList) {
                    System.out.println(department);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该部门");
            }
            String currentUserType = EUser.getCurrentUserType();
            if (currentUserType.equals("管理员")) {
                DepartmentView.rootView();
            } else {
                DepartmentView.userView();
            }
        }
    }
    //删除部门
    public static void deleteView() {
        Print.print("请输入要删除的部门ID:");
        Integer deleteId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(deleteId==null){
            System.out.println("信息不可为空,请重新输入");
            deleteView();
        }else {
            Print.print("确定执行删除操作吗？（y/n）");
            String answer=sc.nextLine();
            if(answer.equals("y")||answer.equals("Y")){
                Department deletedepartment = new Department();
                deletedepartment.setId(deleteId);
                DepartmentService departmentService = new DepartmentService();
                departmentService.delete(deletedepartment);
            } else if(answer.equals("n")||answer.equals("N")){
                System.out.println("退出删除操作，即将返回上一页");
                DepartmentView.rootView();
            }else{
                System.out.println("输入信息有误，退出删除操作，即将返回上一页");
                DepartmentView.rootView();
            }

        }
    }

    //修改部门
    public static void updateView() {
        Print.print("请输入要修改的部门ID:");
        Integer updateId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(updateId==null){
            System.out.println("信息不可为空,请重新输入");
            updateView();
        }else {
            Department updatedepartment = new Department();
            updatedepartment.setId(updateId);
            Print.print("请输入修改后的部门名称:");
            String updatedDepartmentname = sc.nextLine();
            if(updatedDepartmentname.isEmpty()){
                System.out.println("信息不可为空,请重新输入");
                updateView();
            }else {
                updatedepartment.setDepartment_name(updatedDepartmentname);

                DepartmentService departmentService = new DepartmentService();
                departmentService.update(updatedepartment);
            }
        }
    }
}
