package com.iweb.view;

import com.iweb.controller.EUserController;
import com.iweb.controller.MainController;
import com.iweb.pojo.EUser;
import com.iweb.service.EUserService;
import com.iweb.service.MainService;
import com.iweb.util.Print;

import java.util.List;
import java.util.Scanner;

/**
 * @author zxy
 * @create 2023/6/12 11:08
 */
public class EUserView {
    public static Scanner sc = new Scanner(System.in);

    public static void rootView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加用户");
        Print.print("2.查询所有用户");
        Print.print("3.根据用户名查询用户");
        Print.print("4.根据用户状态查询用户");
        Print.print("5.删除用户");
        Print.print("6.修改用户");
        Print.print("7.返回上一页");
        Print.print("8.退出系统");
        String inputKey = sc.nextLine();
        EUserController.rootController(inputKey);
    }

    //添加用户
    public static void insertView(){
        Print.print("---添加用户界面---");
        Print.print("请输入添加用户的姓名");
        String insertUsername=sc.nextLine();
        Print.print("请输入添加用户的密码");
        String insertPassword=sc.nextLine();
        Print.print("请输入添加用户的状态（管理员/员工）");
        String insertType=sc.nextLine();
        //判断信息是否为空
        if(insertUsername.isEmpty()||insertPassword.isEmpty()||insertType.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            insertView();
        }else{
            //将获取的输入信息封装成对象
            EUser insertUser = new EUser();
            insertUser.setUsername(insertUsername);
            insertUser.setPassword(insertPassword);
            insertUser.setUser_type(insertType);
            EUserService userService = new EUserService();
            userService.insert(insertUser);
        }
    }

    //查询全部用户
    public static void selectallView() {
        EUserService userService = new EUserService();
        List<EUser> userList = userService.selectAll();
        if (userList != null) {
            for (EUser user : userList) {
                System.out.println(user);
            }
            System.out.println("加载完成，即将返回上一页");
        } else {
            System.out.println("系统内没有用户,即将返回上一页");
        }
        EUserView.rootView();
    }

    //根据用户名模糊查询
    public static void selectnameView(){
        Print.print("输入您要查询的用户名称:");
        String selectUsername = sc.nextLine();
        if(selectUsername.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selectnameView();
        }else{
            //将获取的输入信息封装成对象
            EUser selectUser = new EUser();
            selectUser.setUsername(selectUsername);
            //视图层应该将上面的selectUser传递给业务方法
            EUserService userService = new EUserService();
            List<EUser> userList = userService.listByNameLike(selectUsername);
            if (userList != null) {
                for (EUser user : userList) {
                    System.out.println(user);
                }
                System.out.println("加载完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该用户,即将返回上一页");
            }
            EUserView.rootView();
        }
    }

    public static void selecttypeView() {
        Print.print("输入您要查询的用户状态:");
        String selectType = sc.nextLine();
        if(selectType.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selecttypeView();
        }else {
            EUser selectUser = new EUser();
            selectUser.setUser_type(selectType);

            EUserService userService = new EUserService();
            List<EUser> userList = userService.listByType(selectType);
            if (userList != null) {
                for (EUser user : userList) {
                    System.out.println(user);
                }
                System.out.println("加载完成，即将返回上一页");
            } else {
                System.out.println("系统内没有符合条件的用户,即将返回上一页");
            }
            EUserView.rootView();
        }
    }

    public static void deleteView() {
        Print.print("请输入要删除的用户ID:");
        Integer deleteId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(deleteId==null){
            System.out.println("信息不可为空,请重新输入");
            deleteView();
        }else {
            Print.print("确定执行删除操作吗？（y/n）");
            String answer=sc.nextLine();
            if(answer.equals("y")||answer.equals("Y")){
                EUser deleteUser = new EUser();
                deleteUser.setId(deleteId);
                EUserService userService = new EUserService();
                userService.delete(deleteUser);
            } else if(answer.equals("n")||answer.equals("N")){
                System.out.println("退出删除操作，即将返回上一页");
                EUserView.rootView();
            }else{
                System.out.println("输入信息有误，退出删除操作，即将返回上一页");
                EUserView.rootView();
            }
        }
    }

    public static void updateView() {
        Print.print("请输入要修改的用户ID:");
        Integer updateId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if (updateId == null) {
            System.out.println("信息不可为空，请重新输入");
            sc.nextLine();  // 读取换行符
            updateView();
        } else {
            EUser updateUser = new EUser();
            updateUser.setId(updateId);

            Print.print("请输入修改后的用户名:");
            String updatedUsername = sc.nextLine();
            Print.print("请输入修改后的密码:");
            String updatedPassword = sc.nextLine();
            Print.print("请输入修改后的用户状态:");
            String updatedType = sc.nextLine();
            if (updatedUsername.isEmpty() || updatedPassword.isEmpty() || updatedType.isEmpty()) {
                System.out.println("信息不可为空，请重新输入");
                updateView();
            } else {
                updateUser.setUsername(updatedUsername);
                updateUser.setPassword(updatedPassword);
                updateUser.setUser_type(updatedType);

                EUserService userService = new EUserService();
                userService.update(updateUser);
            }
        }
    }


}
