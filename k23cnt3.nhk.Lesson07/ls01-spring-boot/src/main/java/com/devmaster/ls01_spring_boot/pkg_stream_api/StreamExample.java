package com.devmaster.ls01_spring_boot.pkg_stream_api;
import java.util.Arrays;
import java.util.List;
public class StreamExample {
    List<Integer> integerList=Arrays.asList(11,22,33,44,55,66);
    //dem cac so chan
    //khong dung stream
    public void withoutStream(){
        int count=0;
        for(Integer integer:integerList){
            if (integer % 2==0) count++;

        }
        System.out.println("WithoutStram-> So phan tu chan:"+count);
    }
    //Dung Stream
    public void withStream(){
        long count=integerList.stream().filter(num->num%2==0).count();
        System.out.println("WithStream-> So phan tu chan:"+count);
    }
    public static void main(String[] args){
        StreamExample streamExample=new StreamExample();
        streamExample.withoutStream();
        streamExample.withStream();
    }

}
