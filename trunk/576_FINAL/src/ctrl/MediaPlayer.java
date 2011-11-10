package ctrl;

import model.Video;

public class MediaPlayer {

	private SoundPlayer soundPlayer;
	private VideoPlayer videoPlayer;

	public MediaPlayer(Video video) {
		soundPlayer = new SoundPlayer(video.getAudioFile());
		videoPlayer = new VideoPlayer(video);
	}

	public void startPlay() {
		soundPlayer.start();
	}

	public void continuePlay() {

	}

	public void pause() {

	}

	public void stop() {

	}
}
