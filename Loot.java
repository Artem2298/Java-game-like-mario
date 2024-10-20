package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Loot implements DrawableSimulable{
    private World world;
    private Point2D position;
    private double size;
    private static Image image = new Image(Step.class.getResourceAsStream("loot.png"));

    public Loot(World world, Point2D position, double size) {
        this.world = world;
        this.position = position;
        this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY(), size, size);
    }

    @Override
    public void simulate(double timeDelta) {}

    public Rectangle2D getBoundinBox() {
        return new Rectangle2D(position.getX(), position.getY(), size, size);
    }
}
