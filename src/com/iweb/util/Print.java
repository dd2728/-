package com.iweb.util;


public class Print {
   public static void print(String printMessage){
       //利用一个特殊的代码 Thread.sleep() 这个方法可以让
       //代码暂时停止运行一段时间 时间单位为ms
       //将传入的字符串切分为字符数组 然后逐个打印出来
       char[] cs = printMessage.toCharArray();
       for (int i = 0; i < cs.length; i++) {
           System.out.print(cs[i]);
           try {
               Thread.sleep(40);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       System.out.println();
   }
}
