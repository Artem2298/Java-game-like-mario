package lab;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *  Class <b>App</b> - extends class Application and it is an entry point of the program
 * @author     Java I
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private Canvas canvas;
	private AnimationTimer timer;

	@Override
	public void start(Stage primaryStage) {
		try {
			//Construct a main window with a canvas.
			Group root = new Group();
			canvas = new Canvas(800, 500);
			root.getChildren().add(canvas);
			Scene scene = new Scene(root, 800, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.resizableProperty().set(false);
			primaryStage.setTitle("KUZ0061 JumpMan");
			primaryStage.show();

			//Exit program when main window is closed
			primaryStage.setOnCloseRequest(this::exitProgram);
			timer = new DrawingThread(canvas, scene);
			timer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void stop() throws Exception {
		timer.stop();
		super.stop();
	}

	private void exitProgram(WindowEvent evt) {
		System.exit(0);
	}


}