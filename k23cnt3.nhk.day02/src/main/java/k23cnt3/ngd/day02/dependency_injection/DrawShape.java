package k23cnt3.ngd.day02.dependency_injection;
// dependency injection
interface Shape {
    void draw();
}

class CircleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("CircleShape draw");
    }
}

class RectangleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("RectangleShape draw");
    }
}
public class DrawShape {
    private Shape shape;

    public DrawShape(Shape shape) {
        this.shape = shape;
    }

    public void Draw() {
        shape.draw();
    }

    public static void main(String[] args) {
        DrawShape drawCircle = new DrawShape(new CircleShape());
        drawCircle.Draw();

        DrawShape drawRectangle = new DrawShape(new RectangleShape());
        drawRectangle.Draw();
    }
}
