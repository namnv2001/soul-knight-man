package master.soulknight;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import master.soulknight.States.GameStateManager;

import java.awt.image.BufferedImage;

public class GamePanel extends Application {

    public static int width = 1280;
    public static int height = 720;

    private boolean running;
    private BufferedImage img;

    private GraphicsContext gc;
    private Canvas canvas;

    private GameStateManager gsm;

    EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
        private KeyEvent key;

        @Override
        public void handle(KeyEvent keyEvent) {
            this.key = keyEvent;
        }
    };

    public void initGraphics() {
        canvas = new Canvas(width,height);
        gc = canvas.getGraphicsContext2D();
    }

    public void init() {
        running = true;
        initGraphics();
        gsm = new GameStateManager(gc);
    }

    @Override
    public void start(Stage stage) throws Exception {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
//        while(running) {
//            render();
//            update();
//        }
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, eventHandler);
        stage.setScene(scene);
        stage.show();
    }

    public void input(KeyEvent key) {
        gsm.input(key);
    }

    public void update() {
        gsm.update();
    }

    public void render() {
        if(gc != null) {
            gc.setFill(Color.BLUE);
            gc.fillRect(0,0,width,height);
            gsm.render(gc);
        }
    }

}
