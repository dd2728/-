package com.iweb.controller;

import com.iweb.util.Print;
import com.iweb.view.DepartmentView;
import com.iweb.view.EPositionView;
import com.iweb.view.MainView;
import com.iweb.view.NoticeView;

/**
 * @author zxy
 * @create 2023/6/12 19:25
 */
public class NoticeController {
    public static void rootController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到添加公告页面...");
                NoticeView.insertView();
                break;
            case "2":
                Print.print("即将跳转到查询所有公告页面");
                NoticeView.selectallView();
                break;
            case "3":
                Print.print("即将跳转到根据名称查询公告页面");
                NoticeView.selecttitleView();
                break;
            case "4":
                Print.print("即将跳转到根据内容查询公告页面");
                NoticeView.selectcontentView();
                break;
            case "5":
                Print.print("即将跳转到删除公告页面");
                NoticeView.deleteView();
                break;
            case "6":
                Print.print("即将跳转到修改公告页面");
                NoticeView.updateView();
                break;
            case "7":
                System.out.println("即将返回上一页");
                MainView.rootloginSucessView();
                break;
            case "8":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示
                System.out.println("输入范围有误,请重新输入");
                NoticeView.rootView();
        }
    }

    public static void userController(String key){
        switch (key){
            case "1":
                Print.print("即将跳转到查询所有公告页面");
                NoticeView.selectallView();
                break;
            case "2":
                Print.print("即将跳转到根据名称查询公告页面");
                NoticeView.selecttitleView();
                break;
            case "3":
                Print.print("即将跳转到根据内容查询公告页面");
                NoticeView.selectcontentView();
                break;
            case "4":
                System.out.println("即将返回上一页");
                MainView.userloginSucessView();
                break;
            case "5":
                System.out.println("感谢您的使用");
                break;
            default:
                //打印提示
                System.out.println("输入范围有误,请重新输入");
                NoticeView.userView();
        }
    }
}
