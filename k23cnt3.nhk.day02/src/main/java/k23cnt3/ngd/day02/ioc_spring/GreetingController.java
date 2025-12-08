package k23cnt3.ngd.day02.ioc_spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GreetingController {
    private final GreetingService greetingService;
    //Su dung constructor-base
    @Autowired
    public GreetingController(GreetingService greetingService){
        this.greetingService=greetingService;
    }
    @GetMapping("/greet")
public String greet(){
        return greetingService.greet("Nguyen Duy");
    }
}
