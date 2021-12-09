package master.soulknight.Util;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PlaySound {

    MediaPlayer mp;
    public PlaySound(String file) {
        Media media = new Media(new File(file).toURI().toString());
        mp = new MediaPlayer(media);
        System.out.println("sound1");
    }

    public void play() {
        System.out.println("sound");
        mp.setVolume(0.75);
        mp.setCycleCount(10);
        mp.play();
    }

    public void mute() {
        mp.setMute(!mp.isMute());
    }
}
