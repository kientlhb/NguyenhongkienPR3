package com.devmaster.ls01_spring_boot.pkg_collection_api_enhencements;
import java.util.Map;
import java.util.HashMap;
public class ForEachMapExample {
    public static void main(String[] args){
        Map<Integer,String> hmap=new HashMap<>();
        hmap.put(1,"Java Spring");
        hmap.put(2,"JavaScript");
        hmap.put(3,"PHP ");
        hmap.put(4,"C# NET Core");
        //Hien thi du lieu
        hmap.forEach((key,value)->System.out.println(key+"-"+value));
    }
}
