package lab;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;


public class Wall implements DrawableSimulable {
    private final World world;
    public List<Brick[]> wallList = new ArrayList<>();

    public Wall(World world, Point2D start, int count, int size) {
        this.world = world;
        Brick[] BrickArray = new Brick[count];
        for (int i = 0; i < BrickArray.length; i++) {
            BrickArray[i] = new Brick(world, new Point2D(start.getX() + i * 20, start.getY()), size);
        }
        wallList.add(BrickArray);
    }

    public static Wall createWall(World world, double x, double y, int count, int size) {
        return new Wall(world, new Point2D(x, y), count, size);
    }

    public void draw(GraphicsContext gc) {
        for (Brick[] brickArray : wallList) {
            for (Brick brick : brickArray) {
                brick.draw(gc);
            }
        }
    }

    public Rectangle2D getBoundingBox() {
        return new Rectangle2D(this.wallList.get(0)[0].getPosition().getX(),
                               this.wallList.get(0)[0].getPosition().getY(),
                               this.wallList.get(0)[0].getSize() * this.wallList.get(0).length,
                           this.wallList.get(0)[0].getSize());
    }

    @Override
    public void simulate(double timeDelta) {}
}
