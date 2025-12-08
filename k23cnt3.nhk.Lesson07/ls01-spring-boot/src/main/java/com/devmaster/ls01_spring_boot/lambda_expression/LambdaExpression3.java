package com.devmaster.ls01_spring_boot.lambda_expression;

@FunctionalInterface
interface Calculator1 {
    int add(int a, int b);
}

@FunctionalInterface
interface Calculator2 {
    void add(int a, int b);
}

public class LambdaExpression3 {
    public static void main(String[] args) {
        // Biểu thức lambda có kiểu trả về int
        Calculator1 calc1 = (int a, int b) -> (a + b);
        System.out.println(calc1.add(11, 12));

        // Biểu thức lambda kiểu void - không nên return (a+b) vì không in ra
        // Sửa thành System.out.println trong lambda
        Calculator2 calc2 = (a, b) -> System.out.println(a + b);
        calc2.add(21, 22);

        // Viết lambda in kết quả trực tiếp
        Calculator2 calc3 = (a, b) -> System.out.println(a + b);
        calc3.add(31, 32);

        // Lambda có nhiều dòng lệnh (dùng ngoặc khối {})
        Calculator2 calc4 = (a, b) -> {
            int sum = a + b;
            System.out.println(a + " + " + b + " = " + sum);
        };
        calc4.add(41, 42);
    }
}
