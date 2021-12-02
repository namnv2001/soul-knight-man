package master.soulknight.States;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameState {
    protected static GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public static void mouseEvent(double x, double y) {
        // To pick champ state
        if (gsm.isMenuState() && x >= 130 && y >= 540 && x <= 345 && y <= 715) {
            gsm.pop(0);
            gsm.add(5);
        }
        // To info state
        if (gsm.isMenuState() && x >= 415 && y >= 540 && x <= 630 && y <= 715) {
            gsm.pop(0);
            gsm.add(4);
        }
        // Escape info state
        if (gsm.isInfoState() && x >= 1307 && y >= 71 && x <= 1363 && y <= 133) {
            gsm.pop(0);
            gsm.add(0);
        }
        // Escape pick champ state
        if (gsm.isPickChampState() && x >= 1249 && y >= 25 && x <= 1344 && y <= 130) {
            gsm.pop(0);
            gsm.add(0);
        }
        // Enter play state - pick champ 1
        if (gsm.isPickChampState() && x >= 242 && y >= 363 && x <= 514 && y <= 618) {
            System.out.println("Pick champ: Alchemist");
            gsm.pop(0);
            gsm.add(1);
        }
        // Enter play state - pick champ 2
        if (gsm.isPickChampState() && x >= 542 && y >= 300 && x <= 882 && y <= 625) {
            System.out.println("Pick champ: Druid");
            gsm.pop(0);
            gsm.add(1);
        }
        // Enter play state - pick champ 3
        if (gsm.isPickChampState() && x >= 917 && y >= 328 && x <= 1224 && y <= 618) {
            System.out.println("Pick champ: Priest");
            gsm.pop(0);
            gsm.add(1);
        }
    }

    public abstract void update();

    public abstract void render(GraphicsContext gc);
}
