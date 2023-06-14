package com.iweb.view;

import com.iweb.controller.EPositionController;
import com.iweb.controller.NoticeController;
import com.iweb.pojo.EUser;
import com.iweb.pojo.Notice;
import com.iweb.pojo.Notice;
import com.iweb.service.NoticeService;
import com.iweb.util.Print;
import org.omg.CORBA.NVList;

import java.util.List;
import java.util.Scanner;

/**
 * @author zxy
 * @create 2023/6/12 19:20
 */
public class NoticeView {
    public static Scanner sc = new Scanner(System.in);

    public static void rootView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.添加公告");
        Print.print("2.查询所有公告");
        Print.print("3.根据标题查询公告");
        Print.print("4.根据内容查询公告");
        Print.print("5.删除公告");
        Print.print("6.修改公告");
        Print.print("7.返回上一页");
        Print.print("8.退出系统");
        String inputKey = sc.nextLine();
        NoticeController.rootController(inputKey);
    }

    public static void userView() {
        Print.print("请输入你想访问的功能");
        Print.print("1.查询所有公告");
        Print.print("2.根据标题查询公告");
        Print.print("3.根据内容查询公告");
        Print.print("4.返回上一页");
        Print.print("5.退出系统");
        String inputKey = sc.nextLine();
        NoticeController.userController(inputKey);
    }

    //添加公告
    public static void insertView(){
        Print.print("---添加公告界面---");
        Print.print("请输入添加公告的标题");
        String insertNoticetitle=sc.nextLine();
        Print.print("请输入添加公告的内容");
        String insertNoticecontent=sc.nextLine();
        if(insertNoticetitle.isEmpty()||insertNoticecontent.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            insertView();
        }else{
            //将获取的输入信息封装成对象
            Notice insertNotice = new Notice();
            insertNotice.setTitle(insertNoticetitle);
            insertNotice.setContent(insertNoticecontent);

            NoticeService noticeService = new NoticeService();
            noticeService.insert(insertNotice);
        }
    }

    //查询全部公告
    public static void selectallView() {
        NoticeService noticeService = new NoticeService();
        List<Notice> noticeList = noticeService.selectAll();
        if (noticeList != null&&!noticeList.isEmpty()) {
            for (Notice notice : noticeList) {
                System.out.println(notice.toString());
            }
            System.out.println("查询完成，即将返回上一页");
        } else {
            System.out.println("系统内没有公告，即将返回上一页");
        }
        String currentUserType = EUser.getCurrentUserType();
        if (currentUserType.equals("管理员")) {
            NoticeView.rootView();
        } else {
            NoticeView.userView();
        }
    }

    //根据标题模糊查询
    public static void selecttitleView(){
        Print.print("输入您要查询的公告标题:");
        String selecttitle = sc.nextLine();
        if(selecttitle.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selecttitleView();
        }else {
            //将获取的输入信息封装成对象
            Notice selectnotice = new Notice();
            selectnotice.setTitle(selecttitle);
            NoticeService noticeService = new NoticeService();
            List<Notice> noticeList = noticeService.listByTitleLike(selecttitle);
            if (noticeList != null && !noticeList.isEmpty()) {
                for (Notice notice : noticeList) {
                    System.out.println(notice);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该公告，即将返回上一页");
            }
            String currentUserType = EUser.getCurrentUserType();
            if (currentUserType.equals("管理员")) {
                NoticeView.rootView();
            } else {
                NoticeView.userView();
            }
        }
    }
    //根据内容模糊查询
    public static void selectcontentView(){
        Print.print("输入您要查询的公告内容:");
        String selectcontent = sc.nextLine();
        if(selectcontent.isEmpty()){
            System.out.println("信息不可为空,请重新输入");
            selectcontentView();
        }else {
            //将获取的输入信息封装成对象
            Notice selectnotice = new Notice();
            selectnotice.setContent(selectcontent);
            NoticeService noticeService = new NoticeService();
            List<Notice> noticeList = noticeService.listByTitleLike(selectcontent);
            if (noticeList != null && !noticeList.isEmpty()) {
                for (Notice notice : noticeList) {
                    System.out.println(notice);
                }
                System.out.println("查询完成，即将返回上一页");
            } else {
                System.out.println("系统内没有该公告，即将返回上一页");
            }
            String currentUserType = EUser.getCurrentUserType();
            if (currentUserType.equals("管理员")) {
                NoticeView.rootView();
            } else {
                NoticeView.userView();
            }
        }
    }
    //删除公告
    public static void deleteView() {
        Print.print("请输入要删除的公告ID:");
        Integer deleteId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(deleteId==null){
            System.out.println("信息不可为空,请重新输入");
            deleteView();
        }else {
            Print.print("确定执行删除操作吗？（y/n）");
            String answer=sc.nextLine();
            if(answer.equals("y")||answer.equals("Y")){
                Notice deletenotice = new Notice();
                deletenotice.setId(deleteId);
                NoticeService noticeService = new NoticeService();
                noticeService.delete(deletenotice);
            } else if(answer.equals("n")||answer.equals("N")){
                System.out.println("退出删除操作，即将返回上一页");
                NoticeView.rootView();
            }else{
                System.out.println("输入信息有误，退出删除操作，即将返回上一页");
                NoticeView.rootView();
            }

        }
    }

    //修改公告
    public static void updateView() {
        Print.print("请输入要修改的公告ID:");
        Integer updateId = sc.nextInt();
        sc.nextLine();  // 读取换行符
        if(updateId==null){
            System.out.println("信息不可为空,请重新输入");
            updateView();
        }else {
            Notice updatenotice = new Notice();
            updatenotice.setId(updateId);

            Print.print("请输入修改后的公告标题:");
            String updatedNoticetitle = sc.nextLine();
            Print.print("请输入修改后的公告内容:");
            String updatedNoticecontent = sc.nextLine();

            if(updatedNoticetitle.isEmpty()||updatedNoticecontent.isEmpty()){
                System.out.println("信息不可为空,请重新输入");
                updateView();
            }else {
                updatenotice.setTitle(updatedNoticetitle);
                updatenotice.setContent(updatedNoticecontent);
                updatenotice.setId(updateId);

                NoticeService noticeService = new NoticeService();
                noticeService.update(updatenotice);
            }
        }
    }
    
}
