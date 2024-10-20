package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Brick implements DrawableSimulable{
    private final World world;
    private Point2D position;
    private double size;
    private final static Image image = new Image(Step.class.getResourceAsStream("kirpic.png"));

    public Brick(World world) {this.world = world;}

    public Brick(World world, Point2D position, double size) {
        this.world = world;
        this.position = position;
        this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY(), size, size);
    }

    public Point2D getPosition() {
        return this.position;
    }

    public double getSize() {
        return this.size;
    }

    @Override
    public void simulate(double timeDelta) {}
}
