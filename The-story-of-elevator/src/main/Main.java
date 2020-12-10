package main;
import java.math.*;

import static java.lang.StrictMath.max;

public class Main {
    public static void main(String[] args){
        Elevator temp=new Elevator(10);
        temp.adduser(1,2,4);
        temp.adduser(1,1,5);
        temp.adduser(1,9,5);
        temp.adduser(1,5,4);
        temp.adduser(1,0,4);
        temp.adduser(1,3,6);
        temp.print();
        temp.run();
        //        temp.print();1
//        System.out.print("asd");

    }
}
