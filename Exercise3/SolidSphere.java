import java.lang.Math;

public class SolidSphere extends Sphere implements Solid {
    private final double density;
    private final SolidImpl solidImpl;
    private static final double FORMULA1 = 4;
    private static final double FORMULA2 = 3;
    private static final double FORMULA3 = 3;

    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
        this.solidImpl = new SolidImpl(new Sphere(radius), density);
    }

    public String toString() {
        return String.format("solid-sphere [%.2f] with a mass of %.2f", 
        this.getRadius(), this.mass());
    }

    public double volume() {
        return FORMULA1 / FORMULA2 * Math.PI * Math.pow(this.getRadius(), FORMULA3);
    }

    public double mass() {
        return this.solidImpl.mass();
    }
}