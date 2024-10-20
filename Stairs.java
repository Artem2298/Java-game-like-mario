package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Stairs implements DrawableSimulable {
    private final World world;
    public List<Step[]> stairList = new ArrayList<>();

    public Stairs(World world, Point2D start, int count, int size) {
        this.world = world;
        Step[] StepsArray = new Step[count];
        for (int i = 0; i < StepsArray.length; i++) {
            StepsArray[i] = new Step(world, new Point2D(start.getX(), start.getY() + i * 35), size);
        }
        stairList.add(StepsArray);
    }

    public static Stairs createStairs(World world, double x, double y, int count, int size) {
        return new Stairs(world, new Point2D(x, y), count, size);
    }

    public void draw(GraphicsContext gc) {
        for (Step[] stepArray : stairList) {
            for (Step step : stepArray) {
                step.draw(gc);
            }
        }
    }

    public Point2D helpForBox () {
        Point2D point = new Point2D(this.stairList.get(0)[0].getPosition().getX() + this.stairList.get(0)[0].getSize()/2,
                this.stairList.get(0)[0].getPosition().getY());
        return point;
    }

    public Rectangle2D getBoundinBox() {
        return new Rectangle2D(helpForBox().getX(), helpForBox().getY(), 1,
                this.stairList.get(0)[0].getSize() * this.stairList.get(0).length);
    }

    @Override
    public void simulate(double timeDelta) {}

}


