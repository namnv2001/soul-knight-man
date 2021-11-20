package master.soulknight;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import master.soulknight.States.GameStateManager;
import master.soulknight.States.PlayState;

public class GamePanel extends Application {
    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;

    public static int width = 1280;
    public static int height = 720;

    private GraphicsContext gc;
    private Canvas canvas;

    private GameStateManager gsm;

    public void initGraphics() {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
    }

    public void init() {
        initGraphics();
        gsm = new GameStateManager(gc);
    }

    @Override
    public void start(Stage stage) {

        final double GAME_HERTZ = 64.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Time Before Update

        final int MUBR = 3; // Must Update before render

        final double[] lastUpdateTime = {System.nanoTime()};
        final double[] lastRenderTime = new double[1];

        final double TARGET_FPS = 1000;
        final double TTBR = 1000000000 / TARGET_FPS; // Total time before render

        final int[] frameCount = {0};
        final int[] lastSecondTime = {(int) (lastUpdateTime[0] / 1000000000)};
        oldFrameCount = 0;

        tickCount = 0;
        oldTickCount = 0;
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                double now = System.nanoTime();
                int updateCount = 0;
                while (((now - lastUpdateTime[0]) > TBU) && (updateCount < MUBR)) {
                    lastUpdateTime[0] += TBU;
                    updateCount++;
                    tickCount++;
                    // (^^^^) We use this varible for the soul purpose of displaying it
                }

                if ((now - lastUpdateTime[0]) > TBU) {
                    lastUpdateTime[0] = now - TBU;
                }

                render();
                update();

                lastRenderTime[0] = now;
                frameCount[0]++;

                int thisSecond = (int) (lastUpdateTime[0] / 1000000000);
                if (thisSecond > lastSecondTime[0]) {
                    if (frameCount[0] != oldFrameCount) {
                        System.out.println("NEW SECOND " + thisSecond + " " + frameCount[0]);
                        oldFrameCount = frameCount[0];
                    }
                    if (tickCount != oldTickCount) {
                        System.out.println("NEW SECOND (T) " + thisSecond + " " + tickCount);
                        oldTickCount = tickCount;
                    }
                    tickCount = 0;
                    frameCount[0] = 0;
                    lastSecondTime[0] = thisSecond;
                }

                while (now - lastRenderTime[0] < TTBR && now - lastUpdateTime[0] < TBU) {
                    Thread.yield();
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        System.out.println("ERROR: yielding thread");
                    }
                    now = System.nanoTime();
                }
            }
        };
        timer.start();
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(event -> {
            PlayState.player.handleKeyPressedEvent(event.getCode());
        });
        scene.setOnKeyReleased(event -> {
            PlayState.player.handleKeyReleasedEvent(event.getCode());
        });
        scene.setOnMouseClicked(event -> {
            System.out.println("mouse clicked");
        });
    }

    public void update() {
        gsm.update();
    }

    public void render() {
        if (gc != null) {
            gsm.render(gc);
        }
    }
}