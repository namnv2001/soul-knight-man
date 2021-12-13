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

    public void play(double Volume, int CycleCount) {
        mp.setVolume(Volume);
        mp.setCycleCount(CycleCount);
        mp.play();
    }

    public void mute() {
        mp.setMute(!mp.isMute());
    }
}
