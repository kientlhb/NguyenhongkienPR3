package com.devmaster.ls01_spring_boot.lambda_expression;
@FunctionalInterface
interface Sayhello2{
    public void sayHello(String name);
}
public class LambdaExpression2 {
    public static void main(String[] args){
        //Lambda su dung 1 tham so
        Sayhello2 say1=(name)->{
            System.out.println("Hello"+name);
        };
        say1.sayHello("NguyenGiaDuy");
        //Ngan gon
        Sayhello2 say2=name -> {
            System.out.println("Hello"+name);
        };
        say2.sayHello("NguyenDuy");
        //ngan gon hon
        Sayhello2 say3=name->System.out.println("hello"+name);
        say3.sayHello("Duy DEp Trai");
    }
}
