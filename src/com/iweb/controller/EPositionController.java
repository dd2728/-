package com.iweb.controller;

import com.iweb.util.Print;
import com.iweb.view.EPositionView;
import com.iweb.view.MainView;

/**
 * @author zxy
 * @create 2023/6/12 16:45
 */
public class EPositionController {
    public static void rootController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到添加职位页面...");
                EPositionView.insertView();
                break;
            case "2":
                Print.print("即将跳转到查询所有职位页面");
                EPositionView.selectallView();
                break;
            case "3":
                Print.print("即将跳转到根据名称查询职位页面");
                EPositionView.selectnameView();
                break;
            case "4":
                Print.print("即将跳转到删除职位页面");
                EPositionView.deleteView();
                break;
            case "5":
                Print.print("即将跳转到修改职位页面");
                EPositionView.updateView();
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
                EPositionView.rootView();
        }
    }

    public static void userController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到查询所有职位页面");
                EPositionView.selectallView();
                break;
            case "2":
                Print.print("即将跳转到根据名称查询职位页面");
                EPositionView.selectnameView();
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
                EPositionView.userView();
        }
    }
}
