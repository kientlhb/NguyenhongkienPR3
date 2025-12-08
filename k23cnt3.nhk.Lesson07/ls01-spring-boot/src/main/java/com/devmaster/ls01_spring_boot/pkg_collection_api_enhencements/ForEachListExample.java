package com.devmaster.ls01_spring_boot.pkg_collection_api_enhencements;
import java.util.Arrays;
import java.util.List;
public class ForEachListExample {
    public static void main(String[] args){
        List<String> languages=Arrays.asList("Java Spring","PHP","C#","JavaScrips","ASP Net");
        System.out.println("Su dung bieu thuc Lambda:");
        languages.forEach(lang->System.out.println(lang));
        System.out.println("Su dung method Reference: ");
        languages.forEach(System.out::println);
    }
}
