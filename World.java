package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class World {
    private static final int STAIR_SIZE = 40;
    private static final int WALL_SIZE = 20;
    private static final double MAN_SIZE = 30;
    private static final double BALL_SIZE = 30;
    private static final double LOOT_SIZE = 25;
    private final double width;     // 800
    private final double height;    // 500
    private final Man man;
    private final Ball ball;

    private List<Loot> loots = new ArrayList<>();
    private final Wall[] walls;
    private final Stairs[] stairs;
    public boolean youLose = false;
    public boolean youWin = false;


    //  Инициализация всех обьектов
    public World (double width, double height){
        this.width = width;
        this.height = height;

        ball = new Ball(this,new Point2D(10,10), new Point2D(100,0), BALL_SIZE);
        man = new Man(this,new Point2D(20,50),new Point2D(0,0),MAN_SIZE);

        loots.add(new Loot(this, new Point2D(390,30), LOOT_SIZE));
        loots.add(new Loot(this, new Point2D(40,105), LOOT_SIZE));
        loots.add(new Loot(this, new Point2D(740,105), LOOT_SIZE));
        loots.add(new Loot(this, new Point2D(220,180), LOOT_SIZE));
        loots.add(new Loot(this, new Point2D(560,180), LOOT_SIZE));
        loots.add(new Loot(this, new Point2D(200,255), LOOT_SIZE));
        loots.add(new Loot(this, new Point2D(450,330), LOOT_SIZE));

        walls = new Wall[13];
        walls[0] = new Wall(this,new Point2D(200,50),20,WALL_SIZE);
        walls[1] = new Wall(this, new Point2D(40,125), 12,WALL_SIZE);
        walls[2] = new Wall(this, new Point2D(520,125), 12, WALL_SIZE);
        walls[3] = new Wall(this,new Point2D(120,200), 10, WALL_SIZE);
        walls[4] = new Wall(this,new Point2D(480,200), 10, WALL_SIZE);
        walls[5] = new Wall(this,new Point2D(40,275), 9, WALL_SIZE);
        walls[6] = new Wall(this,new Point2D(580,275), 9, WALL_SIZE);
        walls[7] = new Wall(this,new Point2D(50,350), 5, WALL_SIZE);
        walls[8] = new Wall(this,new Point2D(250,350), 5, WALL_SIZE);
        walls[9] = new Wall(this,new Point2D(450,350), 5, WALL_SIZE);
        walls[10] = new Wall(this,new Point2D(650,350), 5, WALL_SIZE);
        walls[11] = new Wall(this,new Point2D(120,425), 8, WALL_SIZE);
        walls[12] = new Wall(this,new Point2D(520,425), 8, WALL_SIZE);

        stairs = new Stairs[12];
        stairs[0] = new Stairs(this,new Point2D(190,50),2,STAIR_SIZE);
        stairs[1] = new Stairs(this, new Point2D(570,50),2,STAIR_SIZE);
        stairs[2] = new Stairs(this, new Point2D(110,125),4,STAIR_SIZE);
        stairs[3] = new Stairs(this, new Point2D(650,125),4,STAIR_SIZE);
        stairs[4] = new Stairs(this, new Point2D(290,200),4,STAIR_SIZE);
        stairs[5] = new Stairs(this, new Point2D(470,200),4,STAIR_SIZE);
        stairs[6] = new Stairs(this, new Point2D(40,275),2,STAIR_SIZE);
        stairs[7] = new Stairs(this, new Point2D(720,275),2,STAIR_SIZE);
        stairs[8] = new Stairs(this, new Point2D(110,350),2,STAIR_SIZE);
        stairs[9] = new Stairs(this, new Point2D(250,350),2,STAIR_SIZE);
        stairs[10] = new Stairs(this, new Point2D(510,350),2,STAIR_SIZE);
        stairs[11] = new Stairs(this, new Point2D(650,350),2,STAIR_SIZE);
    }

    //  отрисовка
    public void drawBackGround(GraphicsContext gc){
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,width, height);
    }
    public void draw(GraphicsContext gc) {
        gc.clearRect(0,0,width, height);
        this.drawBackGround(gc);

        for(Wall w : walls) { w.draw(gc); }
        for(Stairs s : stairs){ s.draw(gc); }
        for(Loot s : loots){ s.draw(gc); }

        man.draw(gc);
        ball.draw(gc);
    }

    //  пересечение обьектов с героем
    public boolean intersectsWallAndMan() {
        // ПЕРЕСЕЧЕНИЕ СТЕН И ЧЕЛОВЕКА
        Rectangle2D manBox = man.getBoundinBox();
        Rectangle2D[] wallBoxes = new Rectangle2D[13];
        boolean intersectsWall = false;
        for (int i = 0; i < walls.length; i++) {
            wallBoxes[i] = walls[i].getBoundingBox();
        }
        for (Rectangle2D wallBox : wallBoxes) {
            if (manBox.intersects(wallBox)) {
                intersectsWall = true;
                break;
            }
        }
        return intersectsWall;
    }
    public boolean intersectsStairsAndMan() {
        Rectangle2D[] stairsBoxes = new Rectangle2D[12];
        Rectangle2D manBox = man.getBoundinBox();
        boolean intersectsStair = false;
        for (int i = 0; i < stairs.length; i++) {
            stairsBoxes[i] = stairs[i].getBoundinBox();
        }
        for (Rectangle2D stairBox : stairsBoxes) {
            if (manBox.intersects(stairBox)) {
                intersectsStair = true;
                break;
            }
        }
        return intersectsStair;
    }

    //  Движениея
    public void simulate (double timeDelta) {
        if (intersectsWallAndMan()) {
            man.setSpeed(new Point2D(0,0));
        }
        else if (intersectsStairsAndMan()) {
            man.setSpeed(new Point2D(0,0));
        }
        else {
            if (!man.getIsJump()) {
                man.goDown();
            }
        }
        if (ball.getBoundinBox().intersects(man.getBoundinBox(man.getSize()))) {
            youLose = true;
        }

        ball.checkIntersectionWithMan();
        lootCheck();
        this.man.simulate(timeDelta);
        this.ball.simulate(timeDelta);
    }

    public void youLose(GraphicsContext gc) {
        drawBackGround(gc);
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Retro Typo", FontWeight.BOLD,30));
        gc.fillText("YOU LOSE", getWidth()/2 - 100, getHeight()/2);
    }

    public void youWin(GraphicsContext gc) {
        drawBackGround(gc);
        gc.setFill(Color.RED);
        gc.setFont(Font.font("Retro Typo", FontWeight.BOLD,30));
        gc.fillText("YOU WIN", getWidth()/2 - 100, getHeight()/2);
    }

    private void lootCheck() {
        Iterator<Loot> lootIterator = loots.iterator();
        while (lootIterator.hasNext()) {
            Loot loot = lootIterator.next();
            if (man.getBoundinBox().intersects(loot.getBoundinBox())) {
                man.setLoot();
                lootIterator.remove();
                System.out.println(man.getLoot());
            }
        }
        if(loots.isEmpty()) {
            youWin = true;
        }
    }

    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return this.height;
    }
    public Man getMan() {
        return this.man;
    }
}
