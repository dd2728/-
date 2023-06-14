package com.iweb.controller;

import com.iweb.util.Print;
import com.iweb.view.EUserView;
import com.iweb.view.MainView;

/**
 * @author zxy
 * @create 2023/6/12 11:13
 */
public class EUserController {
    public static void rootController(String key){
        //根据key的值 决定后续的跳转页面
        switch (key){
            case "1":
                Print.print("即将跳转到添加用户页面...");
                EUserView.insertView();
                break;
            case "2":
                Print.print("即将跳转到查询所有用户页面...");
                EUserView.selectallView();
                break;
            case "3":
                Print.print("即将跳转到根据用户名查询用户页面");
                EUserView.selectnameView();
                break;
            case "4":
                Print.print("即将跳转到根据用户状态查询用户页面");
                EUserView.selecttypeView();
                break;
            case "5":
                Print.print("即将跳转到删除用户页面");
                EUserView.deleteView();
                break;
            case "6":
                Print.print("即将跳转到修改用户页面");
                EUserView.updateView();
                break;
            case "7":
                Print.print("即将返回上一页");
                MainView.rootloginSucessView();
                break;
            case "8":
                System.out.println("感谢您的使用");
                break;
            default:
                System.out.println("输入范围有误，请重新输入");
                EUserView.rootView();
        }
    }
}
