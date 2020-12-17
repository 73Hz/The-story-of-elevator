package main;

//import com.sun.xml.internal.messaging.saaj.soap.FastInfosetDataContentHandler;

import java.util.Scanner;
import java.io.*;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Elevator {
    Elevator(int n) {
        floorCnt = n;
        userEndPoint = new int[n + 1];
        userStartPoint = new int[n + 1][n + 1];
        floorTop = n;
        up = true;
        endPosition = nowPosition = n / 2;
    }

    private Boolean up;//true表示往上
    int nowPosition;//电梯现在的位置
    int endPosition;//电梯要去的位置
    private int[] userEndPoint;//在电梯内用户的终点
    private int userEndCnt = 0;//电梯内人数
    private int[][] userStartPoint;//电梯外用户需求，[用户起点楼层][用户目的楼层]
    private int userStartCnt = 0;//用户请求人数
    private int floorCnt;//电梯楼层数
    private int floorLowest = 0;//最低楼层(可能有-3等)
    private int floorTop;//最高楼层

    public void print() {
        for (int i = 0; i <= floorCnt; i++) {
            for (int t = 0; t <= floorCnt; t++) {
                if (userStartPoint[i][t] > 0)
                    System.out.println("userStartPoint[" + i + "][" + t + "] = " + userStartPoint[i][t]);
            }
        }

    }

    public Boolean adduser(int sum, int start, int end) {//人数，起点，终点
        if (start < floorLowest || start > floorTop || end < floorLowest || end > floorTop)
            return false;
        userStartPoint[start - floorLowest][end - floorLowest] += sum;
        userStartCnt += sum;
        return true;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        while (userEndCnt != 0 || userStartCnt != 0) {
            //System.out.println("endCnt = " + userEndCnt + "   startCnt = " + userStartCnt);
            //确定终点
            endPosition = findEndPosition();
            //System.out.println("endPosition =" + endPosition + "   up=" + up);
            //一层一层走向终点
            int change = 0;
            if (up)
                change = 1;
            else
                change = -1;
            for (; ; nowPosition += change) {
                System.out.print("电梯现在在第"+nowPosition+"层:");
                int sum = 0;
                for (int i = nowPosition; i >= 0 && i <= floorCnt; i += change) {
                    if (userStartPoint[nowPosition][i] != 0) {//在nowPosotion层上客，去往i层
                        sum += userStartPoint[nowPosition][i];
                        if(up)
                            endPosition=max(endPosition,i);
                        else
                            endPosition=min(endPosition,i);
                        userEndPoint[i] += userStartPoint[nowPosition][i];
                        userStartCnt -= userStartPoint[nowPosition][i];
                        userStartPoint[nowPosition][i] = 0;
                    }
                }
                if (sum != 0) {
                    System.out.print("上客 " + sum + " 人   ");
                    userEndCnt += sum;
                }
                if (userEndPoint[nowPosition] != 0) {
                    System.out.print("下客 " + userEndPoint[nowPosition] + " 人   ");
                    userEndCnt -= userEndPoint[nowPosition];
                    userEndPoint[nowPosition] = 0;
                }
                if (nowPosition == endPosition)
                    break;
                System.out.println();
                TimeStop.stop(1);
            }
            up = !up;
            System.out.println("电梯改变运行方向\n\n\n");
            TimeStop.stop(1);
            //sc.nextLine();
        }
    }

    private int findEndPosition() {
        int temp = nowPosition;
        if (up) {//电梯是往上走的
            if (userEndCnt != 0)//找电梯内用户的目的地最高点
                for (int i = temp + 1; i < floorCnt; i++)
                    if (userEndPoint[i] != 0)
                        temp = max(temp, i);
            if (userStartCnt != 0)//找电梯外有需求的用户的目的地最高点
                for (int i = temp + 1; i <= floorCnt; i++)
                    for (int t = 0; t <= floorCnt; t++)
                        if (userStartPoint[i][t] > 0)
                            temp = max(temp, i);

        } else {
            if (userEndCnt != 0)//找电梯内用户的目的地最低点
                for (int i = temp - 1; i >= 0; i--)
                    if (userEndPoint[i] != 0)
                        temp = min(temp, i);
            if (userStartCnt != 0)//找电梯外有需求的用户的目的地最低点
                for (int i = temp - 1; i >= 0; i--)
                    for (int t = floorCnt; t >= 0; t--)
                        if (userStartPoint[i][t] != 0)
                            temp = min(temp, i);
        }
        //System.out.println("now = "+nowPosition+"   floorCnt = " + floorCnt+"   temp = " + temp+"   up="+up);
        return temp;
    }
}
