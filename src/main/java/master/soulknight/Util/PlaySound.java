package master.soulknight.Util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class PlaySound {

    MediaPlayer mp;

    public PlaySound(String file) {
        Media media = new Media(new File(file).toURI().toString());
        mp = new MediaPlayer(media);
    }

    public boolean isMute() {
        return mp.isMute();
    }

    public void play() {
        mp.setVolume(0.75);
        mp.setCycleCount(10);
        mp.play();
    }

    public void mute() {
        mp.setMute(!mp.isMute());
    }
}
