package k23cnt3.ngd.day02.ioc;

// Lớp Service: cung cấp một chức năng nào đó
class IoCService {
    public void serve() {
        System.out.println("Service is serving");
    }
}

// Lớp Client: phụ thuộc vào Service, nhưng được inject từ ngoài (DI)
class IoCClient {
    private IoCService iocService;

    // Dùng Dependency Injection (DI) để truyền Service vào thay vì tự tạo nó
    public IoCClient(IoCService service) {
        this.iocService = service;
    }

    public void doSomething() {
        iocService.serve();
    }
}

// Lớp chứa hàm main để chạy chương trình
public class IoCWithDI {
    public static void main(String[] args) {
        // Tạo đối tượng Service và truyền nó vào Client
        IoCService service = new IoCService();
        IoCClient client = new IoCClient(service);
        client.doSomething();
    }
}
