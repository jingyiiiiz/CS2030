import java.lang.Math;

public class Sphere implements Shape3D {
    private final double radius;
    private static final double FORMULA1 = 4;
    private static final double FORMULA2 = 3;
    private static final double FORMULA3 = 3;

    Sphere(double radius) {
        this.radius = radius;
    }

    public String toString() {
        return String.format("sphere [%.2f]", this.radius);
    }

    public double volume() {
        return FORMULA1 / FORMULA2 * Math.PI * Math.pow(this.radius, FORMULA3);
    }

    public double getRadius() {
        return this.radius;
    }
}