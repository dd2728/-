package com.iweb.controller;


import com.iweb.util.Print;
import com.iweb.view.DepartmentView;
import com.iweb.view.EPositionView;
import com.iweb.view.MainView;

/**
 * @author zxy
 * @create 2023/6/12 16:00
 */
public class DepartmentController {
    public static void rootController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到添加部门页面...");
                DepartmentView.insertView();
                break;
            case "2":
                Print.print("即将跳转到查询所有部门页面");
                DepartmentView.selectallView();
                break;
            case "3":
                Print.print("即将跳转到根据名称查询部门页面");
                DepartmentView.selectnameView();
                break;
            case "4":
                Print.print("即将跳转到删除部门页面");
                DepartmentView.deleteView();
                break;
            case "5":
                Print.print("即将跳转到修改部门页面");
                DepartmentView.updateView();
                break;
            case "6":
                System.out.println("即将返回上一页");
                MainView.rootloginSucessView();
                break;
            case "7":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示
                System.out.println("输入范围有误,请重新输入");
                DepartmentView.rootView();
        }
    }

    public static void userController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到查询所有部门页面");
                DepartmentView.selectallView();
                break;
            case "2":
                Print.print("即将跳转到根据名称查询部门页面");
                DepartmentView.selectnameView();
                break;
            case "3":
                System.out.println("即将返回上一页");
                MainView.userloginSucessView();
                break;
            case "4":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示
                System.out.println("输入范围有误,请重新输入");
                DepartmentView.userView();
        }
    }
}
