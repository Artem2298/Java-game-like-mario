package lab;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class DrawingThread extends AnimationTimer {
	private final GraphicsContext gc;
	private long lastTime;
	private final World world;
	private final GameController gameController;

	public DrawingThread(Canvas canvas, Scene scene) {
		this.gc = canvas.getGraphicsContext2D();
		this.world = new World(canvas.getWidth(), canvas.getHeight());
		this.gameController = new GameController(world, scene);
	}

	@Override
	public void handle(long now) {
		if (!world.youLose && !world.youWin) {
			if (lastTime > 0) {
				double timeDelta = (now - lastTime) / 1e9;
				world.simulate(timeDelta);
			}
			world.draw(gc);
			lastTime = now;
		}
		else if (world.youLose) {
			world.youLose(gc);
		}
		else if (world.youWin) {
			world.youWin(gc);
		}
	}
}
