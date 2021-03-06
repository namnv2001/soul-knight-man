package master.soulknight;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import master.soulknight.States.GameStateManager;
import master.soulknight.Util.KeyHandler;
import master.soulknight.Util.MouseHandler;
import master.soulknight.Util.PlaySound;
import master.soulknight.Util.StatusTimer;

import java.io.FileInputStream;
import java.io.IOException;

public class GamePanel extends Application {
    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;

    public static int width = 1426;
    public static int height = 806 + 40;

    private GraphicsContext gc;
    private Canvas canvas;

    private GameStateManager gsm;

    private PlaySound ps;

    Image unmuteButton = null;
    Image muteButton = null;
    Image muteimage = null;
    Image image;

    public void initGraphics() {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
    }

    public void init() {
        initGraphics();
        gsm = new GameStateManager(gc);
        ps = new PlaySound("src/main/resources/Music/VitalityHelltakerOST-Mittsies-6554269.mp3");
    }

    @Override
    public void start(Stage stage) {

        init();
       // playBGMusic("src/main/resources/Music/VitalityHelltakerOST-Mittsies-6554269.mp3");
        ps.play(0.2, 10);
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);

        KeyHandler keyHandler = new KeyHandler();
        MouseHandler mouseHandler = new MouseHandler();

        scene.setOnKeyPressed(keyHandler);
        scene.setOnKeyReleased(keyHandler);


        // ----------------------------------------------------------------------------------
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
        // ---------------------------------------------------------------------------------
        StatusTimer timer = new StatusTimer() {
            @Override
            public void handle(long l) {


                //--------------------------------------------------------------------------
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

                //--------------------------------------------------------------------------
                render();
                input(keyHandler, mouseHandler);
                update();
                //--------------------------------------------------------------------------

                lastRenderTime[0] = now;
                frameCount[0]++;

                int thisSecond = (int) (lastUpdateTime[0] / 1000000000);
                if (thisSecond > lastSecondTime[0]) {
                    if (frameCount[0] != oldFrameCount) {
                        oldFrameCount = frameCount[0];
                    }
                    if (tickCount != oldTickCount) {
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
        // Pause ---------------------------------------------------------------
        scene.setOnKeyPressed(keyEvent -> {
            if (gsm.isPlayState() && !gsm.isGameOverState()) {
                if (keyEvent.getCode() == KeyCode.ESCAPE) {
                    if (timer.isRunning()) {
                        try {
                            image = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/Pause.png"));
                            muteimage = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/Pause-Muted.png"));
                            muteButton = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/PauseButton.png"));
                            unmuteButton = new Image(new FileInputStream("src/main/resources/Sprite/Ui/States/UnmuteButton.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(ps.isMute()) {
                            gc.drawImage(muteimage,0, 0);
                        } else {
                            gc.drawImage(image, 0, 0);
                        }
                        timer.stop();
                        scene.setOnMouseClicked(mouseEvent -> {
                            if(mouseEvent.getX() > 0) {
                                double x = mouseEvent.getX();
                                double y = mouseEvent.getY();
                                // continue
                                if (x >= 374 && x <= 579 && y >= 339 && y <= 506) {
                                    timer.start();
                                }
                                // mute
                                if (x >= 607 && x <= 812 && y >= 339 && y <= 506) {
                                    System.out.println("mute");
                                    ps.mute();

                                    if (ps.isMute()) {
                                        gc.drawImage(muteButton, 607, 339);
                                    } else {
                                        gc.drawImage(unmuteButton, 607, 339);
                                    }
                                }
                                // quit
                                if (x >= 842 && x <= 1047 && y >= 339 && y <= 506) {
                                    gsm.pop(0);
                                    gsm.add(0);
                                    scene.setOnMouseClicked(mouseHandler);
                                    timer.start();
                                }
                            }
                        });
                    } else {
                        timer.start();
                    }
                } else {
                    if (KeyEvent.KEY_PRESSED.equals(keyEvent.getEventType())) {
                        keyHandler.getSetActiveKeys().add(keyEvent.getCode());
                    }
                    scene.setOnMouseClicked(mouseHandler);
                }
            }
        });
        scene.setOnMouseClicked(mouseHandler);
        // -------------------------------------------------------------------
        stage.setScene(scene);
        stage.show();
    }

    public void update() {
        gsm.update();
    }

    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        gsm.input(keyHandler, mouseHandler);
    }

    public void render() {
        gc.clearRect(0, 0, width, height);
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,width,height);
        if (gc != null) {
            gsm.render(gc);
        }
    }

}