package ctrl;

public class MediaPlayer {
	private static MediaPlayer mp = null;

	public static MediaPlayer getInstance() {
		if (mp == null) {
			mp = new MediaPlayer();
		}
		return mp;
	}

	private SoundPlayer soundPlayer;
	private VideoPlayer videoPlayer;

	public MediaPlayer() {
		soundPlayer = SoundPlayer.getInstance();
		videoPlayer = videoPlayer.getInstance();
	}
	
	
	
	public void startPlay() {

	}

	public void continuePlay() {

	}

	public void pause() {

	}

	public void stop() {

	}
}
