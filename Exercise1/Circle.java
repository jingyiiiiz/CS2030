class Circle {
    private final Point centre;
    private final double radius;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public String toString() {
        return String.format("circle of radius %.1f centred at %s", this.radius, this.centre);
    }

    Point getCentre() {
        return this.centre;
    }

    double getRadius() {
        return this.radius;
    }

    public boolean contains(Point pt) {
        return this.centre.distanceTo(pt) <= this.radius;
    }
}
