package com.devmaster.ls01_spring_boot.lambda_expression;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
class Book{
    int id;
    String name;
    float price;
    public Book(int id,String name,float price){
        this.id=id;
        this.name=name;
        this.price=price;

    }
    @Override
    public String toString(){
        return "Book{"+
                "id="+id+
                ",name="+name+'\''+
                ",price="+price+
                '}';
    }
}
public class LambdaExpression5{
    public static void main(String[] args){
        List<Book> books=new ArrayList<Book>();
        books.add(new Book(1,"Lap trinh Java",9.95f));
        books.add(new Book(2,"PHP",34.25f));
        books.add(new Book(3,"C#",4.5f));
        books.add(new Book(4,"Java SpringBoot",13.5f));
        books.add(new Book(5,"JavaScrips",29.5f));
        //Loc sach co gia lon hon 15.000
        Stream<Book> filter=books.stream().filter(b->b.price>15);
        filter.forEach(System.out::println);
    }
}