package lab;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Step implements DrawableSimulable {
    private final World world;
    private final Point2D position;
    private final double size;
    private final static Image image = new Image(Step.class.getResourceAsStream("stairs.png"));

    public Step(World world, Point2D position, double size) {
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
