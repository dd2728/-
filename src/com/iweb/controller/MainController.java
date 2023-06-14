package com.iweb.controller;

import com.iweb.util.Print;
import com.iweb.view.*;


public class MainController {
    public static void mainController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
//                跳转到登录页面
                Print.print("即将跳转到登录页面...");
                MainView.loginView();
                break;
            case "2":
//                跳转到注册页面
                Print.print("即将跳转到注册页面...");
                MainView.registerView();
                break;
            case "3":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示 重新回到主页面
                System.out.println("输入范围有误,请重新输入");
                MainView.mainView();
        }
    }

    public static void rootloginSuccessController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到用户管理页面...");
                EUserView.rootView();
                break;
            case "2":
                Print.print("即将跳转到部门管理页面");
                DepartmentView.rootView();
                break;
            case "3":
                Print.print("即将跳转到职位管理页面");
                EPositionView.rootView();
                break;
            case "4":
                Print.print("即将跳转到员工管理页面");
                EmployeeView.rootView();
                break;
            case "5":
                Print.print("即将跳转到公告管理页面");
                NoticeView.rootView();
                break;
            case "6":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示
                System.out.println("输入范围有误,请重新输入");
                MainView.rootloginSucessView();
        }
    }

    public static void userloginSuccessController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到查看部门页面");
                DepartmentView.userView();
                break;
            case "2":
                Print.print("即将跳转到查看职位页面");
                EPositionView.userView();
                break;
            case "3":
                Print.print("即将跳转到查看公告页面");
                NoticeView.userView();
                break;
            case "4":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示
                System.out.println("输入范围有误,请重新输入");
                MainView.userloginSucessView();
        }
    }


}
