public class SolidCuboid extends Cuboid implements Solid {
    private final double density;
    private final SolidImpl solidImpl;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
        this.solidImpl = new SolidImpl(new Cuboid(height, width, length), density);
    }

    public String toString() {
        return String.format("solid-cuboid [%.2f x %.2f x %.2f] with a mass of %.2f",
        this.getHeight(), this.getWidth(), this.getLength(), this.mass());
    }

    public double volume() {
        return super.volume();
    }

    public double mass() {
        return this.solidImpl.mass();
    }
}
