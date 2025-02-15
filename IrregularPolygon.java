import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList

import gpdraw.*; // for DrawingTool

public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<>();

    // Constructor
    public IrregularPolygon() {}

    // Add a point to the polygon
    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    // Calculate the perimeter of the polygon
    public double perimeter() {
        if (myPolygon.size() < 2) return 0.0;

        double perimeter = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % myPolygon.size()); // Wrap around
            perimeter += p1.distance(p2);
        }
        return perimeter;
    }

    // Calculate the area using the Shoelace formula
    public double area() {
        if (myPolygon.size() < 3) return 0.0; // Not a valid polygon

        double sum1 = 0.0, sum2 = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double p1 = myPolygon.get(i);
            Point2D.Double p2 = myPolygon.get((i + 1) % myPolygon.size()); // Wrap around
            sum1 += p1.x * p2.y;
            sum2 += p1.y * p2.x;
        }
        return Math.abs(sum1 - sum2) / 2.0;
    }

    // Draw the polygon
    public void draw() {
        try {
            if (myPolygon.isEmpty()) return;

            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            pen.up();
            Point2D.Double first = myPolygon.get(0);
            pen.move(first.x, first.y);
            pen.down();

            for (Point2D.Double point : myPolygon) {
                pen.move(point.x, point.y);
            }
            // Close the shape
            pen.move(first.x, first.y);
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}

