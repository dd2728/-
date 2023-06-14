package com.iweb.controller;

import com.iweb.util.Print;
import com.iweb.view.DepartmentView;
import com.iweb.view.EPositionView;
import com.iweb.view.EmployeeView;
import com.iweb.view.MainView;

/**
 * @author zxy
 * @create 2023/6/12 19:24
 */
public class EmployeeController {
    public static void rootController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到添加员工页面...");
                EmployeeView.insertView();
                break;
            case "2":
                Print.print("即将跳转到查询所有员工页面");
                EmployeeView.selectAllView();
                break;
            case "3":
                Print.print("即将跳转到根据姓名查询员工页面");
                EmployeeView.selectByNameView();
                break;
            case "4":
                Print.print("即将跳转到根据身份证号查询员工页面");
                EmployeeView.selectByIdNumberView();
                break;
            case "5":
                Print.print("即将跳转到根据手机号查询员工页面");
                EmployeeView.selectByPhoneNumberView();
                break;
            case "6":
                Print.print("即将跳转到根据性别查询员工页面");
                EmployeeView.selectBySexView();
                break;
            case "7":
                Print.print("即将跳转到根据职位查询员工页面");
                EmployeeView.selectByPositionView();
                break;
            case "8":
                Print.print("即将跳转到根据部门查询员工页面");
                EmployeeView.selectByDepartmentView();
                break;
            case "9":
                Print.print("即将跳转到删除员工页面");
                EmployeeView.deleteView();
                break;
            case "10":
                Print.print("即将跳转到修改员工页面");
                EmployeeView.updateView();
                break;
            case "11":
                System.out.println("即将返回上一页");
                MainView.rootloginSucessView();
                break;
            case "12":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示
                System.out.println("输入范围有误,请重新输入");
                EmployeeView.rootView();
        }
    }

}
