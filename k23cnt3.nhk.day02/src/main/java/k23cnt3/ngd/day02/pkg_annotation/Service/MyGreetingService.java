package k23cnt3.ngd.day02.pkg_annotation.Service;
import org.springframework.stereotype.Service;
@Service
public class MyGreetingService {
    public String greet(){
        return "<h1>Hello from MyGreetingService!";

    }
}
