package k23cnt3.ngd.day02.pkg_annotation.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
   @GetMapping("/hello")
    public String sayHello(){
       return "Hello,SpringBoot";
   }
    }
