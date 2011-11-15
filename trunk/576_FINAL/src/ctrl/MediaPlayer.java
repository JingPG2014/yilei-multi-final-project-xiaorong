package ctrl;

import gui.RGBPlayer;
import model.Video;

public class MediaPlayer {

	private SoundPlayer soundPlayer;
	private VideoPlayer videoPlayer;

	public MediaPlayer(Video video, RGBPlayer player, int time) {
		soundPlayer = new SoundPlayer(video.getAudioFile());
		videoPlayer = new VideoPlayer(video, player);
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
