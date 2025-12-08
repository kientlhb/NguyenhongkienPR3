package k23cnt3.ngd.day02.pkg_annotation.controller;
import k23cnt3.ngd.day02.pkg_annotation.Service.MyGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyGreetingController {
    @Autowired
    private final MyGreetingService myGreetingService;

    public MyGreetingController(MyGreetingService greetingService) {
        this.myGreetingService = greetingService;
    }

    @GetMapping("/my-greet")
    public String greet() {
        return myGreetingService.greet();
    }
}
