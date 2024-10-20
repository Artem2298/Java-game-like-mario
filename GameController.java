package lab;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class GameController {

    private final World world;
    private final Scene scene;

    public GameController(World world, Scene scene) {
        this.world = world;
        this.scene = scene;

        // Добавьте слушатель событий клавиатуры
        scene.setOnKeyPressed(this::handleKeyPress);
        //scene.setOnKeyReleased(this::handleKeyRelease);
    }

    private void handleKeyPress(javafx.scene.input.KeyEvent event) {
        KeyCode keyCode = event.getCode();
        switch (keyCode) {
            case UP:
                if (world.intersectsWallAndMan() && world.intersectsStairsAndMan()) {
                    world.getMan().goUp();
                }
                else if (!world.intersectsWallAndMan() && world.intersectsStairsAndMan()) {
                    world.getMan().goUp();
                }
                break;
            case DOWN:
                if (world.intersectsWallAndMan() && world.intersectsStairsAndMan()) {
                    world.getMan().goDown();
                }
                else if (!world.intersectsWallAndMan() && world.intersectsStairsAndMan()) {
                    world.getMan().goDown();
                }
                break;
            case LEFT:
                world.getMan().goLeft();
                break;
            case RIGHT:
                world.getMan().goRight();
                break;
            case SPACE:
                if (world.intersectsWallAndMan()) {
                    world.getMan().jump();
                }
        }
    }


//    private void handleKeyRelease(KeyCode code) {
//        // Дополнительная логика при отпускании клавиш, если необходимо
//    }
}
