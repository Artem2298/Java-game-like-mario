package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



public class Man implements DrawableSimulable {
    private static final int JUMP_HEIGHT= 50;
    private static final Point2D JUMP_SPEED = new Point2D(0,-150);
    private final World world;
    private Point2D position;
    private Point2D speed;
    private double size;
    private final static Image image = new Image(Step.class.getResourceAsStream("man.png"));

    private boolean isJumping = false;
    private Point2D startJumpPosition;

    private int loot = 0;


    public Man(World world, Point2D position, Point2D speed, double size) {
        this.world = world;
        this.position = position;
        this.speed = speed;
        this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(image, position.getX(), position.getY(), size, size);
    }

    public void simulate(double timeDelta) {
        if (isJumping) {
            if (this.position.getY() <= startJumpPosition.getY() - JUMP_HEIGHT) {
                isJumping = false;
            }
            position = position.add(JUMP_SPEED.multiply(timeDelta));
        } else {
            position = position.add(speed.multiply(timeDelta));
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            startJumpPosition = this.position;
        }
    }

    public Rectangle2D getBoundinBox() {
        return new Rectangle2D(position.getX(), position.getY() + size, size, 1);
    }

    public Rectangle2D getBoundinBox(double size) {
        return new Rectangle2D(position.getX(), position.getY(), size, size);
    }

    public void setSpeed(Point2D speed) {
        this.speed = speed;
    }

    public double getSize(){
        return this.size;
    }

    public void goRight() {
        this.position = new Point2D(this.position.getX() + 3,this.position.getY());
    }
    public void goLeft() {
        this.position = new Point2D(this.position.getX() - 3,this.position.getY());
    }
    public void goDown() {
        this.position = new Point2D(this.position.getX(),this.position.getY() + 3);
    }
    public void goUp() {
        this.position = new Point2D(this.position.getX(),this.position.getY() - 3);
    }

    public boolean getIsJump() {return this.isJumping;}

    public void setLoot() {
        this.loot++;
    }
    public int getLoot() {
        return loot;
    }

}
