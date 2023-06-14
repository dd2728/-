package com.iweb.view;

import com.iweb.controller.EPositionController;
import com.iweb.pojo.EPosition;
import com.iweb.pojo.EUser;
import com.iweb.service.EPositionService;
import com.iweb.util.Print;

import java.util.List;
import java.util.Scanner;

/**
 * @author zxy
 * @create 2023/6/12 16:44
 */
public class EPositionView {
    public static Scanner sc = new Scanner(System.in);

    public static void rootView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加职位");
        Print.print("2.查询所有职位");
        Print.print("3.根据名称查询职位");
        Print.print("4.删除职位");
        Print.print("5.修改职位");
        Print.print("6.返回上一页");
        Print.print("7.退出系统");
        String inputKey = sc.nextLine();
        EPositionController.rootController(inputKey);
    }

    public static void userView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.查询所有职位");
        Print.print("2.根据名称查询职位");
        Print.print("3.返回上一页");
        Print.print("4.退出系统");
        String inputKey = sc.nextLine();
        EPositionController.userController(inputKey);
    }

    //添加职位
    public static void insertView(){
        Print.print("---添加职位界面---");
        Print.print("请输入添加职位的名称");
        String insertEPositionname=sc.nextLine();
        if(insertEPositionname.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            insertView();
        }else{
            //将获取的输入信息封装成对象
            EPosition insertEPosition = new EPosition();
            insertEPosition.setPosition_name(insertEPositionname);
            EPositionService ePositionService = new EPositionService();
            ePositionService.insert(insertEPosition);
        }
    }

    //查询全部职位
    public static void selectallView() {
        EPositionService ePositionService = new EPositionService();
        List<EPosition> ePositionList = ePositionService.selectAll();
        if (ePositionList != null&&!ePositionList.isEmpty()) {
            for (EPosition ePosition : ePositionList) {
                System.out.println(ePosition.toString());
            }
            System.out.println("查询完成，即将返回上一页");
        } else {
            System.out.println("系统内没有职位，即将返回上一页");
        }
        String currentUserType = EUser.getCurrentUserType();
        if(currentUserType.equals("管理员")){
            EPositionView.rootView();
        }else{
            EPositionView.userView();
        }
    }

    //根据名称模糊查询
    public static void selectnameView(){
        Print.print("输入您要查询的职位名称:");
        String selectname = sc.nextLine();
        if(selectname.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selectnameView();
        }else {
            //将获取的输入信息封装成对象
            EPosition selectePosition = new EPosition();
            selectePosition.setPosition_name(selectname);
            EPositionService ePositionService = new EPositionService();
            List<EPosition> ePositionList = ePositionService.listByNameLike(selectname);
            if (ePositionList != null && !ePositionList.isEmpty()) {
                for (EPosition ePosition : ePositionList) {
                    System.out.println(ePosition);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该职位，即将返回上一页");
            }
            String currentUserType = EUser.getCurrentUserType();
            if (currentUserType.equals("管理员")) {
                EPositionView.rootView();
            } else {
                EPositionView.userView();
            }
        }
    }

    //删除职位
    public static void deleteView() {
        Print.print("请输入要删除的职位ID:");
        Integer deleteId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(deleteId==null){
            System.out.println("信息不可为空,请重新输入");
            deleteView();
        }else {
            Print.print("确定执行删除操作吗？（y/n）");
            String answer=sc.nextLine();
            if(answer.equals("y")||answer.equals("Y")){
                EPosition deleteePosition = new EPosition();
                deleteePosition.setId(deleteId);
                EPositionService ePositionService = new EPositionService();
                ePositionService.delete(deleteePosition);
            } else if(answer.equals("n")||answer.equals("N")){
                System.out.println("退出删除操作，即将返回上一页");
                EPositionView.rootView();
            }else{
                System.out.println("输入信息有误，退出删除操作，即将返回上一页");
                EPositionView.rootView();
            }

        }
    }

    //修改职位
    public static void updateView() {
        Print.print("请输入要修改的职位ID:");
        Integer updateId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(updateId==null){
            System.out.println("信息不可为空,请重新输入");
            updateView();
        }else {
            EPosition updateePosition = new EPosition();
            updateePosition.setId(updateId);

            Print.print("请输入修改后的职位名称:");
            String updatedEPositionname = sc.nextLine();
            if(updatedEPositionname.isEmpty()){
                System.out.println("信息不可为空,请重新输入");
                updateView();
            }else {
                updateePosition.setPosition_name(updatedEPositionname);

                EPositionService ePositionService = new EPositionService();
                ePositionService.update(updateePosition);
            }
        }
    }
}
