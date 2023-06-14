package com.iweb.view;

import com.iweb.controller.MainController;
import com.iweb.pojo.EUser;
import com.iweb.service.MainService;
import com.iweb.util.Print;
import com.iweb.util.StringUtil;

import java.util.Scanner;


public class MainView {
    //全局只需要使用这一个sc对象
    public static Scanner sc = new Scanner(System.in);
    public static void mainView(){
        Print.print("欢迎来到简陋的员工管理系统！！！");
        Print.print("请输入您的选项");
        Print.print("1.登录");
        Print.print("2.注册");
        Print.print("3.退出系统");
        String mainInputKey = sc.nextLine();
        MainController.mainController(mainInputKey);
    }


    public static void loginView(){
        Print.print("请输入你登录的用户名:");
        String inputUsername = sc.nextLine();
        Print.print("请输入您的密码:");
        String inputPassword = sc.nextLine();
        if(inputUsername.isEmpty()||inputPassword.isEmpty()){
            System.out.println("用户名或密码不可为空,请重新输入");
            loginView();
        }else {
            //将获取的输入信息封装成对象
            EUser inputUser = new EUser();
            inputUser.setUsername(inputUsername);
            inputUser.setPassword(inputPassword);
            //获取验证码:如果发现验证码不对 则直接重新登录
            String confirmCode = StringUtil.getRandomString();
            Print.print("验证码为:" + confirmCode + ",请输入验证码进行验证,验证码不区分大小写");
            String inputConfirmCode = sc.nextLine();
            if(inputConfirmCode.isEmpty()){
                System.out.println("验证码不可为空,请重新输入");
                confirmCode = StringUtil.getRandomString();
                Print.print("验证码为:" + confirmCode + ",请输入验证码进行验证,验证码不区分大小写");
                inputConfirmCode = sc.nextLine();
            }
            if (confirmCode.equalsIgnoreCase(inputConfirmCode)) {
                //如果发现验证码验证通过,则进行验证 在进入到后续流程
                Print.print("验证码通过,正在验证用户名和密码,请稍后...");
                //视图层应该将上面的inputUser传递给业务方法 根据业务方法判断用户是否登录成功
                boolean isLogin = MainService.login(inputUser);
                String utype=inputUser.getUser_type();
                EUser.setCurrentUserType(inputUser.getUser_type());
                //如果登录成功,则跳转到下一级页面
                if (isLogin) {
                    //跳转到下一级页面
                    if(utype.equals("管理员")){
                        rootloginSucessView();
                    }else{
                        userloginSucessView();
                    }
                } else {
                    //否则 重新访问登录页面
                    loginView();
                }
            } else {
                //直接要求用户重新输入用户名 密码 新的验证码
                Print.print("验证码输入有误,请重新输入!");
                loginView();
            }

        }
    }

    public static void rootloginSucessView(){
        Print.print("请输入你想访问的功能");
        Print.print("1.用户管理");
        Print.print("2.部门管理");
        Print.print("3.职位管理");
        Print.print("4.员工管理");
        Print.print("5.公告管理");
        Print.print("6.退出系统");
        String inputKey = sc.nextLine();
        //将输入的数据交给下一层控制器处理
        MainController.rootloginSuccessController(inputKey);
    }


    public static void userloginSucessView(){
        Print.print("请输入你想访问的功能");
        Print.print("1.查看部门");
        Print.print("2.查看职位");
        Print.print("3.查看公告");
        Print.print("4.退出系统");
        String inputKey = sc.nextLine();
        //将输入的数据交给下一层控制器处理
        MainController.userloginSuccessController(inputKey);
    }


    public static void registerView(){
        System.out.println("该页面只可以注册普通员工！");
        Print.print("输入您要注册的用户名称:");
        String registerUsername = sc.nextLine();
        Print.print("输入您要注册的用户密码:");
        String registerPassword = sc.nextLine();
        if(registerUsername.isEmpty()||registerPassword.isEmpty()){
            System.out.println("用户名或密码不可为空,请重新输入");
            registerView();
        }else{
            //将获取的输入信息封装成对象
            EUser registerUser = new EUser();
            registerUser.setUsername(registerUsername);
            registerUser.setPassword(registerPassword);
            //视图层应该将上面的registerUser传递给业务方法 根据业务方法判断用户是否注册成功
            boolean isRegister = MainService.register(registerUser);
            if(isRegister){
                //注册成功后跳转登陆界面
                loginView();
            }else {
                //否则 重新访问首页
                mainView();
            }
        }
    }
}
