class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return String.format("point (%.3f, %.3f)", this.x, this.y);
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }

    public Point midPoint(Point pt1) {
        Point newPoint;
        double newX = (pt1.getX() + this.x) / 2;
        double newY = (pt1.getY() + this.y) / 2;
        newPoint = new Point(newX, newY);
        return newPoint;
    }

    public double angleTo(Point pt1) {
        double angle;
        angle = Math.atan2(pt1.getY() - this.y, pt1.getX() - this.x);
        return angle;
    }

    public Point moveTo(double angle, double length) {
        Point newPoint;
        double newX = this.x + Math.cos(angle) * length;
        double newY = this.y + Math.sin(angle) * length;
        newPoint = new Point(newX, newY);
        return newPoint;
    }

    public double distanceTo(Point pt1) {
        return Math.sqrt((pt1.getX() - this.x) * (pt1.getX() - this.x) + 
                        (pt1.getY() - this.y) * (pt1.getY() - this.y));
    }

    public boolean equals(Point pt1) {
        if (this.x == pt1.getX() && this.y == pt1.getY()) {
            return true;
        }
        return false;
    }
}
