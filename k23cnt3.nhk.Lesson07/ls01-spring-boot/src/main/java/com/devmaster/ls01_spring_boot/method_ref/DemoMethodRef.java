package com.devmaster.ls01_spring_boot.method_ref;
import java.util.Arrays;
@FunctionalInterface
interface ExecuteFunction{
    public int execute(int a,int b);
}
class MathUtils{
    public MathUtils(){}
    public MathUtils(String str){
        System.out.println("MathUtils:"+str);
    }
    public static int sum(int a,int b){
        return a+b;
    }
    public static int minus(int a,int b){
        return a-b;
    }
    public static int multiply(int a,int b){
        return a*b;
    }
}
public class DemoMethodRef {
    public static void main(String[] args){
        int a=10;
        int b=20;
        int sum=doAction(a,b,MathUtils::sum);//MathUtils.sum(a,b);
        System.out.println(a+"+"+b+"="+sum);
        int minus=doAction(a,b,MathUtils::minus);
        System.out.println(a+"-"+b+"="+minus);
        //Tham chieu den 1 instance method cua 1 doi tuong cu the
        MathUtils mathUtils=new MathUtils();
        int multiply=doAction(a,b,MathUtils::multiply);
        System.out.println(a+"*"+b+"="+multiply);
        //Tham chieu den 1 instance method cua 1 doi tuong tuy y cua 1 kieu cu the
        String[] stringArray={"Java","C#","PHP","C++","JavaScrips"};
        Arrays.sort(stringArray,String::compareToIgnoreCase);
        for (String str:stringArray){
            System.out.println(str);
        }

    }
    public static int doAction(int a,int b,ExecuteFunction func){
        return func.execute(a,b);
    }
}
