package player;

import gui.RGBPlayer;
import model.Video;

public class MediaPlayer {

	private SoundPlayer soundPlayer;
	private VideoPlayer videoPlayer;

	public MediaPlayer(Video video, RGBPlayer player, int timestamp) {
		soundPlayer = new SoundPlayer(video, timestamp);
		videoPlayer = new VideoPlayer(video, player, timestamp);
	}

	public void startPlay() {
		videoPlayer.start();
		soundPlayer.start();
	}

	public void continuePlay() {

	}

	public void pause() {

	}

	public void stop() {
		videoPlayer.interrupt();
		soundPlayer.interrupt();
	}
}
