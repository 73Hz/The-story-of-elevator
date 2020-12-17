package main;
import java.util.Date;
import java.util.*;
public class TimeStop {
    public static void stop(int n){
        try {
            //System.out.println(new Date( ) + "\n");
            Thread.sleep(1000*n);   // 休眠3秒
            //System.out.println(new Date( ) + "\n");
        } catch (Exception e) {
            System.out.println("Got an exception!");
        /*n*=1000;
        Date date = new Date();
        long nowtime=date.getTime();
        System.out.println(nowtime);
        for(long temp=date.getTime();temp-nowtime<=n;temp=date.getTime()){
            System.out.println(date.getTime());*/
        }
    }

}
