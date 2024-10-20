package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Ball implements DrawableSimulable{
    protected World world;
    protected Point2D position;
    private Point2D speed;
    protected double size;
    private final static Image image = new Image(Step.class.getResourceAsStream("fireball-transparent.gif"));
    public Ball(World world) {
        this.world = world;
    }
    private int randomValue;


    public Ball(World world, Point2D start, Point2D speed, double size) {
        this.world = world;
        this.position = start;
        this.speed = speed;
        this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY(), size, size);
    }

    public void randomFly() {
        Random rand = new Random();
        this.randomValue = rand.nextInt(4) + 1;
        switch (randomValue) {
            case 1:
                position = new Point2D(0, 10);
                speed = new Point2D(100,0);
                break;
            case 2:
                position = new Point2D(0, 490 - size);
                speed = new Point2D(100,0);
                break;
            case 3:
                position = new Point2D(790 - size, 0);
                speed = new Point2D(0,100);
                break;
            case 4:
                position = new Point2D(10, 0);
                speed = new Point2D(0,100);
                break;
        }
    }

    public void simulate(double timeDelta) {
        position = position.add(speed.multiply(timeDelta));
        if(position.getY() >= world.getHeight() || position.getY() <= 0 ||
           position.getX() >= world.getWidth() || position.getX() <= 0) {
            randomFly();
        }
    }

    public void checkIntersectionWithMan() {
        Rectangle2D manBox = world.getMan().getBoundinBox(world.getMan().getSize());
        if (randomValue == 1) {
            Rectangle2D ballBox = new Rectangle2D(position.getX(), position.getY(), 1, world.getHeight());
            if (ballBox.intersects(manBox)) {
                this.speed = new Point2D(0, 200);
            }
        } else if (randomValue == 2) {
            // Используйте Math.abs() для гарантии положительного значения высоты
            Rectangle2D ballBox = new Rectangle2D(position.getX(), position.getY() - world.getHeight(), 1, world.getHeight());
            if (ballBox.intersects(manBox)) {
                this.speed = new Point2D(0, -200);
            }
        } else if (randomValue == 3) {
            Rectangle2D ballBox = new Rectangle2D(position.getX() - world.getWidth(), position.getY(), world.getWidth(), 1);
            if (ballBox.intersects(manBox)) {
                this.speed = new Point2D(-200, 0);
            }
        } else if (randomValue == 4) {
            Rectangle2D ballBox = new Rectangle2D(position.getX(), position.getY(), world.getWidth(), 1);
            if (ballBox.intersects(manBox)) {
                this.speed = new Point2D(200, 0);
            }
        }
    }

    public Rectangle2D getBoundinBox() {
        return new Rectangle2D(position.getX(), position.getY(),size, size);
    }

    public void setSpeed(Point2D speed) {
        this.speed = speed;
    }

}

