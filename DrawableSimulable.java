package lab;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface DrawableSimulable {
    public void draw(GraphicsContext gc);
    public void simulate(double timeDelta);
}
