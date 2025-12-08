package com.devmaster.ls01_spring_boot.lambda_expression;

@FunctionalInterface
interface SayHello1 {
    void sayHello();
}

public class LambdaExpression1 {
    public static void main(String[] args) {
        SayHello1 sayHello = () -> {
            System.out.println("Hello World");
        };
        sayHello.sayHello();
    }
}
