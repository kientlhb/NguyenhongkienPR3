package com.devmaster.ls01_spring_boot.lambda_expression;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class SortLambdaExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java SpringBoot", "C# NetCore", "PHP", "JavaScript");

        // Sắp xếp danh sách bằng biểu thức lambda
        Collections.sort(list, (str1, str2) -> str1.compareTo(str2));

        // In kết quả sau khi sắp xếp
        for (String str : list) {
            System.out.println(str);
        }
    }
}
