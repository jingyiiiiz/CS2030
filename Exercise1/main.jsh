double epsilon = 1e-15;

double distanceBetween(Point p, Point q) {
    double dx = p.getX() - q.getX();
    double dy = p.getY() - q.getY();

    return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
}

boolean circleContainsPoint(Circle c, Point p) {
    return distanceBetween(c.getCentre(), p) < c.getRadius() + epsilon;
}

int findMaxDiscCoverage(List<Point> points) {
    int maxDiscCoverage = 0;
    int numOfPoints = points.size();

    for (int i = 0; i < numOfPoints - 1; i++) {
        for (int j = i + 1; j < numOfPoints; j++) {
            Point p = points.get(i);
            Point q = points.get(j);
            double distPQ = distanceBetween(p, q);
            if (distPQ < 2.0 + epsilon && distPQ > 0) {
                int coverage = 0;
		Circle c = createUnitCircle(p, q);
                for (Point point : points) {
                    if (circleContainsPoint(c, point)) {
                        coverage = coverage + 1;
                    }
                }
                if (coverage > maxDiscCoverage) {
                    maxDiscCoverage = coverage;
                }
            }
        }
    }
    return maxDiscCoverage;
}

Circle createUnitCircle(Point p, Point q) {
	Point midpoint = p.midPoint(q);
	double radius = 1;
	double rightSide = distanceBetween(p, q) / 2;
	double remaining = Math.sqrt(1 - rightSide * rightSide);
	double angle = p.angleTo(q);
	double newAngle = Math.PI / 2 + angle;
	Point centre = midpoint.moveTo(newAngle, remaining); 
	return new Circle(centre, radius);	
}
