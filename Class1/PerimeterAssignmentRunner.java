package Class1;

import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int numPoints = 0;
        for (Point currPt : s.getPoints()) {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double averageLength = 0;
        double perim = getPerimeter(s);
        int numPoints = getNumPoints(s);

        averageLength = perim / numPoints;

        return averageLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPoint = s.getLastPoint();

        for (Point currPoint : s.getPoints()) {
            double currDist = prevPoint.distance(currPoint);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            prevPoint = currPoint;
        }

        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;

        for (Point currPoint : s.getPoints()) {
            double currX = currPoint.getX();
            if (currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestPerim = 0;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currentPerim = getPerimeter(s);
            if (currentPerim > largestPerim) {
                largestPerim = currentPerim;
            }
        }

        return largestPerim;
    }

    public String getFileWithLargestPerimeter() {
        double largestPerim = 0;
        File largestPerimFile = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currentPerim = getPerimeter(s);
            if (currentPerim > largestPerim) {
                largestPerim = currentPerim;
                largestPerimFile = f;
            }
        }

        return largestPerimFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);

        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int numPoints = getNumPoints(s);
        System.out.println("Number of Points = " + numPoints);
        double averageLength = getAverageLength(s);
        System.out.println("Average length = " + averageLength);
        double largestSide = getLargestSide(s);
        System.out.println("Largest Side = " + largestSide);
        double largestX = getLargestX(s);
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeters in all the files = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String largestPerimFile = getFileWithLargestPerimeter();
        System.out.println("largest perimeters file in all the files = " + largestPerimFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void runPerimeterAssignmentRunner (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
